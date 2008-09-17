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
    
    public boolean addSuperNode(NodeInfo new_node) throws RemoteException{
        System.out.println("Adding new SuperNode.");
        if(this.super_node_list.add(new_node)){
            File log = new File("./log.txt");
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(log));
                Date d = new Date();
                writer.println(d.toString()+" : "+new_node.getId()+" added.");
                g.setWhatAppensLine(new_node.getId()+" added.");
            } catch (IOException ex) {
                System.out.println("addSuperNode: "+ex);
            }
            
            return true;
        }
        return false;
    }
    public boolean removeSuperNode(NodeInfo new_node) throws RemoteException{
        System.out.println("Removing new SuperNode.");
        if(this.super_node_list.remove(new_node)){
            File log = new File("./log.txt");
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(log));
                Date d = new Date();
                writer.println(d.toString()+" : "+new_node.getId()+" removed.");
                g.setWhatAppensLine(new_node.getId()+" removed.");
            } catch (IOException ex) {
                System.out.println("removeSuperNode: "+ex);
            }
            
            return true;
        }
        
        return false;
    }
    
    public ArrayList<NodeInfo>  getSuperNodeList() throws RemoteException{
        System.out.println("Returning list of NodeInfo.");
        File log = new File("./log.txt");
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(log));
                Date d = new Date();
                writer.println(d.toString()+" : list returned.");
                g.setWhatAppensLine("List returned");
            } catch (IOException ex) {
                System.out.println("removeSuperNode: "+ex);
            }
        return super_node_list;
    }
}
