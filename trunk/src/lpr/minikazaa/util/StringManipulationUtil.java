/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.util;

import java.util.StringTokenizer;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 15-set-2008
 * @file StringManipulationUtil.java
 */
public class StringManipulationUtil {
    
    /**
     * Function that check a string to say if it's a correct addres or not.
     * 
     * @param addr
     * @return true if addr is a correct address false otherwise.
     */
    public static boolean isInetAddress(String addr){
        StringTokenizer tokenizer = new StringTokenizer(addr,".");
        boolean result = true;
        
        while(tokenizer.hasMoreTokens()){
            String piece = tokenizer.nextToken();
            
            int pic;
            try{
                pic= Integer.parseInt(piece);
            }
            catch(NumberFormatException ex){ return false; }
            if(pic< 0 || pic >255) result = false;
        }
        
        return result;
    }

    public static String getRapresentableSize(long file_size){
        String size = null;
        //Bytes
        if(file_size < 1024) {
            size = file_size+" bytes";
            if(file_size == 1)
                size = file_size+" byte";
        }
        else if(file_size < 1024*1024){
            size = (file_size / 1024) + " Kb";
        }
        else if(file_size < 1024*1024*1024){
            size = (file_size /(1024*1024)) + " Mb";
        }
        else if(file_size < 1024*1024*1024*1024){
            size = (file_size / (1024*1024*1024)) + " Gb";
        }
        else
            size = (file_size / (1024*1024*1024*1024)) + " Tb";

        return size;
    }
}
