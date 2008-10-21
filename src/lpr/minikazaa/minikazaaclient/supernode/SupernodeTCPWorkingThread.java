/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.minikazaaclient.supernode;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lpr.minikazaa.minikazaaclient.Answer;
import lpr.minikazaa.minikazaaclient.NodeConfig;
import lpr.minikazaa.minikazaaclient.Query;
import lpr.minikazaa.minikazaaclient.SupernodeList;
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeFiles;

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

    public SupernodeTCPWorkingThread(Socket sock, NodeConfig conf, SupernodeList list,
            SupernodeQueryList q_list, SupernodeOnFileList f_list) {
        this.client_socket = sock;
        this.my_conf = conf;
        this.my_list = list;
        this.my_q_list = q_list;
        this.my_f_list = f_list;
    }

    public void run() {
        ObjectInputStream input_object = null;
        Query peer_query = null;
        try {
            input_object = new ObjectInputStream(this.client_socket.getInputStream());
            peer_query = (Query) input_object.readObject();

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

                Query answer_query = new Query();
                answer_query.setReceiver(peer_query.getSender());
                answer_query.setSender(peer_query.getReceiver());
                answer_query.setOrigin(peer_query.getOrigin());
                answer_query.setAskingQuery(peer_query.getBodyQ());

                Answer answer = new Answer(query_answer);
                answer_query.setAnswerQuery(answer);

                output_stream.writeObject(answer_query);

                cli_sock.close();
                //Propagazione query
                if (peer_query.getTTL() >= 1) {
                    //Propaga la query
                    
                }
                //Altrimenti non fare niente perchè la query muore.
                return;

            } else if (peer_query.getBodyA() != null &&
                    peer_query.getBodyF() == null) {
                //E' arrivata al nodo una risposta di un altro peer,
                //bisogna dunque inoltrarla al peer richiedente.
            } else if (peer_query.getBodyF() != null &&
                    peer_query.getBodyA() == null &&
                    peer_query.getBodyQ() == null) {
                //Bisogna aggiornare la lista di file relativi a un ordinary
                //node sottostante.
            }
        } catch (IOException ex) {
            Logger.getLogger(SupernodeTCPWorkingThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException c_ex) {
            Logger.getLogger(SupernodeTCPWorkingThread.class.getName()).log(Level.SEVERE, null, c_ex);
        }
    }
}
