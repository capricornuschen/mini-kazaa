package lpr.minikazaa.test;

import lpr.minikazaa.minikazaaclient.SupernodeList;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 24-ott-2008
 * @file TestFunctions.java
 */
public class TestFunctions {
    public static void objectProva(){
        SupernodeList list = new SupernodeList();
        Object a = list;

        if(a instanceof SupernodeList)
            System.out.println("C'avevo ragione io.");
        else
            System.out.println("Non c'avevo ragione io.");
    }
}
