/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient;

import java.util.ArrayList;
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
    
    //Check point is modified
    public synchronized void isModified(){
        this.is_updated = true;
    }
    public synchronized boolean getStatus(){
        return this.is_updated;
    }
}
