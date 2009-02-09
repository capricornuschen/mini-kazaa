package lpr.minikazaa.minikazaaclient.supernode;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.Answer;
import lpr.minikazaa.minikazaaclient.NodeConfig;
import lpr.minikazaa.minikazaaclient.Query;
import lpr.minikazaa.minikazaaclient.SupernodeList;
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeFiles;
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeFriendRequest;
import lpr.minikazaa.util.NetUtil;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 6-ott-2008
 * @file SupernodeTCPWorkingThread.java
 */
public class SupernodeTCPWorkingThread implements Runnable {

    private Socket client_socket;
    private NodeConfig my_conf;
    private SupernodeList my_list;
    private SupernodeQueryList my_q_list;
    private SupernodeOnFileList my_f_list;

    public SupernodeTCPWorkingThread(
            Socket sock,
            NodeConfig conf,
            SupernodeList list,
            SupernodeQueryList q_list,
            SupernodeOnFileList f_list) {
        this.client_socket = sock;
        this.my_conf = conf;
        this.my_list = list;
        this.my_q_list = q_list;
        this.my_f_list = f_list;

    }

    public void run() {
        ObjectInputStream input_object = null;
        Query peer_query = null;
        Object read_object = null;
        try {
            input_object = new ObjectInputStream(this.client_socket.getInputStream());
            read_object = input_object.readObject();
            if (read_object instanceof Query) {
                peer_query = (Query) read_object;

                if (peer_query.getBodyQ() != null &&
                        peer_query.getBodyA() == null &&
                        peer_query.getBodyF() == null) {
                    //Richiesta di un file, all'interno di body_q abbiamo una
                    //espressione regolare che identifica la richiesta di un file

                    //Mia risposta al nodo richiedente.
                    ArrayList<OrdinarynodeFiles> query_answer = null;
                    query_answer = this.my_f_list.searchFiles(peer_query.getBodyQ());

                    Socket cli_sock = new Socket(
                            peer_query.getSender().getIaNode(),
                            peer_query.getSender().getDoor());

                    ObjectOutputStream output_stream = new ObjectOutputStream(
                            cli_sock.getOutputStream());

                    //Sistemo i vari campi della query
                    Query answer_query = new Query();
                    answer_query.setReceiver(peer_query.getSender());
                    answer_query.setSender(peer_query.getReceiver());
                    answer_query.setOrigin(peer_query.getOrigin());
                    answer_query.setAskingQuery(peer_query.getBodyQ());

                    Answer answer = new Answer(query_answer, peer_query.getBodyA().getID());
                    answer_query.setAnswerQuery(answer);

                    output_stream.writeObject(answer_query);

                    cli_sock.close();
                    //Propagazione query
                    if (peer_query.getTTL() >= 1) {
                        //Propaga la query
                        ArrayList<Query> out_query_queue =
                                this.my_q_list.generateQueryList(peer_query,
                                this.my_list.getSubSet());

                        for (Query q : out_query_queue) {
                            NetUtil.sendQuery(q);
                        }
                    }
                    //Altrimenti non fare niente perchè la query muore.
                    return;

                } else if (peer_query.getBodyA() != null &&
                        peer_query.getBodyF() == null) {
                    //E' arrivata al nodo una risposta di un altro peer,
                    //bisogna dunque inoltrarla al peer richiedente.
                    Query other_answer = this.my_q_list.getRelativeQuery(peer_query);
                    other_answer.setAnswerQuery(peer_query.getBodyA());

                    //Scambio mittete e destinatario.
                    NodeInfo temp_sender = other_answer.getSender();
                    other_answer.setSender(other_answer.getReceiver());
                    other_answer.setReceiver(temp_sender);

                    //Send query.
                    NetUtil.sendQuery(other_answer);

                } else if (peer_query.getBodyF() != null &&
                        peer_query.getBodyA() == null &&
                        peer_query.getBodyQ() == null) {
                    //Bisogna aggiornare la lista di file relativi a un ordinary
                    //node sottostante.
                    this.my_f_list.addNewOnFileList(peer_query.getBodyF());


                }
            } else if (read_object instanceof OrdinarynodeFriendRequest) {

                System.out.println("DEBUG: Friendship request received.");

                OrdinarynodeFriendRequest friendship = (OrdinarynodeFriendRequest) read_object;

                System.out.println("DEBUG: friendship "+friendship.toString());

                boolean friend;

                if(friendship.getRelationship())
                    friend = true;
                else
                    friend = false;

                while (friend) {
                    System.out.println("DEBUG: "+Thread.currentThread()+" Ciclo di friendship.");
                    
                    Object friend_request = input_object.readObject();
                    
                    System.out.println("DEBUG: richiesta ricevuta "+friend_request.toString());
                    if (friend_request instanceof Query) {
                        //Ricezione di una query da un ON friend.
                        Query friend_query = (Query) friend_request;
                        //Stampa di debug
                        System.out.println("Testo della query ricevuta: "+friend_query.getBodyQ());
                        //Mia risposta al nodo richiedente.
                        ArrayList<OrdinarynodeFiles> query_answer = null;
                        query_answer = this.my_f_list.searchFiles(friend_query.getBodyQ());

                        Answer answer = new Answer(query_answer, friend_query.getId());

                        friend_query.setAnswerQuery(answer);

                        Socket cli_sock =
                                new Socket(
                                friend_query.getSender().getIaNode(),
                                friend_query.getSender().getDoor());

                        ObjectOutputStream out_stream = new ObjectOutputStream(cli_sock.getOutputStream());

                        out_stream.writeObject(friend_query);
  
                    } else if (friend_request instanceof OrdinarynodeFiles) {
                        //Ricezione di una lista di file da un ON friend.
                    } else if (friend_request instanceof OrdinarynodeFriendRequest){
                        //Probabile disconnessione o terminazione della relazione.
                        OrdinarynodeFriendRequest update_friend = (OrdinarynodeFriendRequest) friend_request;
                        if(!update_friend.getRelationship())
                            friend  = false; //Con conseguente uscita dal ciclo
                    }



                }

            }
        } catch (IOException ex) {
            Logger.getLogger(SupernodeTCPWorkingThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException c_ex) {
            Logger.getLogger(SupernodeTCPWorkingThread.class.getName()).log(Level.SEVERE, null, c_ex);
        }
    }
}
