package lpr.minikazaa.minikazaaclient.supernode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import lpr.minikazaa.minikazaaclient.NodeConfig;
import lpr.minikazaa.minikazaaclient.SupernodeList;
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeDownloadMonitor;
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeFiles;
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeQuestionsList;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 23-set-2008
 * @file SupernodeTCPListener.java
 */
public class SupernodeTCPListener implements Runnable {

    private NodeConfig my_conf;
    private SupernodeList my_list;
    private SupernodeOnFileList on_files;
    private OrdinarynodeFiles my_files;
    private OrdinarynodeDownloadMonitor my_dl_monitor;
    private OrdinarynodeQuestionsList my_found_list;

    public SupernodeTCPListener(
            NodeConfig conf,
            SupernodeList list,
            SupernodeOnFileList file_list,
            OrdinarynodeFiles sn_files,
            OrdinarynodeDownloadMonitor dl_monitor,
            OrdinarynodeQuestionsList found_list) {
        this.on_files = file_list;
        this.my_conf = conf;
        this.my_list = list;
        this.my_files = sn_files;
        this.my_dl_monitor = dl_monitor;
        this.my_found_list = found_list;
    }

    public void run() {
        //Server that liste all TCP requests.
        ServerSocket listen_sock = null;
        Socket client_socket = null;
        SupernodeQueryList query_list = new SupernodeQueryList();
        ThreadPoolExecutor answer_pool = 
                new ThreadPoolExecutor(
                10,
                this.my_conf.getMaxConnection(),
                50000L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        try {
            listen_sock = new ServerSocket(this.my_conf.getPort());
        } catch (IOException ex) {
        }

        while (true) {
            try {
                client_socket = listen_sock.accept();
                SupernodeTCPWorkingThread answer = 
                        new SupernodeTCPWorkingThread(
                        client_socket,
                        this.my_conf,
                        this.my_list,
                        query_list, 
                        this.on_files,
                        this.my_files,
                        this.my_dl_monitor,
                        this.my_found_list);
                answer_pool.execute(answer);
            } catch (IOException ex) {
                Logger.getLogger(SupernodeTCPListener.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
