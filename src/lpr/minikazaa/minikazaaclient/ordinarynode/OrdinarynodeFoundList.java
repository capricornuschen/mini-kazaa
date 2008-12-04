
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.minikazaaclient.ordinarynode;

import java.util.ArrayList;
import java.util.Observable;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.Answer;

/**
 * Class that lists the answers from super nodes with the shearched files.
 * 
 * @author Andrea Di Grazia, Massimiliano Giovine
 */
public class OrdinarynodeFoundList extends Observable {
    private int id;
    private ArrayList<Answer> found;
    
    public OrdinarynodeFoundList(int n) {
        this.id = n;
        found = new ArrayList();
    }

    public void add(Answer k) {
        found.add(k);
        this.setChanged();
        this.notifyObservers();
    }

    public void remove(Answer k) {
        found.remove(k);
        this.setChanged();
        this.notifyObservers();
    }

    public boolean isIn(Answer k) {
        if (found.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < found.size(); i++) {
                if (k.getFilesList() == found.get(i).getFilesList()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public ArrayList <Answer> getFoundList(){
        return this.found;
    }
    
    public int getId(){return this.id;}
    
    public ArrayList <NodeInfo> getOwners(String md5){
        ArrayList <NodeInfo> owner_list = new ArrayList();
        
        for(Answer a : found){
            ArrayList <OrdinarynodeFiles> files = a.getFilesList();
            for(OrdinarynodeFiles f : files){
                
            }
        }
        
        return owner_list;
    }
}
