/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.test.GUI;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 10-nov-2008
 * @file MyTable.java
 */
public class MyTable extends JTable implements Observer{
    private DefaultTableModel my_dtm;
    
    public MyTable(){
        this.my_dtm = new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        );
        this.setModel(this.my_dtm);
    }
    public void update(Observable o , Object art){
        this.my_dtm.setNumRows(0);
        if(o instanceof SharedDataStructure){
            SharedDataStructure sh = (SharedDataStructure)o;
            ArrayList <String> list = sh.getVariable();
            
            for(String s : list){
                Object [] row = new Object[1];
                row[0] = s;
                this.my_dtm.addRow(row);
                
            }
            
        }
    }
}
