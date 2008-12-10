package lpr.minikazaa.minikazaaclient.ordinarynode;

import java.util.ArrayList;
import java.util.Observable;
import lpr.minikazaa.minikazaaclient.Download;

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

    public void add(Download d){
        if(!this.downloads.contains(d))
            this.downloads.add(d);
    }

    public void remove(Download d){
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

}
