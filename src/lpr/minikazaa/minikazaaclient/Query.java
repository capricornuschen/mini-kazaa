/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient;

import java.io.Serializable;
import lpr.minikazaa.bootstrap.NodeInfo;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 3-ott-2008
 * @file Query.java
 */
public class Query implements Serializable{
    private NodeInfo sender;
    private Object body;
    private NodeInfo receiver;
    
    public Query(){
        //Empty constructor
    }
    
    public void setAskingQuery(String query){this.body = query;}
    public void setAnswerQuery(Answer a){this.body = a;}
    public void setSender(NodeInfo s){this.sender = s;}
    public void setReceiver(NodeInfo r){this.receiver = r;}
    
    public Object getBody(){return this.body;}
    public NodeInfo getSender(){return this.sender;}
    public NodeInfo getReceiver(){return this.receiver;}
}
