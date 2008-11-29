/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 25-nov-2008
 * @file DownloadPartResponse.java
 */
public class DownloadPartResponse {
    private byte [] part; 
    private int part_n;
    private String file;
    
    public DownloadPartResponse(
            byte [] part,
            int part_n,
            String file){
    
        this.part = part;
        this.part_n = part_n;
        this.file = file;
    }
    
    public byte [] getPart(){return this.part;}
    public int getPartN(){return this.part_n;}
    public String getFile(){return this.file;}
}
