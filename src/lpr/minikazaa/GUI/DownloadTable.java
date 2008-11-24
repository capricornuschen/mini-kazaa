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
 * @date 24-nov-2008
 * @file DownloadTable.java
 */
public class DownloadTable extends JTable implements Observer {
    
    private DefaultTableModel my_dtm;

    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
