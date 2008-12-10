/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient;

import java.util.ArrayList;
import java.util.List;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.util.MKFileDescriptor;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 10-dic-2008
 * @file SearchField.java
 */
public class SearchField {
    private List <NodeInfo> owners;
    private MKFileDescriptor file; //We take the first cause they're all the same
    public SearchField(MKFileDescriptor first_file, NodeInfo first_owner){
        owners = new ArrayList();

        this.owners.add(first_owner);
        this.file = first_file;

    }

    public List <NodeInfo> getOwners(){return this.owners;}
    public MKFileDescriptor getFile(){return this.file;}

    public void addOwner(NodeInfo new_owner){
        if(!owners.contains(new_owner))
            owners.add(new_owner);
    }

    public boolean sameFile(MKFileDescriptor file){
        if(file.equals(this.file))
            return true;
        
        return false;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof SearchField){
            SearchField other = (SearchField) obj;

            if(this.file.getMd5().equals(other.file.getMd5())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.file != null ? this.file.hashCode() : 0);
        return hash;
    }
}
