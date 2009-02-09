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
import lpr.minikazaa.minikazaaclient.DownloadRequest;
import lpr.minikazaa.minikazaaclient.DownloadResponse;
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
    OrdinarynodeDownloadMonitor  my_dl_monitor;
    OrdinarynodeFiles my_files;
    
    public OrdinarynodeTCPWorkingThread(
            Socket incoming,
            OrdinarynodeQuestionsList list,
            OrdinarynodeDownloadMonitor dl_monitor,
            OrdinarynodeFiles files) {
        
        this.my_found_list = list;
        this.in_sock = incoming;
        this.my_dl_monitor = dl_monitor;
        this.my_files = files;
    }

    public void run() {
        //We may have 2 kinds of object in:
        //1 - Download request;
        //2 - Answer from an outgone query.
        ObjectInputStream input_object = null;
        Query peer_query = null;
        DownloadRequest peer_request = null;
        DownloadResponse peer_response = null;
        Object incoming_obj;

        try {
            input_object = new ObjectInputStream(this.in_sock.getInputStream());
            incoming_obj = input_object.readObject();

            if (incoming_obj instanceof Query) {
                //incoming query contains files relativ our regex research.
                peer_query = (Query) incoming_obj;

                System.out.println("DEBUG: risposta alla query: "+peer_query.getBodyQ());
                //Check if incoming query is not corrupt.
                if ((peer_query.getBodyA() != null) &&
                        (peer_query.getBodyF() == null) ) {

                    System.out.println("DEBUG: la query "+peer_query.getBodyQ()+" è corretta.");
                    //This query is correct
                    this.my_found_list.add(peer_query.getBodyA());
                }
            } else if (incoming_obj instanceof DownloadRequest) {
                //Incoming download reuest
                peer_request = (DownloadRequest) incoming_obj;
                NodeInfo source = peer_request.getSource();
                Socket response_socket = new Socket(source.getIaNode(),source.getDoor());

                
            } else if (incoming_obj instanceof DownloadResponse){
                //Look what file is and add the bytes.
                peer_response = (DownloadResponse) incoming_obj;
                this.my_dl_monitor.addBytes(peer_response);

            }



        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrdinarynodeTCPWorkingThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OrdinarynodeTCPWorkingThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
