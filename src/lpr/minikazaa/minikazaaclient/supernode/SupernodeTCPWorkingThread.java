/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient.supernode;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import lpr.minikazaa.minikazaaclient.NodeConfig;
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
    public SupernodeTCPWorkingThread(Socket sock, NodeConfig conf, SupernodeList list){
        this.client_socket = sock;
        this.my_conf = conf;
        this.my_list = list;
    }
    
    public void run(){
        ObjectInputStream input_object;
        try {
             input_object = new ObjectInputStream(this.client_socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(SupernodeTCPWorkingThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
