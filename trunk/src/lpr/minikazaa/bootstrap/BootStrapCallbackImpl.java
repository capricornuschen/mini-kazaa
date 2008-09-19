/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.bootstrap;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import lpr.minikazaa.minikazaaclient.supernode.SupernodeCallbacksInterface;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 19-set-2008
 * @file BootStrapCallbackImpl.java
 */
public class BootStrapCallbackImpl implements BootStrapCallbackInterface{
    
    List <SupernodeCallbacksInterface> client_list;
    
    public BootStrapCallbackImpl(){
        super();
        client_list = new ArrayList <SupernodeCallbacksInterface>();
    }
    
    public synchronized void registerForCallback(SupernodeCallbacksInterface client_callback) throws RemoteException{
        
    }
    
    public synchronized void unregisterForCallback(SupernodeCallbacksInterface client_callback) throws RemoteException{
        
    }
    
    private synchronized void doCallbacks(){
        
        
    }
}
