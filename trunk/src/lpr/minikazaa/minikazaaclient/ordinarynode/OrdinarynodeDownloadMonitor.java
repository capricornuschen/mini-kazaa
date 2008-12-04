/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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

    public boolean isEmpty() {
        if(this.downloads.size() == 0)
            return true;
        else
            return false;
    }

}
