/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient;

import java.io.File;
import javax.swing.UIManager;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 11-set-2008
 * @file MiniKazaaClient.java
 */
public class MiniKazaaClient {
    public static void main (String [] args){
        
        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (Exception ex) {
        }
        
        //File xml = new File("config.xml");
        //if(xml == null){
            InitialChoiceFrame init_frame = new InitialChoiceFrame();
            init_frame.setLocationRelativeTo(null);
            init_frame.setVisible(true);
        //}
        //else{
            //Load xml configuration file
        //}
    }
}
