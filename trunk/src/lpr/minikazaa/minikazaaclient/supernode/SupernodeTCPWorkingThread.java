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
import lpr.minikazaa.minikazaaclient.NodeConfig;
import lpr.minikazaa.minikazaaclient.Query;
import lpr.minikazaa.minikazaaclient.SupernodeList;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 6-ott-2008
 * @file SupernodeTCPWorkingThread.java
 */
public class SupernodeTCPWorkingThread implements Runnable{
    private Socket client_socket;
    private NodeConfig my_conf;
    private SupernodeList my_list;
    private SupernodeQueryList my_q_list;
    
    public SupernodeTCPWorkingThread
            (Socket sock, NodeConfig conf, SupernodeList list, SupernodeQueryList q_list){
        this.client_socket = sock;
        this.my_conf = conf;
        this.my_list = list;
        this.my_q_list = q_list;
    }
    
    public void run(){
        ObjectInputStream input_object = null;
        Query peer_query = null;
        try {
             input_object = new ObjectInputStream(this.client_socket.getInputStream());
             peer_query = (Query)input_object.readObject();
             
             if(!this.my_q_list.isIn(peer_query)){
                this.my_q_list.addQuery(peer_query);
                
                Query out_query = this.my_q_list.getRelativeQuery(peer_query);
                ArrayList <Query> sending_queries = 
                        this.my_q_list.generateQueryList(out_query, this.my_list.getSubSet());
                
                Socket sock = null;
                for(Query q : sending_queries){
                     sock = new Socket(q.getReceiver().getIaNode().toString(),q.getReceiver().getDoor());
                    ObjectOutputStream output = new ObjectOutputStream(sock.getOutputStream());
                    output.writeObject(q);
                }
                sock.close();
             }
             else{
                Query out_query = this.my_q_list.getRelativeQuery(peer_query);
                ArrayList <Query> sending_queries = 
                        this.my_q_list.generateQueryList(out_query, this.my_list.getSubSet());
                
                Socket sock = null;
                for(Query q : sending_queries){
                    sock = new Socket(q.getReceiver().getIaNode().toString(),q.getReceiver().getDoor());
                    ObjectOutputStream output = new ObjectOutputStream(sock.getOutputStream());
                    output.writeObject(q);
                }
                sock.close();
             }
        } catch (IOException ex) {
            Logger.getLogger(SupernodeTCPWorkingThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException c_ex){
            Logger.getLogger(SupernodeTCPWorkingThread.class.getName()).log(Level.SEVERE, null, c_ex);
        }   
    }
}
