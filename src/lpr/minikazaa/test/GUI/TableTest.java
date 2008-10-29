/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.test.GUI;

import java.io.File;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 28-ott-2008
 * @file TableTest.java
 */
public class TableTest {
    public static void main_function(){
        SharedDataStructure shared = new SharedDataStructure();
        System.out.println("Table Test.");
        GuiTableTest gui = new GuiTableTest(shared);
        gui.setVisible(true);
        
        File f = new File("/home/giovine/Scrivania/extension.zip");
        
        
    }
}
