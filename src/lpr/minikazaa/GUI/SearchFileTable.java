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
import lpr.minikazaa.minikazaaclient.SearchField;
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeFoundList;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 13-gen-2009
 * @file SearchFileTable.java
 */
public class SearchFileTable extends JTable implements Observer {

    private DefaultTableModel my_dtm;
    private int my_index;

    public SearchFileTable(int idx) {
        this.my_index = idx;

        this.my_dtm = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "File name", "Size", "Owner", "MD5"
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
    }

    public void update(Observable o, Object arg) {
        if(o instanceof OrdinarynodeFoundList){
            
            this.my_dtm.setNumRows(0);

            OrdinarynodeFoundList found_list = (OrdinarynodeFoundList) o;

            ArrayList <SearchField> field_list = found_list.getFoundList();

            for(SearchField field : field_list){
                Object [] row = new Object [4];

                row[0] = field.getFile().getFileName();
                row[1] = field.getFile().getSize();
                row[2] = field.getOwners().getId();
                row[3] = field.getFile().getMd5();

                this.my_dtm.addRow(row);
            }
        }
    }
}
