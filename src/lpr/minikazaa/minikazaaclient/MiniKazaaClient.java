/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.minikazaaclient;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.UIManager;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 11-set-2008
 * @file MiniKazaaClient.java
 */
public class MiniKazaaClient {

    public static void main(String[] args) throws FileNotFoundException {

        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (Exception ex) {
        }

        File xml_config = new File("./config.xml");

        if (!xml_config.exists()) {
            //Xml file, contains the configuration, doesn't exist so user
            //will decide any parameter.
            InitialChoiceFrame init_frame = new InitialChoiceFrame();
            init_frame.setLocationRelativeTo(null);
            init_frame.setVisible(true);

            //Waiting creation of config file
            while (!xml_config.exists()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
            }

            NodeConfig config;

            //It won't call any exception cause the xml file is created during
            //while loop.
            XMLDecoder decode_xml = new XMLDecoder(
                    new BufferedInputStream(
                    new FileInputStream("config.xml")));

            config = (NodeConfig) decode_xml.readObject();

            if (config.getIsSN()) {
                System.out.println("We must load a Super node application.");
            }
            else{
                System.out.println("We must load an Ordinary node application.");
            }
        }
        else{
            //Xml file exists so we can load properly application.
            NodeConfig config;
            //It won't call any exception cause the "if" above checks
            //if the xml file exists
            XMLDecoder decode_xml = new XMLDecoder(
                    new BufferedInputStream(
                    new FileInputStream("config.xml")));

            config = (NodeConfig) decode_xml.readObject();

            if (config.getIsSN()) {
                System.out.println("We must load a Super node application.");
            }
            else{
                System.out.println("We must load an Ordinary node application.");
            }
        }

    }
}
