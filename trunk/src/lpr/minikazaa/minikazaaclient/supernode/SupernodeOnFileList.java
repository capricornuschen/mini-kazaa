
package lpr.minikazaa.minikazaaclient.supernode;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeFiles;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 14-ott-2008
 * @file SupernodeOnFileList.java
 */
public class SupernodeOnFileList {
    private ArrayList <OrdinarynodeFiles> file_list;
    
    private SupernodeGuiEngine sn_gui_engine;
    
    public SupernodeOnFileList(){
        this.file_list = null;
        this.sn_gui_engine = null;
    }
    
    public boolean alreadyHave(OrdinarynodeFiles f){
        
        for(OrdinarynodeFiles o : this.file_list){
            if(f.getOwner().getId().equals(o.getOwner().getId())){
                //Owner found, it is already in list.
                return true;
            }
        }
        
        return false;
    }
    
    public void addNewOnFileList(OrdinarynodeFiles new_file_list){
        //Check if we already have it in our data struct.
        
        for(OrdinarynodeFiles o : this.file_list){
            if(o.getOwner().getId().equals(new_file_list.getOwner().getId())){
                //We already have a list for this owner.
                o.resetList(new_file_list.getFileList());
                return;
            }
        }
        
        this.file_list.add(new_file_list);
        
        
    }
    
    public ArrayList <OrdinarynodeFiles> searchFiles(String regex){
        ArrayList <OrdinarynodeFiles> l = new ArrayList();
        
        Pattern pattern = Pattern.compile(regex);
        for(OrdinarynodeFiles files : this.file_list){
            
            ArrayList <File[]> node_files = files.getFileList();
            OrdinarynodeFiles files_found = new OrdinarynodeFiles(files.getOwner());
            File [] new_array = null;
            
            for(File[] arr_files : node_files){
                
                ArrayList <File> found_list = new ArrayList();
                
                for(int i = 0; i< arr_files.length; i++){
                    Matcher matcher = pattern.matcher(arr_files[i].getName());
                    
                    while(matcher.find()){
                        found_list.add(arr_files[i]);
                    }
                }
                
                 new_array = (File [])found_list.toArray();
            }
            files_found.addFiles(new_array);
        }
        
        return l;
    }
    
    public void removeFiles(NodeInfo n){
        int i = 0;
        for(OrdinarynodeFiles f : this.file_list){
            if(f.getOwner().getId().equals(n))
            {
                this.file_list.remove(i);
                return;
            }
            i++;
        }
    }
    
    public void setGuiEngine(SupernodeGuiEngine sge){this.sn_gui_engine = sge;}
}
