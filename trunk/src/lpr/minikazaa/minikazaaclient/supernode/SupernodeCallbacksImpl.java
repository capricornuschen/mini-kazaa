/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient.supernode;

import java.rmi.RemoteException;
import lpr.minikazaa.bootstrap.NodeInfo;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 19-set-2008
 * @file SupernodeCallbacksImpl.java
 */
public class SupernodeCallbacksImpl {
    public SupernodeCallbacksImpl(){}
    
    public void notifyMe(NodeInfo new_node) throws RemoteException{
        
    }
    
    public void notifyMeRemove(NodeInfo old_node) throws RemoteException{
    
    }
}
