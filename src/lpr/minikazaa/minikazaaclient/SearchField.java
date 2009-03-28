/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient;

import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.util.MKFileDescriptor;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 10-dic-2008
 * @file SearchField.java
 */
public class SearchField {
    private NodeInfo owner;
    private MKFileDescriptor file;
    
    public SearchField(MKFileDescriptor first_file, NodeInfo first_owner){
        this.owner = first_owner;
        this.file = first_file;
    }

    public NodeInfo getOwners(){return this.owner;}
    public MKFileDescriptor getFile(){return this.file;}


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
