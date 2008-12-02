/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.GUI;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 1-dic-2008
 * @file SharedFilesTable.java
 */
public class SharedFilesTable extends JTable implements Observer {

    private DefaultTableModel my_dtm;

    public SharedFilesTable() {
        this.my_dtm = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Name", "Size", "MD5", "Path"
                }) {

            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        this.setModel(my_dtm);
    }

    public void update(Observable o, Object arg) {
    }
}
