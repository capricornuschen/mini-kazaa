/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.util;

import java.io.Serializable;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 21-nov-2008
 * @file MKFileDescriptor.java
 */
public class MKFileDescriptor implements Serializable{
    
    private String file_name;
    private String md5;
    private long size;
    private String absolute_owner_path;
    
    public MKFileDescriptor(
            String fn,
            String code,
            long s,
            String path
            ){
        this.file_name = fn;
        this.md5 = code;
        this.size = s;
        this.absolute_owner_path = path;
    }
    
    public String getFileName(){return this.file_name;}
    public String getMd5(){return this.md5;}
    public long getSize(){return this.size;}
    public String getPath(){return this.absolute_owner_path;}
}
