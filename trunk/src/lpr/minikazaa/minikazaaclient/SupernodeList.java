/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lpr.minikazaa.bootstrap.NodeInfo;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 19-set-2008
 * @file SupernodeList.java
 */
public class SupernodeList {
    
   private ArrayList <NodeInfo> sn_list;
   private boolean is_updated;
    
    public SupernodeList(){
        this.sn_list = new ArrayList();
        this.is_updated = false;
    }
    
    public synchronized void refreshList( ArrayList <NodeInfo> list){
        this.sn_list = list;
        this.is_updated = true;
    }
    
    public synchronized void addNewNode( NodeInfo node){
        this.sn_list.add(node);
        this.is_updated = true;
    }
    
    public synchronized void removeOldNode( NodeInfo node){
        this.sn_list.remove(node);
        this.is_updated = true;
    }
    
    public synchronized ArrayList <NodeInfo> getList(){
        return this.sn_list;
    }
    
    public synchronized void refreshPing(InetAddress ia, int port, long new_ping){
        for(NodeInfo n : sn_list){
            //Now we compare the 2 toString methods, comparing 2 strings.
            if(n.getIaNode().toString().equals(ia.toString())){
                if(n.getDoor() == port){
                    n.setPing(new_ping);
                }
            }
        }
        this.is_updated = true;
    }
    
    public synchronized void refreshPing(){
        //Thread pool
        ThreadPoolExecutor my_thread_pool = 
                new ThreadPoolExecutor(10,15,50000L,TimeUnit.MILLISECONDS, 
                new LinkedBlockingQueue<Runnable>());
        
        for(NodeInfo n : sn_list){
            NodePing pinging = new NodePing(n.getIaNode(),n.getDoor(),this);
            
            my_thread_pool.execute(pinging);
        }
        
        my_thread_pool.shutdown();
        this.is_updated = true;
    }
    
    public synchronized ArrayList <NodeInfo> subSet(int set_size, long threshold){
        ArrayList <NodeInfo> neighbors = new ArrayList();
        
        for(NodeInfo n : this.sn_list){
            if(n.getPing() != -1){
                //Unactive node.
                this.is_updated = true;
                if(n.getPing() <= threshold){
                    neighbors.add(n);
                    if(neighbors.size() == set_size)
                        return neighbors;
                }
            }
        }
        
        return neighbors;
    }
    
    //Check point is modified
    public synchronized void guiUpdated(){
                
        if(is_updated = false)
            this.is_updated = true;
        else
            this.is_updated = false;
    }
    
    public synchronized boolean getStatus(){
        return this.is_updated;
    }
}
