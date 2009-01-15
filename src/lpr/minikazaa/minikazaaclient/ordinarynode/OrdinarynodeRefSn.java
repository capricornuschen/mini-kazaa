package lpr.minikazaa.minikazaaclient.ordinarynode;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.SupernodeList;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 14-gen-2009
 * @file OrdinarynodeRefSn.java
 */
public class OrdinarynodeRefSn implements Observer {

    private Socket my_sn;
    private NodeInfo best_sn;
    private int num_query;

    public OrdinarynodeRefSn() {
        this.num_query = 0;
    }

    public void setSocket(InetAddress ia_node, int port) {
        try {
            this.my_sn = new Socket(ia_node, port);
        } catch (IOException ex) {
            this.my_sn = null;
            System.out.println("Unable to set socket connection to " + ia_node.toString() + ":" + port);
        }
    }

    public void setNodeInfo(NodeInfo best) {
        this.best_sn = best;
    }

    public Socket getSocket() {
        return this.my_sn;
    }

    public int getNumQuery() {
        return this.num_query;
    }

    public NodeInfo getBestSn() {
        return this.best_sn;
    }

    public void increaseNumQuery() {
        this.num_query++;
    }

    public void print() {
        System.out.println("My supernode: " + this.my_sn.toString());
        System.out.println("My queries to my supernode: " + this.num_query);
    }

    public void update(Observable o, Object arg) {
        if (o instanceof SupernodeList) {
            SupernodeList list = (SupernodeList) o;
            if (this.my_sn == null) {
                NodeInfo best = list.getBest();
                try {
                    this.my_sn = new Socket(best.getIaNode(), best.getDoor());
                    this.best_sn = best;
                    try {
                        ObjectOutputStream output_object = new ObjectOutputStream(this.my_sn.getOutputStream());
                        OrdinarynodeFriendRequest friend_request = new OrdinarynodeFriendRequest();
                        output_object.writeObject(friend_request);
                    } catch (IOException ex) {
                        Logger.getLogger(OrdinarynodeRefSn.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    this.my_sn = null;
                    System.out.println("Unable to set socket connection to " + best.getIaNode().toString() + ":" + best.getDoor());
                }
            }
        }
    }
}
