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
    private ArrayList <OrdinarynodeFiles> files;    //List of files that corresponding to a query.
    private int id;                                 //Id of origin query
    
    public Answer(int n){this.id = n;}
    public Answer(ArrayList <OrdinarynodeFiles> owner_list,int n){
        this.files = owner_list;
        this.id = n;
    }
 
    
    public void setFilesList(ArrayList <OrdinarynodeFiles> l){
        this.files = l;
    }
       
    public ArrayList <OrdinarynodeFiles> getFilesList(){
        return this.files;
    }
    
    public int getID(){return this.id;}

    @Override
    public boolean equals(Object obj){
        
        //To do yet.
        if(obj instanceof Answer)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (this.files != null ? this.files.hashCode() : 0);
        hash = 11 * hash + this.id;
        return hash;
    }
    
}
