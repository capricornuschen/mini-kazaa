/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.bootstrap;

import java.rmi.Remote;
import java.rmi.RemoteException;
import lpr.minikazaa.minikazaaclient.supernode.SupernodeCallbacksInterface;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 19-set-2008
 * @file BootStrapCallbackInterface.java
 */
public interface BootStrapCallbackInterface extends Remote{
    public void registerForCallback(SupernodeCallbacksInterface client_callback) throws RemoteException;
    public void unregisterForCallback(SupernodeCallbacksInterface client_callback) throws RemoteException;
}
