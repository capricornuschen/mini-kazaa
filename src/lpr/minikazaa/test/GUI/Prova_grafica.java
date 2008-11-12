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
        GuiObserver gui = new GuiObserver(sh);
        
        
        gui.setVisible(true);
        
        
    }
}
