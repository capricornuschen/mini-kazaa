/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.bootstrap;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import lpr.minikazaa.minikazaaclient.SupernodeList;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 11-set-2008
 * @file BootStrapServer.java
 */
public class BootStrapServer implements BootStrapServerInterface {

    
    private SupernodeList supernode_list;
    private SupernodeList ordinarynode_list;
    private BootStrapGui g;

    public BootStrapServer(BootStrapGui g, SupernodeList sn_list, SupernodeList on_list) {
        this.supernode_list = sn_list;
        this.ordinarynode_list = on_list;
        this.g = g;

    }

    public synchronized boolean addSuperNode(NodeInfo new_node) throws RemoteException {
        System.out.println("Adding new SuperNode.");


        try {
            this.supernode_list.addNewNode(new_node);

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


        this.ordinarynode_list.addNewNode(new_node);

        g.setWhatAppensLine("ON: " + new_node.getId() + " added.\n");

        return true;
    }

    public synchronized boolean removeSuperNode(NodeInfo new_node) throws RemoteException {
        System.out.println("Removing new SuperNode "+new_node.getId());

        this.supernode_list.removeOldNode(new_node);

            try {
                g.setWhatAppensLine(new_node.getId() + " removed.\n");

                //Notify every client
                doCallbacksForRemove(new_node);

            } catch (IOException ex) {
                System.out.println("removeSuperNode: " + ex);
            }

       return true;
    }

    public synchronized boolean removeOrdinaryNode(NodeInfo new_node) throws RemoteException {

        this.ordinarynode_list.removeOldNode(new_node);
            g.setWhatAppensLine("ON: "+new_node.getId() + " removed.\n");
            return true;
        
        
    }

    public synchronized ArrayList<NodeInfo> getSuperNodeList() throws RemoteException {
        System.out.println("Returning list of NodeInfo.");
        
       g.setWhatAppensLine("List returned\n");

        return supernode_list.getList();
    }

    private synchronized void doCallbacksForAdd(NodeInfo node) throws RemoteException {
        System.out.println("Starting callbacks.");
        ArrayList <NodeInfo> sn_list = this.supernode_list.getList();
        System.out.println("Numer of nodes: " + sn_list.size());

        if (sn_list.size() <= 1) {
            return;
        }

        Iterator i = sn_list.iterator();

        while (i.hasNext()) {
            NodeInfo n = (NodeInfo) i.next();
            System.out.println("Node tryed to notified: " + n.getId());
            //Scroll all the list and check if it is not the new added node.
            if(!n.getId().equals(node.getId())){ //To add again in final version.
                System.out.println("Node notified.");
                n.getCallbackInterface().notifyMeAdd(node);
            }
        }

        ArrayList <NodeInfo> on_list = this.ordinarynode_list.getList();
        System.out.println("Number of nodes: "+ on_list.size());

        if(on_list.size() <= 1)
            return;

        Iterator j = on_list.iterator();
        while(j.hasNext()){
            NodeInfo n = (NodeInfo) j.next();
            System.out.println("Node tryed to notified: " + n.getId());
            System.out.println("Node notified.");
            n.getCallbackInterface().notifyMeAdd(node);
        }

    }

    private synchronized void doCallbacksForRemove(NodeInfo node) throws RemoteException {
        System.out.println("Starting callbacks.");
        ArrayList <NodeInfo> sn_list = this.supernode_list.getList();
        
        if (sn_list.size() == 0) {
            return;
        }

        Iterator i = sn_list.iterator();

        while (i.hasNext()) {
            NodeInfo n = (NodeInfo) i.next();
            System.out.println("Node tryed to notified: " + n.getId());
            //Scroll all the list and check if it is not the new added node.
            if (!n.getId().equals(node.getId())) {
                System.out.println("Node notified: " + n.getId());
                n.getCallbackInterface().notifyMeRemove(node);
            }
        }

        ArrayList <NodeInfo> on_list = this.ordinarynode_list.getList();
        System.out.println("Number of nodes: "+ on_list.size());

        if(on_list.size() <= 1)
            return;

        Iterator j = on_list.iterator();
        while(j.hasNext()){
            NodeInfo n = (NodeInfo) j.next();
            System.out.println("Node tryed to notified: " + n.getId());
            System.out.println("Node notified.");
            n.getCallbackInterface().notifyMeRemove(node);
        }
    }
}
