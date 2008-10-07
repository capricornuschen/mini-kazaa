/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpr.minikazaa.minikazaaclient.supernode;

import java.util.ArrayList;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.Query;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 6-ott-2008
 * @file SupernodeQueryList.java
 */
public class SupernodeQueryList {
    private ArrayList <Query> query_list;
    
    public SupernodeQueryList(){this.query_list = new ArrayList();}
    
    public boolean isIn(Query external_query){
        for(Query q : this.query_list){
            if(q.getOrigin().theSame(external_query.getOrigin())&&
               q.getBodyQ().equals(external_query.getBodyQ()))
                return true;
        }
        return false;
    }
    
    public void addQuery(Query q){
        this.query_list.add(q);
    }
    
    public void removeQuery(Query q){
        int i = 0;
        for(Query x : query_list){
            if((x.getBodyQ().equals(q.getBodyQ()))&&
                x.getOrigin().theSame(q.getOrigin()))
            {
                query_list.remove(i);
            }   
            i++;
        }
    }
    
    public Query getRelativeQuery(Query q){
        int i = 0;
        for(Query x : this.query_list){
            if((x.getBodyQ().equals(q.getBodyQ()))&&
                x.getOrigin().theSame(q.getOrigin()))
            {
                return x;
            }   
            i++;
        }
        return null;
    }
    
    public ArrayList <Query> generateQueryList
            (Query q, ArrayList <NodeInfo> list){
        
        ArrayList <Query> q_list = new ArrayList();
        
        for(NodeInfo n : list){
               Query new_q = new Query();
               
               new_q.setAnswerQuery(q.getBodyA());
               new_q.setAskingQuery(q.getBodyQ());
               new_q.setOrigin(q.getOrigin());
               new_q.setReceiver(n);
               new_q.setSender(q.getReceiver());
               
               q_list.add(new_q);
        }
        
        return q_list;
    }
}
