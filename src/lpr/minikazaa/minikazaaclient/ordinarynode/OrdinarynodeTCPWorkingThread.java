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
import javax.management.Query;
import lpr.minikazaa.minikazaaclient.DownloadRequest;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 13-nov-2008
 * @file OrdinarynodeTCPWorkingThread.java
 */
public class OrdinarynodeTCPWorkingThread implements Runnable {

    Socket in_sock;

    public OrdinarynodeTCPWorkingThread(Socket incoming) {
        this.in_sock = incoming;
    }

    public void run() {
        //We may have 2 kinds of object in:
        //1 - Download request;
        //2 - Answer from an outgone query.
        ObjectInputStream input_object = null;
        Query peer_query = null;
        DownloadRequest peer_request = null;
        Object incoming_obj;

        try {
            input_object = new ObjectInputStream(this.in_sock.getInputStream());
            incoming_obj = input_object.readObject();
            
            if(incoming_obj instanceof Query){
                //incoming query contains files relativ our regex research.
                peer_query = (Query) incoming_obj;
                
                
            }
            else if(incoming_obj instanceof DownloadRequest){
                peer_request = (DownloadRequest) incoming_obj;
            }
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrdinarynodeTCPWorkingThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OrdinarynodeTCPWorkingThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
