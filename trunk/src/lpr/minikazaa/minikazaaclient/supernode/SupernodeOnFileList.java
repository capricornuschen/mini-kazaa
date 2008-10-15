
package lpr.minikazaa.minikazaaclient.supernode;

import java.util.ArrayList;
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeFiles;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 14-ott-2008
 * @file SupernodeOnFileList.java
 */
public class SupernodeOnFileList {
    private ArrayList <OrdinarynodeFiles> file_list;
    
    public SupernodeOnFileList(){
        this.file_list = null;
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
}
