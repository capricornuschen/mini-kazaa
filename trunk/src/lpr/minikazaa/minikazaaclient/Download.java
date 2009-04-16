 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient;

import lpr.minikazaa.util.MKFileDescriptor;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 4-dic-2008
 * @file Download.java
 */
public class Download {

    private MKFileDescriptor file_to_download;
    private long downloaded_bytes;
    private String downloader_path;

    public Download(MKFileDescriptor file){
        this.file_to_download = file;
        this.downloaded_bytes = 0;
        this.downloader_path = "./downloads/";
        
    }

    public MKFileDescriptor getFile(){return this.file_to_download;}
    public long getDownloadedBytes(){return this.downloaded_bytes;}
    public String getDownloaderPath(){return this.downloader_path;}
    
    public void updateDownloadBytes(long bytes){this.downloaded_bytes += bytes;}

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Download){
            Download other = (Download) obj;

            if(this.downloader_path.equals(other.downloader_path))
                return true;

        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.downloader_path != null ? this.downloader_path.hashCode() : 0);
        return hash;
    }
}
