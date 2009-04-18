package lpr.minikazaa.minikazaaclient.ordinarynode;

import java.io.Serializable;
import lpr.minikazaa.bootstrap.NodeInfo;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 14-gen-2009
 * @file OrdinarynodeFriendRequest.java
 */
public class OrdinarynodeFriendRequest implements Serializable {

    private boolean want_to_be_friend;
    private NodeInfo friend;
    //Ancora da vedere cosa scriverci.
    public OrdinarynodeFriendRequest(){

    }
    public void setRelationship(boolean rel){this.want_to_be_friend = rel;}
    public boolean getRelationship(){return this.want_to_be_friend;}

    public void setInfo(NodeInfo info){this.friend = info;}
    public NodeInfo getInfo(){return this.friend;}
}
