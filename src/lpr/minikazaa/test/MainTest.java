/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.test;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 10-ott-2008
 * @file MainTest.java
 */
public class MainTest {

    public static void main(String[] args) {
        main1();
    }

    public static void main1() {
        Scanner s = new Scanner(System.in);

        ArrayList<String> test_list = new ArrayList();
        test_list.add("mamma ciao");
        test_list.add("ciao mamma");
        test_list.add("ciao papà");
        System.out.println("Enter regex: ");
        Pattern pattern = Pattern.compile(s.nextLine());


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
}
