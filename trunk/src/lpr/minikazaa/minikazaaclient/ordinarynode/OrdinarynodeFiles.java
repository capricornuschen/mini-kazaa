/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient.ordinarynode;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import lpr.minikazaa.bootstrap.NodeInfo;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 9-ott-2008
 * @file OrdinarynodeFiles.java
 */
public class OrdinarynodeFiles implements Serializable{
    private ArrayList <File []> file_list;
    private NodeInfo my_info;
    
    public OrdinarynodeFiles(NodeInfo infos){
        this.my_info = infos;
    }
    
    public void addFiles(File [] new_files){
        this.file_list.add(new_files);
    }
    
    public ArrayList<File []> getFileList(){
        return file_list;
    }
    
    /**
     * This method simply checks if a file, identified by a string (s), 
     * appears in list file_list.
     * 
     * @param s
     * @return true if file s is in list, false otherwise.
     */
    public boolean isIn(String s){
        for(File[] arr_f : file_list){
            for(int i = 0; i< arr_f.length; i++){
                if(s.equals(arr_f[i].getName())){
                    return true;
                }
            }
        }
        return false;
    }
}
