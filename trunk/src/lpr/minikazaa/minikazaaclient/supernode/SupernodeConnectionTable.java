/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.minikazaaclient.supernode;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.SupernodeList;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 11-nov-2008
 * @file SupernodeConnectionTable.java
 */
public class SupernodeConnectionTable extends JTable implements Observer {

    private DefaultTableModel my_dtm;

    public SupernodeConnectionTable() {
        this.my_dtm = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "User Name", "IP address", "File", "O.N. connected"
                });

    }

    public void update(Observable o, Object arg) {
        if (arg instanceof SupernodeList) {
            ArrayList<NodeInfo> list = ((SupernodeList) arg).getList();
            for(NodeInfo n : list){
                Object [] row = new Object[4];
                
                row[0] = n.getId();
                row[1] = n.getIaNode().toString();
                row[2] = n.getDoor();
                row[3] = "10";
            }

        }
    }
}
