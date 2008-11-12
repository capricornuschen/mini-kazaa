/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.test.GUI;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 6-nov-2008
 * @file DoSomethingCell.java
 */
public class DoSomethingCell extends DefaultTableCellRenderer {

    @Override
    public JButton getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        JButton do_something_bt = new JButton("Do it!");
        do_something_bt.setEnabled(true);
        


        return do_something_bt;
    }
}
