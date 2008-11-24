/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient.ordinarynode;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.util.MKFileDescriptor;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 9-ott-2008
 * @file OrdinarynodeFiles.java
 */
public class OrdinarynodeFiles implements Serializable{
    private ArrayList <MKFileDescriptor []> file_list;
    private NodeInfo my_info;
    
    public OrdinarynodeFiles(NodeInfo infos){
        this.my_info = infos;
    }
    
    public void addFiles(MKFileDescriptor [] new_files){
        this.file_list.add(new_files);
    }
    
    //Get methods
    public ArrayList<MKFileDescriptor []> getFileList(){
        return file_list;
    }
    public NodeInfo getOwner(){
        return this.my_info;
    }
    /**
     * This method simply checks if a file, identified by a string (s), 
     * appears in list file_list.
     * 
     * @param s
     * @return true if file s is in list, false otherwise.
     */
    public boolean isIn(String s){
        for(MKFileDescriptor[] arr_f : file_list){
            for(int i = 0; i< arr_f.length; i++){
                if(/*s.equals(arr_f[i].getName())*/true){
                    return true;
                }
            }
        }
        return false;
    }
    
    public void resetList(ArrayList <MKFileDescriptor[]> l){
        this.file_list = l;
    }
    
    
}
