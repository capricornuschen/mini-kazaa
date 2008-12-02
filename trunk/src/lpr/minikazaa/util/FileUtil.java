package lpr.minikazaa.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    /**
     * Function that allows you to get a specified part of a file and
     * transform it in an byte array to send it over the net.
     *
     * @param absolute_path the absolute path to open the file
     * @param part the part we want to transform in an byte[]
     * @param length the length of the part in bytes
     *
     * @return the byte array that rapresents the part of the file.
     */
    public static byte [] getFilePart(String absolute_path, int part, int length){
        byte [] byte_part = new byte[length];

        File file_to_open = new File(absolute_path);

        FileInputStream read_file = null;

        try{
            read_file = new FileInputStream(file_to_open);
        }
        catch(FileNotFoundException ex){
            System.out.println("File not found.");
            return null;
        }

        BufferedInputStream file_buffer = new BufferedInputStream(read_file);

        try{
            file_buffer.read(byte_part,part * length, length);
        }
        catch(IOException e){
            System.out.println("An error has occurred reading "+absolute_path+".");
            return null;
        }

        return byte_part;
    }
    
}
