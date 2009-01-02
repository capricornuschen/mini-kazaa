/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.GUI;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.SupernodeList;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 30-dic-2008
 * @file SuperNodesTable.java
 */
public class SuperNodesTable extends JTable implements Observer{

    private DefaultTableModel my_dtm;

    public SuperNodesTable() {
        this.my_dtm = new DefaultTableModel(new Object[][]{},
                new String[]{
                    "Stato", "Indirizzo peer", "Ping"
                }) {

            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        this.setModel(this.my_dtm);

        this.getColumn("Stato").setCellRenderer(new GenericJComponentCellRenderer());
    }

    public void update(Observable o, Object arg) {
        if( o instanceof SupernodeList){
            SupernodeList sn_list = (SupernodeList) o;
            ArrayList <NodeInfo> nodeinfo_list = sn_list.getList();
            this.my_dtm.setNumRows(0);
            for(NodeInfo info : nodeinfo_list){
                //The new row
                Object [] row = new Object[3];

                //Definition of content of the row
                String address = info.getIaNode().toString().substring(1);
                String ping = ""+info.getPing();

                //Set state smile
                JLabel state = new JLabel();
                state.setText("");
                if(info.getPing()<100)
                    state.setIcon(new ImageIcon(getClass().getResource("/lpr/minikazaa/icon/ok_smile_icon.png")));
                else if(info.getPing() >= 100 && info.getPing() < 200)
                    state.setIcon(new ImageIcon(getClass().getResource("/lpr/minikazaa/icon/mmm_smile_icon.png")));
                else if(info.getPing() >= 200)
                    state.setIcon(new ImageIcon(getClass().getResource("/lpr/minikazaa/icon/bad_smile_icon.png")));

                row[0] = state;
                row[1] = address;
                row[2] = ping;
            }
        }
    }
    
}
