/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.test.GUI;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 27-ott-2008
 * @file Prova_grafica.java
 */
public class Prova_grafica {
    public static void main_function(){
        SharedDataStructure sh = new SharedDataStructure();
        GuiTest gui = new GuiTest(sh);
        AddFrame add = new AddFrame(sh,gui);
        
        gui.setVisible(true);
        add.setVisible(true);
        
    }
}
