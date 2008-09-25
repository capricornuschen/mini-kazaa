/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.bootstrap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 11-set-2008
 * @file BootStrapServer.java
 */
public class BootStrapServer implements BootStrapServerInterface{
    
    private ArrayList <NodeInfo> super_node_list;
    private BootStrapGui g;
        
    public BootStrapServer(BootStrapGui g){
        this.super_node_list = new ArrayList();
        this.g = g;
        
    }
    
    public synchronized boolean addSuperNode(NodeInfo new_node) throws RemoteException{
        System.out.println("Adding new SuperNode.");
        if(this.super_node_list.add(new_node)){
            File log = new File("./log.txt");
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(log));
                Date d = new Date();
                writer.println(d.toString()+" : "+new_node.getId()+" added.");
                g.setWhatAppensLine(new_node.getId()+" added.\n");
                
                //Notify every client
                doCallbacksForAdd(new_node);
                
            } catch (IOException ex) {
                System.out.println("addSuperNode: "+ex);
            }
            
            return true;
        }
        return false;
    }
    public synchronized boolean removeSuperNode(NodeInfo new_node) throws RemoteException{
        System.out.println("Removing new SuperNode.");
        if(this.super_node_list.remove(new_node)){
            File log = new File("./log.txt");
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(log));
                Date d = new Date();
                writer.println(d.toString()+" : "+new_node.getId()+" removed.");
                g.setWhatAppensLine(new_node.getId()+" removed.\n");
                
                //Notify every client
                doCallbacksForRemove(new_node);
                    
            } catch (IOException ex) {
                System.out.println("removeSuperNode: "+ex);
            }
            
            return true;
        }
        
        return false;
    }
    
    public synchronized ArrayList<NodeInfo>  getSuperNodeList() throws RemoteException{
        System.out.println("Returning list of NodeInfo.");
        File log = new File("./log.txt");
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(log));
                Date d = new Date();
                writer.println(d.toString()+" : list returned.");
                g.setWhatAppensLine("List returned\n");
                                
            } catch (IOException ex) {
                System.out.println("Send Supernode List: "+ex);
            }
        return super_node_list;
    }
    
    private synchronized void doCallbacksForAdd (NodeInfo node) throws RemoteException{
        System.out.println("Starting callbacks.");
        
        if(super_node_list.size() <= 1){return;}
        
        Iterator i = super_node_list.iterator();
        
        while(i.hasNext()){
            NodeInfo n = (NodeInfo) i.next();
            //Scroll all the list and check if it is not the new added node.
            if(!n.getId().equals(node.getId())){
                n.getCallbackInterface().notifyMeAdd(node);
            }
        }
        
    }
    
    private synchronized void doCallbacksForRemove (NodeInfo node) throws RemoteException{
        System.out.println("Starting callbacks.");
        
        if(super_node_list.size() <= 1){return;}
        
        Iterator i = super_node_list.iterator();
        
        while(i.hasNext()){
            NodeInfo n = (NodeInfo) i.next();
            //Scroll all the list and check if it is not the new added node.
            if(!n.getId().equals(node.getId())){
                n.getCallbackInterface().notifyMeRemove(node);
            }
        }
    }
}
