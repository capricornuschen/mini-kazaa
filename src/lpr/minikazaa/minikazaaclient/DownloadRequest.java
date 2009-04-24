

package lpr.minikazaa.minikazaaclient;

import java.io.Serializable;
import lpr.minikazaa.bootstrap.NodeInfo;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 25-nov-2008
 * @file DownloadRequest.java
 */
public class DownloadRequest implements Serializable{
    private String file_request;//File da scaricare
    private NodeInfo request_source;//Sorgente della richiesta

    public DownloadRequest(
            String file,
            NodeInfo ni){
    
        //Inizializza la richiesta di download.
        this.file_request = file;
        this.request_source = ni;
    }
    
    public String getFile(){return this.file_request;}
    public NodeInfo getSource(){return this.request_source;}
}
