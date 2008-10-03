/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient;

import java.io.Serializable;
import java.util.ArrayList;
import lpr.minikazaa.bootstrap.NodeInfo;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 3-ott-2008
 * @file Answer.java
 */
public class Answer implements Serializable {
    private ArrayList <String> files;
    private NodeInfo owner;
    
    public Answer(){}
    public Answer(ArrayList <String> owner_list){
        this.files = owner_list;
    }
    public Answer(NodeInfo ow_info){
        this.owner = ow_info;
    }
    public Answer(ArrayList <String> owner_list, NodeInfo ow_info){
        this.files = owner_list;
        this.owner = ow_info;
    }
    
    public void setFilesList(ArrayList <String> l){
        this.files = l;
    }
    public void setOwner(NodeInfo n){
        this.owner = n;
    }
    
    public ArrayList <String> getFilesList(){
        return this.files;
    }
    public NodeInfo getNodeInfo(){
        return this.owner;
    }
}
