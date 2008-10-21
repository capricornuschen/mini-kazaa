/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient;

import java.io.Serializable;
import java.util.ArrayList;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeFiles;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 3-ott-2008
 * @file Answer.java
 */
public class Answer implements Serializable {
    private ArrayList <OrdinarynodeFiles> files;
    
    
    public Answer(){}
    public Answer(ArrayList <OrdinarynodeFiles> owner_list){
        this.files = owner_list;
    }
   
    public Answer(ArrayList <OrdinarynodeFiles> owner_list, NodeInfo ow_info){
        this.files = owner_list;
        
    }
    
    public void setFilesList(ArrayList <OrdinarynodeFiles> l){
        this.files = l;
    }
       
    public ArrayList <OrdinarynodeFiles> getFilesList(){
        return this.files;
    }
    
}
