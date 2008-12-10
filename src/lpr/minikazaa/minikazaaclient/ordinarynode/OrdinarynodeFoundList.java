
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.minikazaaclient.ordinarynode;

import java.util.ArrayList;
import java.util.Observable;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.Answer;
import lpr.minikazaa.minikazaaclient.SearchField;
import lpr.minikazaa.util.MKFileDescriptor;

/**
 * Class that lists the answers from super nodes with the shearched files.
 * 
 * @author Andrea Di Grazia, Massimiliano Giovine
 */
public class OrdinarynodeFoundList extends Observable {
    private int id;
    private ArrayList<SearchField> found;
    
    public OrdinarynodeFoundList(int n) {
        this.id = n;
        found = new ArrayList();
    }

    public void add(Answer k) {
        ArrayList <OrdinarynodeFiles> list = k.getFilesList();

        for(OrdinarynodeFiles of : list){
            ArrayList <MKFileDescriptor> answer_files = of.getFileList();
            for(MKFileDescriptor files : answer_files){
                int index = 0;
                   
                for(SearchField field : this.found){
                    if(field.sameFile(files)){
                        this.found.get(index).addOwner(of.getOwner());
                        break;
                    }
                    else{
                        index ++;
                    }
                }
            }
        }



        this.setChanged();
        this.notifyObservers();
    }

   

    /*public boolean isIn(Answer k) {

    }*/
    
    public ArrayList <SearchField> getFoundList(){
        return this.found;
    }
    
    public int getId(){return this.id;}
    
    /*public ArrayList <NodeInfo> getOwners(String md5){
    ArrayList <NodeInfo> owner_list = new ArrayList();

    for(Answer a : found){
    ArrayList <OrdinarynodeFiles> files = a.getFilesList();
    for(OrdinarynodeFiles f : files){

    }
    }

    return owner_list;
    }*/
}
