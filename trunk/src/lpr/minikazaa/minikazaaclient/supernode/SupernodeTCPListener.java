package lpr.minikazaa.minikazaaclient.supernode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import lpr.minikazaa.minikazaaclient.MainGui;
import lpr.minikazaa.minikazaaclient.NodeConfig;
import lpr.minikazaa.minikazaaclient.SupernodeList;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 23-set-2008
 * @file SupernodeTCPListener.java
 */
public class SupernodeTCPListener implements Runnable{
    
    NodeConfig my_conf;
    SupernodeList my_list;
    SupernodeOnFileList on_files;
    MainGui my_gui;
    
    public SupernodeTCPListener(
            NodeConfig conf, 
            SupernodeList list, 
            SupernodeOnFileList file_list,
            MainGui gui){
        this.on_files = file_list;
        this.my_conf = conf;
        this.my_list = list;
        this.my_gui = gui;
    }
    
    public void run(){
        //Server that liste all TCP requests.
        ServerSocket listen_sock = null;
        Socket client_socket = null;
        SupernodeQueryList query_list = new SupernodeQueryList();
        ThreadPoolExecutor answer_pool = new ThreadPoolExecutor
                (10,15,50000L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue <Runnable>());
        try{
            listen_sock = new ServerSocket(this.my_conf.getPort());
        }
        catch(IOException ex){
        }
        
        while(true){
            try {
                client_socket = listen_sock.accept();
                SupernodeTCPWorkingThread answer = new SupernodeTCPWorkingThread
                        (client_socket,this.my_conf, this.my_list, 
                        query_list, this.on_files, this.my_gui);
                answer_pool.execute(answer);
            } catch (IOException ex) {
                Logger.getLogger(SupernodeTCPListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

}
