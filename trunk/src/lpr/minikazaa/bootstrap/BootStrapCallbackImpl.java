/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.bootstrap;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
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
    BootStrapGui bs_gui;
    
    public BootStrapCallbackImpl(BootStrapGui gui){
        super();
        this.client_list = new ArrayList <SupernodeCallbacksInterface>();
        this.bs_gui= gui;
    }
    
    public synchronized void registerForCallback(SupernodeCallbacksInterface client_callback) throws RemoteException{
        if(!client_list.contains(client_callback)){
            client_list.add(client_callback);
            System.out.println("Client successfuly added for callbacks.");
            this.bs_gui.setWhatAppensLine(client_callback.toString()+" added.");
            
            //write log file
        }
    }
    
    public synchronized void unregisterForCallback(SupernodeCallbacksInterface client_callback) throws RemoteException{
        if(client_list.remove(client_callback)){
            System.out.println("Client unregistered.");
            this.bs_gui.setWhatAppensLine(client_callback.toString()+" unregistered.");
            //Write log file
        }
        else{
            System.out.println("Unable to unregister client.");
            this.bs_gui.setWhatAppensLine("Unable to unregister "+client_callback.toString()+".");
            
            //Write log file.
        }
    }
    
    private synchronized void doCallbacks(){
        System.out.println("Starting callbacks.");
        
        Iterator i = client_list.iterator();
        
        while(i.hasNext()){
            SupernodeCallbacksInterface client = (SupernodeCallbacksInterface) i.next();
            //client.notifyMe( nodeinfo  );
        }
        
    }
}
