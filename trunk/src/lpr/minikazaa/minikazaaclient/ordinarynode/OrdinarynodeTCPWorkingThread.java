/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.minikazaaclient.ordinarynode;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.DownloadPartRequest;
import lpr.minikazaa.minikazaaclient.Query;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 13-nov-2008
 * @file OrdinarynodeTCPWorkingThread.java
 */
public class OrdinarynodeTCPWorkingThread implements Runnable {

    Socket in_sock;
    OrdinarynodeQuestionsList my_found_list;
    
    public OrdinarynodeTCPWorkingThread(Socket incoming, OrdinarynodeQuestionsList list) {
        this.my_found_list = list;
        this.in_sock = incoming;
    }

    public void run() {
        //We may have 2 kinds of object in:
        //1 - Download request;
        //2 - Answer from an outgone query.
        ObjectInputStream input_object = null;
        Query peer_query = null;
        DownloadPartRequest peer_request = null;
        Object incoming_obj;

        try {
            input_object = new ObjectInputStream(this.in_sock.getInputStream());
            incoming_obj = input_object.readObject();

            if (incoming_obj instanceof Query) {
                //incoming query contains files relativ our regex research.
                peer_query = (Query) incoming_obj;
                //Check if incoming query is not corrupt.
                if ((peer_query.getBodyA() != null) &&
                        (peer_query.getBodyF() == null) &&
                        (peer_query.getBodyQ() == null)) {
                    //This query is correct
                    this.my_found_list.add(peer_query.getBodyA());
                }
            } else if (incoming_obj instanceof DownloadPartRequest) {
                //Incoming download reuest
                peer_request = (DownloadPartRequest) incoming_obj;
                NodeInfo source = peer_request.getSource();
                Socket response_socket = new Socket();

            }



        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrdinarynodeTCPWorkingThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OrdinarynodeTCPWorkingThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
