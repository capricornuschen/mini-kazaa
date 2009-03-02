/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.minikazaaclient.ordinarynode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.util.MKFileDescriptor;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 9-ott-2008
 * @file OrdinarynodeFiles.java
 */
public class OrdinarynodeFiles extends Observable implements Serializable {

    private ArrayList<MKFileDescriptor> file_list;
    private NodeInfo my_info;

    public OrdinarynodeFiles(NodeInfo infos) {
        this.my_info = infos;
        this.file_list = new ArrayList();
    }

    public synchronized void addFiles(MKFileDescriptor[] new_files) {
        for (int i = 0; i < new_files.length; i++) {

            if (!isIn(new_files[i])) {
                this.file_list.add(new_files[i]);
            }
        }
        this.setChanged();
        this.notifyObservers();
    }

    public MKFileDescriptor getFileList(String md5) {
        //Scorro la lista e individuo il file richiesto
        for(MKFileDescriptor file : this.file_list){
            if(file.getMd5().equals(md5))
                return file;
        }

        //Se le query arrivano correttamente non si dovrebbe
        //mai arrivare a questo punto.
        return null;
    }

    public synchronized void removeFiles(MKFileDescriptor[] old_files) {
        
        for (int i = 0; i < old_files.length; i++) {
            int index = 0;
            for (MKFileDescriptor file : this.file_list) {
                
                if ((old_files[i].getFileName().equals(file.getFileName())) &&
                        old_files[i].getMd5().equals(file.getMd5()) &&
                        old_files[i].getPath().equals(file.getPath())) {

                    
                    this.file_list.remove(index);
                    break;
                }
                index ++;
            }

        }
        this.setChanged();
        this.notifyObservers();
    }

    //Get methods
    public ArrayList<MKFileDescriptor> getFileList() {
        return file_list;
    }

    public NodeInfo getOwner() {
        return this.my_info;
    }

    public boolean isIn(MKFileDescriptor f) {
        for (MKFileDescriptor file : this.file_list) {
            if ((f.getFileName().equals(file.getFileName())) &&
                    f.getMd5().equals(file.getMd5()) &&
                    f.getPath().equals(file.getPath()) &&
                    f.getSize() == file.getSize()) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        if (this.file_list.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void resetList(ArrayList<MKFileDescriptor> list) {
        this.file_list = list;
    }

    public synchronized ArrayList <OrdinarynodeFiles> searchFiles(String regex){
        ArrayList <OrdinarynodeFiles> l = new ArrayList();

        Pattern pattern = Pattern.compile(regex);



            OrdinarynodeFiles files_found = new OrdinarynodeFiles(this.my_info);
            MKFileDescriptor [] new_array = null;

            for(MKFileDescriptor file : file_list){
                System.out.println("DEBUG: file analizzato "+file.getFileName());
                ArrayList <MKFileDescriptor> found_list = new ArrayList();


                    Matcher matcher = pattern.matcher(file.getFileName());

                    while(matcher.find()){
                        found_list.add(file);
                        System.out.println("DEBUG: file trovato "+file.getFileName());
                        new_array = new MKFileDescriptor[found_list.size()];
                        int index = 0;
                        for(MKFileDescriptor file_just_found : found_list){
                            new_array[index] = file_just_found;
                            index ++;
                        }
                        files_found.addFiles(new_array);
                        l.add(files_found);
                    }

            }
           
        return l;
    }
}
