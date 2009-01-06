/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.bootstrap;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lpr.minikazaa.minikazaaclient.SupernodeList;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 2-gen-2009
 * @file BootstrapPeerTable.java
 */
public class BootstrapPeerTable extends JTable implements Observer {

    DefaultTableModel my_dtm;

    public BootstrapPeerTable() {
        
        this.my_dtm = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Indirizzo"
                }) {

            boolean[] canEdit = new boolean[]{
                false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        this.setModel(this.my_dtm);

    }

    public void update(Observable o, Object arg) {
        System.out.println("Update method called on bootstrap address table.");
        if(o instanceof SupernodeList){
            SupernodeList sn_list = (SupernodeList) o;

            ArrayList<NodeInfo> node_list = sn_list.getList();

            this.my_dtm.setRowCount(0);
            for(NodeInfo node : node_list){
                Object [] row = new Object[1];

                row[0] = node.getId();

                this.my_dtm.addRow(row);
            }
        }
    }
}
