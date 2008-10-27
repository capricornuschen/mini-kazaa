/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.test.GUI;

import java.util.ArrayList;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 27-ott-2008
 * @file SharedDataStructure.java
 */
public class SharedDataStructure {

    private ArrayList<String> variable;

    public SharedDataStructure() {
        this.variable = new ArrayList();

    }

    public void addElement(String new_element) {
        variable.add(new_element);
        
    }
    
    public void removeElement(String old_element){
        int i = 0;
        for(String s : variable){
            if(s.equals(old_element))
                variable.remove(i);
            return;
        }
    }

    public ArrayList<String> getVariable() {
        return this.variable;
    }
    public String getIdx(int i){
        return this.variable.get(i);
    }
}
