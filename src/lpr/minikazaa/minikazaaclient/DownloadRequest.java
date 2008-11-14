/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 13-nov-2008
 * @file DownloadRequest.java
 */
public class DownloadRequest implements Serializable {
    private File file_to_download;
    private String md5;
    private int part;
    
    
    public DownloadRequest(File f, String hash, int i){
        this.file_to_download = f;
        this.md5 = hash;
        this.part = i;
    }
    
    public File getFile(){return this.file_to_download;}
    public String getHash(){return this.md5;}
    public int getPart(){return this.part;}
    
}
