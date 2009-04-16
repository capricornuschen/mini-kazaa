/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.util;

import java.io.File;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 28-mar-2009
 * @file Log.java
 */
public class Log {

    private File log_file;
    private boolean debug;
    private boolean information;
    private boolean critical;

    //Prende il path e lo assegna a una propria variabile che indicizza il file.
    //Prende l'array di booleani e si memorizza che tipo di informazioni salvare.
    public Log(String path, boolean [] log_type){

    }

    //Salva il log su file solo se è attiva l'opzione per quel tipo.
    public void LogMessage(String message, int messageType ){

    }

}
