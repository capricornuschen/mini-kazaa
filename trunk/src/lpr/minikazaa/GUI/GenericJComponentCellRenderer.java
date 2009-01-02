/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.GUI;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 2-gen-2009
 * @file GenericJComponentCellRenderer.java
 */
public class GenericJComponentCellRenderer extends DefaultTableCellRenderer{
    @Override
    public JComponent getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        if(value instanceof JComponent)
            return (JComponent) value;

        return null;
    }
}
