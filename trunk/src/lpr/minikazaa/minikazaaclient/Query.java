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
    private String body_q;
    private Answer body_a;
    private NodeInfo id_origin;
    private NodeInfo receiver;
    
    public Query(){
        //Inizialize variables
        this.sender = null;
        this.body_a = null;
        this.body_q = null;
        this.receiver = null;
        this.id_origin = null;
    }
    
    public void setAskingQuery(String query){this.body_q = query;}
    public void setAnswerQuery(Answer a){this.body_a = a;}
    public void setSender(NodeInfo s){this.sender = s;}
    public void setReceiver(NodeInfo r){this.receiver = r;}
    public void setOrigin(NodeInfo o){this.id_origin = o;}
    
    public String getBodyQ(){return this.body_q;}
    public Answer getBodyA(){return this.body_a;}
    public NodeInfo getSender(){return this.sender;}
    public NodeInfo getReceiver(){return this.receiver;}
    public NodeInfo getOrigin(){return this.id_origin;}
}
