package lpr.minikazaa.minikazaaclient.ordinarynode;

import java.util.ArrayList;
import java.util.Observable;
import lpr.minikazaa.minikazaaclient.Download;
import lpr.minikazaa.minikazaaclient.DownloadResponse;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 3-dic-2008
 * @file OrdinarynodeDownloadMonitor.java
 */
public class OrdinarynodeDownloadMonitor extends Observable {
    
    ArrayList <Download> downloads;
    
    public OrdinarynodeDownloadMonitor(){
        this.downloads = new ArrayList();
    }

    public synchronized void add(Download d){
        if(!this.downloads.contains(d))
            this.downloads.add(d);
    }

    public synchronized void remove(Download d){
        this.downloads.remove(d);
    }

    public boolean isEmpty() {
        if(this.downloads.size() == 0)
            return true;
        else
            return false;
    }

    public ArrayList<Download> getDownloadList(){
        return this.downloads;
    }

    public synchronized boolean addBytes(DownloadResponse part){
        //To be written
        for(Download d : downloads){
            if(d.getFile().getMd5().equals(part.getFile())){
                d.updateDownloadBytes(part.getPart().length);
                addToLocation(part, d.getDownloaderPath()+d.getFile().getFileName());
            }
        }


        return true;
    }

    //Static methods
    public static void addToLocation(DownloadResponse part, String location){

    }

}
