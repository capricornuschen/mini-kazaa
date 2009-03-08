/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient;

import java.io.Serializable;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 25-nov-2008
 * @file DownloadResponse.java
 */
public class DownloadResponse implements Serializable {
    private byte [] part;
    private String file;
    
    public DownloadResponse(
            byte [] part,
            String file){
    
        this.part = part;
        this.file = file;
    }
    
    public String getFile(){return this.file;}
    public byte [] getPart(){return this.part;}

    public void setFile(String md5) {
        this.file = md5;
    }
}
