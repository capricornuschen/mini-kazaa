

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
     * Functione che controlla la stringa e verifica se si tratta di         
     * un indirizzo valido
     * 
     * @param addr
     * @return true se si tratta di un indirizzo, false altrimenti.
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
            size = ((float)file_size / 1024) + " Kb";
        }
        else if(file_size < 1024*1024*1024){
            size = ((float)file_size /(1024*1024)) + " Mb";
        }
        else if(file_size < 1024*1024*1024*1024){
            size = ((float)file_size / (1024*1024*1024)) + " Gb";
        }
        else
            size = ((float)file_size / (1024*1024*1024*1024)) + " Tb";

        return size;
    }
}
