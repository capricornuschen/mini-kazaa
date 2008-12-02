/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.minikazaaclient.supernode;

import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.MainGui;
import lpr.minikazaa.minikazaaclient.NodeConfig;
import lpr.minikazaa.minikazaaclient.NodePong;
import lpr.minikazaa.minikazaaclient.SupernodeList;
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeFiles;
import lpr.minikazaa.util.FileUtil;


/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 17-set-2008
 * @file SupernodeEngine.java
 */
public class SupernodeEngine implements Runnable {

    NodeConfig my_conf;
    
    public SupernodeEngine(NodeConfig conf) {
        this.my_conf = conf;
    }

    public void run() {
        System.out.println("Thread Super node engine init.");
        NodeInfo my_infos = null;
        SupernodeList sn_list = new SupernodeList();
        SupernodeOnFileList on_files = new SupernodeOnFileList();
        OrdinarynodeFiles my_file_list = FileUtil.loadMySharedFiles(my_infos);
        
        //Init main GUI of supernode
        MainGui main_gui = new MainGui(this.my_conf, my_file_list);
        main_gui.setLocationRelativeTo(null);
        main_gui.setVisible(true);
        
              
        //Init ping service to receive pings
        NodePong pong = new NodePong(this.my_conf);
        Thread ping_service = new Thread(pong);
        ping_service.start();
        
        //Init RMI manager Thread.
        SupernodeRMIManager sn_rmi = new SupernodeRMIManager(my_conf,sn_list);
        Thread rmi_manager = new Thread(sn_rmi);
        rmi_manager.start();
        
        //Init TCP requests manager
        SupernodeTCPListener listener_tcp = new SupernodeTCPListener(this.my_conf,sn_list,on_files);
        Thread tcp_listen = new Thread(listener_tcp);
        tcp_listen.start();
        
        
        
        
    }
}
