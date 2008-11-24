

package lpr.minikazaa.bootstrap;

import java.io.Serializable;
import java.net.InetAddress;
import lpr.minikazaa.minikazaaclient.supernode.SupernodeCallbacksInterface;

/**
 * Da commentare.
 * 
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 11-set-2008
 * @file NodeInfo.java
 */
public class NodeInfo implements Serializable {
    
    private InetAddress ia_node;
    private int door;
    private String id_node;
    private String username; //Useless mnemonic username
    private SupernodeCallbacksInterface stub;
    private long ping;
    
    private int my_connection;
    private int my_files;
    
    public NodeInfo(InetAddress ia_node, int door, SupernodeCallbacksInterface callback){
        this.ia_node = ia_node;
        this.door = door;
        this.id_node = ia_node.getHostAddress()+":"+door;
        this.stub = callback;
        this.ping = -1;
        
        this.my_connection = 0;
        this.my_files = 0;
    }
    
    public InetAddress getIaNode(){
        return this.ia_node;
    }
    
    public int getDoor(){
        return this.door;
    }
    
    public String getId(){
        return this.id_node;
    }
    
    public SupernodeCallbacksInterface getCallbackInterface(){
        return this.stub;
    }
    
    public long getPing(){
        return this.ping;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    
    public void addFiles(int n_new_files){
        this.my_files = this.my_files + n_new_files;
    }
    public void removeFiles(int n_old_files){
        this.my_files = this.my_files - n_old_files;
    }
    
    
    public void addConnection(){
        this.my_connection ++;
    }
    public void removeConnection(){
        this.my_connection --;
    }
    
    //Set methods
    public void setPing(long fresh_ping){
        this.ping = fresh_ping;
    }
    public void setUsername(String name){
        this.username = name;
    }
    
    @Override
    public String toString(){
        return this.ia_node.toString();
    }
    
    public String toTable(){
        return ""+this.ia_node.toString()+"\n"+this.my_files+"\n"+this.my_connection+"\n";
    }
    
    public boolean theSame(NodeInfo n){
        if(this.id_node.equals(n.id_node))
            return true;
        else
            return false;
    }
}
