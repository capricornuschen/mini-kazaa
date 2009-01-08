/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient.supernode;

import java.rmi.RemoteException;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.NodeConfig;
import lpr.minikazaa.minikazaaclient.SupernodeList;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 19-set-2008
 * @file SupernodeCallbacksImpl.java
 */
public class SupernodeCallbacksImpl implements SupernodeCallbacksInterface{
    
    SupernodeList my_sn_list;
    NodeConfig my_conf;
    
    public SupernodeCallbacksImpl(SupernodeList list, NodeConfig conf){
        this.my_conf = conf;
        this.my_sn_list = list;
    }
    
    public void notifyMeAdd(NodeInfo new_node) throws RemoteException{
        System.out.println("NotifyMeAdd received.");
        this.my_sn_list.addNewNode(new_node);
        this.my_sn_list.print();
    }
    
    public void notifyMeRemove(NodeInfo old_node) throws RemoteException{
        System.out.println("NotifyMeRemove received.");
        this.my_sn_list.removeOldNode(old_node);
    }

   
}
