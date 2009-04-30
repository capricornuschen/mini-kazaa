package lpr.minikazaa.minikazaaclient;

import java.io.Serializable;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.ordinarynode.OrdinarynodeFiles;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 3-ott-2008
 * @file Query.java
 */
public class Query implements Serializable, Cloneable{

    private NodeInfo sender;            //NodeInfo del nodo che invia.
    private String body_q;              //Regex of a sended query
    private Answer body_a;              //Answer query
    private OrdinarynodeFiles body_f;   //Notify a supernode to have many
    private NodeInfo id_origin;         //Origine della query
    private NodeInfo receiver;          //NodeInfo del nodo che riceve
    private int ttl;                    //Time to live della query
    private int id;                     //Id dell' origionario delle query
    
    public Query(){
        //Inizializzazione variabele
        this.sender = null;
        this.body_a = null;
        this.body_q = null;
        this.body_f = null;
        this.receiver = null;
        this.id_origin = null;
    }
    
    public void setAskingQuery(String query){this.body_q = query;}
    public void setAnswerQuery(Answer a){this.body_a = a;}
    public void setFilesQuery(OrdinarynodeFiles f){this.body_f = f;}
    public void setSender(NodeInfo s){this.sender = s;}
    public void setReceiver(NodeInfo r){this.receiver = r;}
    public void setOrigin(NodeInfo o){this.id_origin = o;}
    public void setTTL(int n){this.ttl = n;}
    public void setId(int x){this.id = x;}
    public void decrementTTL(){this.ttl -= 1;}
    
    public String getBodyQ(){return this.body_q;}
    public Answer getBodyA(){return this.body_a;}
    public OrdinarynodeFiles getBodyF(){return this.body_f;}
    public NodeInfo getSender(){return this.sender;}
    public NodeInfo getReceiver(){return this.receiver;}
    public NodeInfo getOrigin(){return this.id_origin;}
    public int getTTL(){return this.ttl;}
    public int getId(){return this.id;}
}
