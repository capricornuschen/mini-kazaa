package lpr.minikazaa.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeFiles;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 21-nov-2008
 * @file FileUtil.java
 */
public class FileUtil {

    public static MKFileDescriptor[] getFilesIntoDirectory(File dir) {
        if (!dir.isDirectory()) {
            return null;
        }

        MKFileDescriptor[] file_array = null;

        String[] file_list = dir.list();

        int directory = 0;
        for (int i = 0; i < file_list.length; i++) {
            File f = new File(file_list[i]);
            if (f.isDirectory()) {
                directory++;
            }
        }

        file_array = new MKFileDescriptor[file_list.length - directory];
        int index = 0;
        for (int i = 0; i < file_list.length; i++) {
            File f = new File(file_list[i]);

            if (!f.isDirectory()) {
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
     * Funzione che prende una parte specifica di file e la traforma in un
     * array di byte per spedirlo sulla rete.
     *
     * @param absolute_path il path del file che deve essere trasformato
     * @param part la parte del file che deve essere trasformata in byte[]
     * @param length la lunghezza della parte di byte
     *
     * @return byte_part l' array che rappresenta la parte di byte.
     */
    public static byte[] getFilePart(String absolute_path, int part, int length) {
        byte[] byte_part = new byte[length];

        File file_to_open = new File(absolute_path);

        FileInputStream read_file = null;

        try {
            read_file = new FileInputStream(file_to_open);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
            return null;
        }

        BufferedInputStream file_buffer = new BufferedInputStream(read_file);

        try {
            file_buffer.read(byte_part, part * length, length);
        } catch (IOException e) {
            System.out.println("An error has occurred reading " + absolute_path + ".");
            return null;
        }

        return byte_part;
    }

    public static MKFileDescriptor[] transformFileToMKFile(File[] files_array) {
        MKFileDescriptor[] mk_files = new MKFileDescriptor[files_array.length];

        for (int i = 0; i < files_array.length; i++) {
            String file_name = files_array[i].getName();
            String code = md5.getMD5(files_array[i]);
            long size = files_array[i].length();
            String abs_path = files_array[i].getAbsolutePath();
            mk_files[i] = new MKFileDescriptor(
                    file_name,
                    code,
                    size,
                    abs_path);

        }

        return mk_files;
    }

    public static MKFileDescriptor transformFileToMKFile(File file_to_convert) {
        String file_name = file_to_convert.getName();
            String code = md5.getMD5(file_to_convert);
            long size = file_to_convert.length();
            String abs_path = file_to_convert.getAbsolutePath();
            MKFileDescriptor mk_file = new MKFileDescriptor(
                    file_name,
                    code,
                    size,
                    abs_path);

            return mk_file;
    }


    //Functions that save and load already shared files.
    public static void saveMySharedFiles(OrdinarynodeFiles shared_files){
        File sh_files_save = new File("shared.mk");

        FileOutputStream file_stream = null;
        ObjectOutputStream object_stream = null;

        try{
            file_stream = new FileOutputStream(sh_files_save);
            object_stream = new ObjectOutputStream(file_stream);

            object_stream.writeObject(shared_files);
        }
        catch(FileNotFoundException file_ex){
            System.err.println("File shared.mk not Found.");
            return;
        }
        catch(IOException io_ex){
            System.err.println("IO error while initializing Object stream on file_stream.");
            return;
        }
    }

    public static OrdinarynodeFiles loadMySharedFiles(NodeInfo my_infos){
        OrdinarynodeFiles my_files = null;
        File my_shared_files = new File("shared.mk");

        FileInputStream file_stream = null;
        ObjectInputStream input_stream = null;

        try{
            file_stream = new FileInputStream(my_shared_files);
            input_stream = new ObjectInputStream(file_stream);

            my_files = (OrdinarynodeFiles)input_stream.readObject();

        }
        catch(FileNotFoundException file_ex){
            System.err.println("File shared.mk not Found.");
            return new OrdinarynodeFiles(my_infos);
        }
        catch(IOException io_ex){
            System.err.println("IO error while initializing Object stream on file_stream.");
            return  new OrdinarynodeFiles(my_infos);
        }
        catch(ClassNotFoundException class_ex){
            System.err.println("No serializable class inside shared.mk.");
            return null;
        }
        return my_files;
    }
}
