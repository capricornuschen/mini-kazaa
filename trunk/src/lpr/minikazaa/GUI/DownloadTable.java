/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.GUI;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lpr.minikazaa.minikazaaclient.Download;
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeDownloadMonitor;
import lpr.minikazaa.util.StringManipulationUtil;

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

        this.getColumn("Avanzamento").setCellRenderer(new ProgressBarCellRenderer());
        
        if(!this.my_monitor.isEmpty()){
            this.update(my_monitor, null);
        }
    }

    public void update(Observable o, Object arg) {
        if( o instanceof OrdinarynodeDownloadMonitor){
            OrdinarynodeDownloadMonitor monitor = (OrdinarynodeDownloadMonitor) o;
            ArrayList <Download> dl_list = monitor.getDownloadList();

            for(Download dl : dl_list){
                Object [] row = new Object[4];

                long size = dl.getFile().getSize();
                
                JProgressBar progress = new JProgressBar();
                progress.setMaximum(100);

                long value = (dl.getDownloadedBytes() * 100 / size);
                progress.setValue((int) value);

                row[0] = dl.getFile().getFileName();
                row[1] = StringManipulationUtil.getRapresentableSize(size);
                row[2] = progress;
                row[3] = 1;
                
                this.my_dtm.addRow(row);
            }
        }
    }
}
