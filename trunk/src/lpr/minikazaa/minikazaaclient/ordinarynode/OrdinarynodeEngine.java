/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient.ordinarynode;

import java.net.Socket;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.MainGui;
import lpr.minikazaa.minikazaaclient.NodeConfig;
import lpr.minikazaa.minikazaaclient.NodePong;
import lpr.minikazaa.minikazaaclient.SupernodeList;
import lpr.minikazaa.util.FileUtil;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 13-nov-2008
 * @file OrdinarynodeEngine.java
 */
public class OrdinarynodeEngine implements Runnable {
    NodeConfig my_conf;
    
    public OrdinarynodeEngine(NodeConfig conf){
        this.my_conf = conf;
    }
    public void run(){
        System.out.println("Thread Ordinary node engine init.");
        NodeInfo my_infos = null;
        NodeInfo ref_sn = null;
        SupernodeList sn_list = new SupernodeList();
        OrdinarynodeQuestionsList found_list = new OrdinarynodeQuestionsList();
        OrdinarynodeFiles my_file_list = FileUtil.loadMySharedFiles(my_infos);
        OrdinarynodeDownloadMonitor dl_monitor = new OrdinarynodeDownloadMonitor();
        
        //Init the socket connection to the our only one socket.
        Socket my_supernode = null;
        
        //Init TCP listener
        OrdinarynodeTCPListener on_tcp = new OrdinarynodeTCPListener(this.my_conf,found_list,dl_monitor);
        Thread tcp_thread = new Thread(on_tcp);
        tcp_thread.start();

        //Init main GUI of supernode
        MainGui main_gui = new MainGui(this.my_conf, my_file_list,my_supernode,found_list, sn_list,my_infos,dl_monitor);
        main_gui.setLocationRelativeTo(null);
        main_gui.setVisible(true);

        //Init RMI manager
        OrdinarynodeRMIManager on_rmi = new OrdinarynodeRMIManager(this.my_conf,my_infos,sn_list,my_supernode,ref_sn);
        Thread rmi_thread = new Thread(on_rmi);
        rmi_thread.start();

        //Init ping service to receive pings
        NodePong pong = new NodePong(this.my_conf);
        Thread ping_service = new Thread(pong);
        ping_service.start();
        
    }

}
