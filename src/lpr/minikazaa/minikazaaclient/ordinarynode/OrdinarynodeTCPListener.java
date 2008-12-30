/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient.ordinarynode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import lpr.minikazaa.minikazaaclient.NodeConfig;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 13-nov-2008
 * @file OrdinarynodeTCPListener.java
 */
public class OrdinarynodeTCPListener implements Runnable {
    
    private NodeConfig my_conf;
    private OrdinarynodeQuestionsList my_found_list;
    private OrdinarynodeDownloadMonitor my_dl_monitor;
    
    
    public OrdinarynodeTCPListener(NodeConfig conf, OrdinarynodeQuestionsList list, OrdinarynodeDownloadMonitor dl_monitor){
        this.my_conf = conf;
        this.my_found_list = list;
        this.my_dl_monitor = dl_monitor;
    }

    public void run() {
        ServerSocket listen_sock = null;
        Socket incoming_sock = null;
        
        ThreadPoolExecutor answer_pool = new ThreadPoolExecutor
                (10,15,50000L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue <Runnable>());
        
        try{
            listen_sock = new ServerSocket(this.my_conf.getPort());
        }
        catch(IOException ex){
        }
        
        while(true){
            try {
                incoming_sock = listen_sock.accept();
                OrdinarynodeTCPWorkingThread tcp_job = 
                        new OrdinarynodeTCPWorkingThread(incoming_sock,this.my_found_list, this.my_dl_monitor);
                answer_pool.execute(tcp_job);
            } catch (IOException ex) {
                Logger.getLogger(OrdinarynodeTCPListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
}
