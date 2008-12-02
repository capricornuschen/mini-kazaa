/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient.ordinarynode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.util.MKFileDescriptor;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 9-ott-2008
 * @file OrdinarynodeFiles.java
 */
public class OrdinarynodeFiles extends Observable implements Serializable {
    private ArrayList <MKFileDescriptor> file_list;
    private NodeInfo my_info;
    
    public OrdinarynodeFiles(NodeInfo infos){
        this.my_info = infos;
        this.file_list = new ArrayList();
    }
    
    public void addFiles(MKFileDescriptor [] new_files){
        for(int i = 0; i< new_files.length; i++){
            
            if(!isIn(new_files[i]))
                this.file_list.add(new_files[i]);
        }
        this.setChanged();
        this.notifyObservers();
    }

    public void removeFiles(MKFileDescriptor [] old_files){
        
    }
    
    //Get methods
    public ArrayList<MKFileDescriptor> getFileList(){
        return file_list;
    }
    public NodeInfo getOwner(){
        return this.my_info;
    }

    public boolean isIn(MKFileDescriptor f){
        for(MKFileDescriptor file : this.file_list){
            if((f.getFileName().equals(file.getFileName())) &&
               f.getMd5().equals(file.getMd5()) &&
               f.getPath().equals(file.getPath()) &&
               f.getSize() == file.getSize()
               )
                return true;
        }
        return false;
    }

    public boolean isEmpty(){
        if(this.file_list.size() == 0)
            return true;
        else
            return false;
    }

    public void resetList(ArrayList <MKFileDescriptor> list){
        this.file_list = list;
    }
    
}
