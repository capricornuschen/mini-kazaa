/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.test.GUI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 29-ott-2008
 * @file FileCellRenderer.java
 */
public class FileCellRenderer extends DefaultTableCellRenderer{
    
    @Override
    public JLabel getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        String val = (String)value;
        
        JLabel cell = new JLabel();
        
        if(val.contains(".txt")){
            cell.setText(val);
            cell.setIcon(new ImageIcon(getClass().getResource("/lpr/minikazaa/icons/mini_icon.png")));
        }
        
        return cell;
    }
}
