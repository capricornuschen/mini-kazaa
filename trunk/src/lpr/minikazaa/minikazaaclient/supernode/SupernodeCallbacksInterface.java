/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient.supernode;

import java.rmi.Remote;
import java.rmi.RemoteException;
import lpr.minikazaa.bootstrap.NodeInfo;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 18-set-2008
 * @file SupernodeCallbacksInterface.java
 */
public interface SupernodeCallbacksInterface extends Remote{
    public void notifyMeAdd(NodeInfo new_node) throws RemoteException;
    public void notifyMeRemove(NodeInfo new_node) throws RemoteException;
}
