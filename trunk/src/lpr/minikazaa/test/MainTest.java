/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.test;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.UIManager;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.MainGui;
import lpr.minikazaa.minikazaaclient.NodeConfig;
import lpr.minikazaa.minikazaaclient.Query;
import lpr.minikazaa.test.GUI.GuiTest;
import lpr.minikazaa.test.GUI.SharedDataStructure;
import lpr.minikazaa.util.FileUtil;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 10-ott-2008
 * @file MainTest.java
 */
public class MainTest {

    public static void main(String[] args) {
        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (Exception ex) {
        }
        //TableTest.main_function();
        //Prova_grafica.main_function();
        //main1();
        //main2();
        //main3();
        //main4();
        main5();
    }

    public static void main1() {
        Scanner s = new Scanner(System.in);

        ArrayList<String> test_list = new ArrayList();
        test_list.add("mamma ciao");
        test_list.add("ciao mamma");
        test_list.add("ciao papà");
        System.out.println("Enter regex: ");
        Pattern pattern = Pattern.compile(s.nextLine());
        
        Query q = new Query();


        boolean found = false;
        for (String sc : test_list) {
            Matcher matcher = pattern.matcher(sc);


            while (matcher.find()) {
                System.out.println("Elemento trovato: " + sc);
                found = true;
            }


        }

        if (!found) {
            System.out.println("Non ho trovato niente.");
        }
    }
    
    

    public static void main3(){
        byte [] array = FileUtil.getFilePart("/home/giovine/pp-scommessa.pps", 0, 4096);
        System.out.println("Bytes estratti: "+array);
    }

    /*public static void main4(){
        GuiTest t = new GuiTest(new SharedDataStructure());
        t.setVisible(true);
    }*/

    public static void main5(){
        GuiTest t = new GuiTest();
        t.setVisible(true);
    }
}
