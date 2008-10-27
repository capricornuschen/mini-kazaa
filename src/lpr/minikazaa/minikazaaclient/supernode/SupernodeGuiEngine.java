/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.minikazaaclient.supernode;

import lpr.minikazaa.minikazaaclient.NodeConfig;
import lpr.minikazaa.minikazaaclient.SupernodeList;
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeFiles;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 29-set-2008
 * @file SupernodeGuiEngine.java
 */
public class SupernodeGuiEngine{

    SupernodeMainGui main_gui;
    NodeConfig my_conf;
    SupernodeList my_list;

    public SupernodeGuiEngine(SupernodeMainGui gui, NodeConfig conf, SupernodeList list) {
        this.main_gui = gui;
        this.my_conf = conf;
        this.my_list = list;
    }
    
    public synchronized void updateOnFiles(OrdinarynodeFiles f){
        
    }
    
}
