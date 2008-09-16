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

    public static void main(String[] args)  {

        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (Exception ex) {
        }

        File xml_config = new File("./config.xml");
        
        if(!xml_config.exists()){
            
            int config = 0;
            Integer check_config = new Integer(config);
  
            InitialChoiceFrame init_frame = new InitialChoiceFrame(check_config);
            init_frame.setLocationRelativeTo(null);
            init_frame.setVisible(true);
            
            //Waiting creation of config file
            while(!xml_config.exists()){try{Thread.sleep(1000);}catch(InterruptedException ex){}}
            
            
        }

    }
}
