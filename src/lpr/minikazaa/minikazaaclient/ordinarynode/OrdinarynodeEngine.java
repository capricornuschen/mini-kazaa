/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient.ordinarynode;

import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.NodeConfig;
import lpr.minikazaa.minikazaaclient.SupernodeList;

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
        OrdinarynodeQuestionsList found_list = new OrdinarynodeQuestionsList();
        NodeInfo my_infos = null;
        SupernodeList sn_list = new SupernodeList();
        
        //Init TCP listener
        OrdinarynodeTCPListener on_tcp = new OrdinarynodeTCPListener(this.my_conf,found_list);
        Thread tcp_thread = new Thread(on_tcp);
        tcp_thread.start();

        //Init RMI manager
        OrdinarynodeRMIManager on_rmi = new OrdinarynodeRMIManager(this.my_conf,my_infos,sn_list);
        Thread rmi_thread = new Thread(on_rmi);
        rmi_thread.start();
        
    }

}
