/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.GUI;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Customed class that renders
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 9-dic-2008
 * @file ProgressBarCellRenderer.java
 */
public class ProgressBarCellRenderer extends DefaultTableCellRenderer {
    @Override
    public JProgressBar getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        if(value instanceof JProgressBar)
            return (JProgressBar) value;

        return null;
    }
}
