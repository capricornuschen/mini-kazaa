
package lpr.minikazaa.minikazaaclient;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.UIManager;

import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeEngine;
import lpr.minikazaa.minikazaaclient.supernode.SupernodeEngine;
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

        //Imposta la cartella di download e se non la trova la crea.
        File down_dir = new File("./downloads");
        if(!down_dir.exists() || !down_dir.isDirectory())
            down_dir.mkdir();

        File xml_config = new File("./config.xml");

        if (!xml_config.exists()) {
            //xml file, contiene la configurazione, se non esiste l' utente
            //dovrà decidere i parametri.
            InitialChoiceFrame init_frame = new InitialChoiceFrame();
            init_frame.setLocationRelativeTo(null);
            init_frame.setVisible(true);

            //Attendendo la creazione del file di configurazione.
            while (!xml_config.exists()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
            }

            NodeConfig config;

            //Non verranno chiamate le eccezione che vengono create dentro il
            //ciclo.
            XMLDecoder decode_xml = new XMLDecoder(
                    new BufferedInputStream(
                    new FileInputStream("config.xml")));

            config = (NodeConfig) decode_xml.readObject();

            if (config.getIsSN()) {
                System.out.println("We must load a Super node application.");
                //Avvio il nuono thread con il motore del Supernode.
                SupernodeEngine sn_eng = new SupernodeEngine(config);
                Thread super_node_thread = new Thread(sn_eng);
                super_node_thread.start();
                
            }
            else{
                System.out.println("We must load an Ordinary node application.");
                OrdinarynodeEngine on_eng = new OrdinarynodeEngine(config);
                Thread ordinary_node_thread = new Thread(on_eng);
                ordinary_node_thread.start();
            }
        }
        else{
            //Il file Xml esiste e quindi possiamo caricare le proprietà dell' applicazione.
            NodeConfig config;
            //It won't call any exception cause the "if" above checks
            //if the xml file exists
            XMLDecoder decode_xml = new XMLDecoder(
                    new BufferedInputStream(
                    new FileInputStream("config.xml")));

            config = (NodeConfig) decode_xml.readObject();


            
            if (config.getIsSN()) {
                System.out.println("We must load a Super node application.");
                //Start a new thread with super node engine.
                SupernodeEngine sn_eng = new SupernodeEngine(config);
                Thread super_node_thread = new Thread(sn_eng);
                super_node_thread.start();
                
            }
            else{
                System.out.println("We must load an Ordinary node application.");
                OrdinarynodeEngine on_eng = new OrdinarynodeEngine(config);
                Thread ordinary_node_thread = new Thread(on_eng);
                ordinary_node_thread.start();
            }
        }

    }
}
