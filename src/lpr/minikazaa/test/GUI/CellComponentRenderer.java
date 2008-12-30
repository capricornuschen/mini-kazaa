/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.test.GUI;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 27-dic-2008
 * @file CellComponentRenderer.java
 */
public class CellComponentRenderer extends DefaultTableCellRenderer{

    @Override
    public JComponent getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(value instanceof JComponent)
            return (JComponent)value;

        return null;
    }
}
