
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.GUI;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.Answer;
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeFiles;
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeFoundList;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 17-nov-2008
 * @file FilesSearchTable.java
 */
public class FilesSearchTable extends JTable implements Observer {

    private DefaultTableModel my_dtm;
    private int index;

    public FilesSearchTable() {
        this.my_dtm = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Nome File", "Dimensione", "N° Fonti", "MD5"
                }) {

            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        this.setModel(this.my_dtm);
        this.index = -1;
    }

    public void update(Observable o, Object arg) {
        if(o instanceof OrdinarynodeFoundList){
            
            
        }
    }
}

