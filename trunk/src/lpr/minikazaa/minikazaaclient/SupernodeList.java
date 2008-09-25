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
    
    public SupernodeList(){}
    
    public synchronized void refreshList( ArrayList <NodeInfo> list){
        this.sn_list = list;
    }
    
    public synchronized void addNewNode( NodeInfo node){
        this.sn_list.add(node);
    }
    
    public synchronized void removeOldNode( NodeInfo node){
        this.sn_list.remove(node);
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
    }
    
    public synchronized ArrayList <NodeInfo> subSet(int set_size){
        
        
        return null; //to be checked.
    }
    //Check point is modified
    public synchronized void isModified(){
        this.is_updated = true;
    }
    public synchronized boolean getStatus(){
        return this.is_updated;
    }
}
