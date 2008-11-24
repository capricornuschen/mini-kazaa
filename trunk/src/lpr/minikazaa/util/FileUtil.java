package lpr.minikazaa.util;

import java.io.File;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 21-nov-2008
 * @file FileUtil.java
 */
public class FileUtil {

    public static MKFileDescriptor [] getFilesIntoDirectory(File dir){
        if(!dir.isDirectory())
            return null;
        
        MKFileDescriptor [] file_array = null;
        
        String [] file_list = dir.list();
        
        int directory = 0;
        for(int i = 0; i < file_list.length; i++){
            File f = new File(file_list[i]);
            if(f.isDirectory())
                directory++;
        }
        
        file_array = new  MKFileDescriptor [file_list.length - directory];
        int index = 0;
        for(int i = 0; i < file_list.length; i++){
            File f = new File(file_list[i]);
            
            if(!f.isDirectory()){
                MKFileDescriptor file = new MKFileDescriptor(
                        f.getName(),
                        md5.getMD5(f),
                        f.length(),
                        f.getAbsolutePath());
               file_array[index] = file;
               index++;
            }
        }
        
        return file_array;
    }
    
}
