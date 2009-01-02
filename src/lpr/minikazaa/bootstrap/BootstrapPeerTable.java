/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.bootstrap;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 2-gen-2009
 * @file BootstrapPeerTable.java
 */
public class BootstrapPeerTable extends JTable implements Observer{

    DefaultTableModel my_dtm;

    public BootstrapPeerTable(){

    }

    public void update(Observable o, Object arg) {

    }

}
