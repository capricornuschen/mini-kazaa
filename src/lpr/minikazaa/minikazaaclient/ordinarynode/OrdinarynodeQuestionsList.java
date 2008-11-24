
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient.ordinarynode;

import java.util.ArrayList;
import java.util.List;
import lpr.minikazaa.minikazaaclient.Answer;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 20-nov-2008
 * @file OrdinarynodeQuestionsList.java
 */
public class OrdinarynodeQuestionsList {
    
    private List <OrdinarynodeFoundList> my_res_list;
    
    
    public OrdinarynodeQuestionsList(){
        this.my_res_list = new ArrayList();
    }
    
    public void add(Answer a){
        for(OrdinarynodeFoundList l : this.my_res_list){
            if(a.getID() == l.getId()){
                l.add(a);
                return;
            }
        }
    }
    
    public void remove (int index){
        
        int i = 0;
        
        for(OrdinarynodeFoundList l : this.my_res_list){
            if(l.getId() == index){
                this.my_res_list.remove(i);
                return;
            }
        }
    }
    
    public OrdinarynodeFoundList getFoundList(int index){
        for(OrdinarynodeFoundList l : this.my_res_list){
            if(l.getId() == index){
                return l;
            }
        }
        return null;
    }
}

