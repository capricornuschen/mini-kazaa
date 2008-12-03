/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.bootstrap;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 11-set-2008
 * @file BootStrapServer.java
 */
public class BootStrapServer implements BootStrapServerInterface {

    
    private ArrayList<NodeInfo> node_list;
    private BootStrapGui g;

    public BootStrapServer(BootStrapGui g) {
        this.node_list = new ArrayList();
        
        this.g = g;

    }

    public synchronized boolean addSuperNode(NodeInfo new_node) throws RemoteException {
        System.out.println("Adding new SuperNode.");


        try {
            this.node_list.add(new_node);

            g.setWhatAppensLine(new_node.getId() + " added.\n");

            //Notify every client
            doCallbacksForAdd(new_node);

        } catch (IOException ex) {
            System.out.println("addSuperNode: " + ex);
        }

        return true;


    }

    public synchronized boolean addOrdinaryNode(NodeInfo new_node) throws RemoteException {
        System.out.println("Adding new OrdinaryNode.");


        this.node_list.add(new_node);

        g.setWhatAppensLine("ON: " + new_node.getId() + " added.\n");

        return true;
    }

    public synchronized boolean removeSuperNode(NodeInfo new_node) throws RemoteException {
        System.out.println("Removing new SuperNode.");
        if (this.node_list.remove(new_node)) {

            try {
                g.setWhatAppensLine(new_node.getId() + " removed.\n");

                //Notify every client
                doCallbacksForRemove(new_node);

            } catch (IOException ex) {
                System.out.println("removeSuperNode: " + ex);
            }

            return true;
        }

        return false;
    }

    public synchronized boolean removeOrdinaryNode(NodeInfo new_node) throws RemoteException {

        if(this.node_list.remove(new_node)){
            g.setWhatAppensLine("ON: "+new_node.getId() + " removed.\n");
            return true;
        }
        else
            return false;
        
    }

    public synchronized ArrayList<NodeInfo> getSuperNodeList() throws RemoteException {
        System.out.println("Returning list of NodeInfo.");
        
       g.setWhatAppensLine("List returned\n");

        return node_list;
    }

    private synchronized void doCallbacksForAdd(NodeInfo node) throws RemoteException {
        System.out.println("Starting callbacks.");
        System.out.println("Numer of nodes: " + node_list.size());
        if (node_list.size() <= 1) {
            return;
        }

        Iterator i = node_list.iterator();

        while (i.hasNext()) {
            NodeInfo n = (NodeInfo) i.next();
            System.out.println("Node tryed to notified: " + n.getId());
            //Scroll all the list and check if it is not the new added node.
            //if(!n.getId().equals(node.getId())){ //To add again in final version.
            System.out.println("Node notified.");
            n.getCallbackInterface().notifyMeAdd(node);
        //}
        }

    }

    private synchronized void doCallbacksForRemove(NodeInfo node) throws RemoteException {
        System.out.println("Starting callbacks.");

        if (node_list.size() <= 1) {
            return;
        }

        Iterator i = node_list.iterator();

        while (i.hasNext()) {
            NodeInfo n = (NodeInfo) i.next();
            System.out.println("Node tryed to notified: " + n.getId());
            //Scroll all the list and check if it is not the new added node.
            if (!n.getId().equals(node.getId())) {
                System.out.println("Node notified: " + n.getId());
                n.getCallbackInterface().notifyMeRemove(node);
            }
        }
    }
}
