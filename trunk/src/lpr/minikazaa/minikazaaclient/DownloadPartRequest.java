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
 * @file DownloadPartRequest.java
 */
public class DownloadPartRequest implements Serializable{
    private String file_request;
    private int part_n;
    
    public DownloadPartRequest(
            String file,
            int part_n){
    
        //Initialize the download request.
        this.file_request = file;
        this.part_n = part_n;
    }
    
    public String getFile(){return this.file_request;}
    public int getPart(){return this.part_n;}
}
