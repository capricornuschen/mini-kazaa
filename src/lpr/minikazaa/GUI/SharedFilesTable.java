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
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeFiles;
import lpr.minikazaa.util.MKFileDescriptor;
import lpr.minikazaa.util.StringManipulationUtil;

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

        if(o instanceof OrdinarynodeFiles){
            this.my_dtm.setNumRows(0);
            OrdinarynodeFiles modified_list = (OrdinarynodeFiles)o;
            ArrayList<MKFileDescriptor []> file_list = modified_list.getFileList();

            for(MKFileDescriptor [] file_array : file_list){
                for(int i = 0; i < file_array.length; i++){
                    Object [] row = new Object[4];
                    row[0] = (String)file_array[i].getFileName();
                    row[1] = (String)StringManipulationUtil.getRapresentableSize(file_array[i].getSize());
                    row[2] = (String)file_array[i].getMd5();
                    row[3] = (String)file_array[i].getPath();

                    this.my_dtm.addRow(row);
                }
            }
        }


    }
}
