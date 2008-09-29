/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient.supernode;

import java.util.ArrayList;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.NodeConfig;
import lpr.minikazaa.minikazaaclient.SupernodeList;



/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 29-set-2008
 * @file SupernodeGuiEngine.java
 */
public class SupernodeGuiEngine implements Runnable{
    
    SupernodeMainGui main_gui;
    NodeConfig my_conf;
    SupernodeList my_list;
    
    public SupernodeGuiEngine
            (SupernodeMainGui gui,NodeConfig conf, SupernodeList list){
        this.main_gui = gui;
        this.my_conf = conf;
        this.my_list = list;
    }
    
    public void run(){
        //Do nothing untill list is unupdated.
        while(my_list.getStatus() == false){}
        
        ArrayList <NodeInfo> sub_set_list = my_list.subSet(my_conf.getMaxConnection(), 100);
        //Show subset of supernode
        for(NodeInfo n : sub_set_list){
            this.main_gui.addSuperNodeTableRow(n);
        }
    }
}
