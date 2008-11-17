/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient.ordinarynode;

import lpr.minikazaa.minikazaaclient.NodeConfig;

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
        OrdinarynodeQuestionsList found_list = new OrdinarynodeQuestionsList();
        
        //Init TCP listener
        OrdinarynodeTCPListener ontcp = new OrdinarynodeTCPListener(this.my_conf,found_list);
        Thread tcp_thread = new Thread(ontcp);
        tcp_thread.start();
        
    }

}
