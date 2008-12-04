/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.GUI;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeDownloadMonitor;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 24-nov-2008
 * @file DownloadTable.java
 */
public class DownloadTable extends JTable implements Observer {

    private DefaultTableModel my_dtm;
    private OrdinarynodeDownloadMonitor my_monitor;

    public DownloadTable(OrdinarynodeDownloadMonitor monitor) {
        this.my_monitor = monitor;

        this.my_dtm = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Nome File", "Dimensione", "Avanzamento", "Numero Fonti"
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

        if(!this.my_monitor.isEmpty()){

        }
    }

    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
