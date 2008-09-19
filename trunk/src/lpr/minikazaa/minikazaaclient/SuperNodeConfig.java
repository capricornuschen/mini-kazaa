/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient;

import java.io.Serializable;

/**
 * @author giovine
 */
public class SuperNodeConfig extends Object implements Serializable {

    private String user_name;
    private int port;
    private String bootstrap_address;
    private int max_conn;
    private int ttl;
    
    
    //Get methods
    public String getUserName(){
        return user_name;
    }
    public int getPort(){
        return port;
    }
    public String getBootStrapAddress(){
        return bootstrap_address;
    }
    public int getMaxConnection(){
        return max_conn;
    }
    public int getTimeToLeave(){
        return ttl;
    }
    
    
    //Set methods
    public void setUserName(String un){
        this.user_name = un;
    }
    public void setPort(int p){
        this.port = p;
    }
    public void setBootStrapAddress(String bsa){
        this.bootstrap_address = bsa;
    }
    public void setMaxConnection(int max_c){
        this.max_conn = max_c;
    }
    public void setTimeToLeave(int time){
        this.ttl = time;
    }
}
