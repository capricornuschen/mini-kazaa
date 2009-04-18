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
import lpr.minikazaa.minikazaaclient.Query;
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
    private ObjectOutputStream output_object;
    private NodeInfo my_info;

    public OrdinarynodeRefSn() {
        this.num_query = 0;
        this.my_sn = null;
        this.best_sn = null;

    }

    public void send(Object obj){
        try {
            this.output_object.writeObject(obj);
        } catch (IOException ex) {
            System.out.println("Error while sending "+obj.toString());
            Logger.getLogger(OrdinarynodeRefSn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void setSocket(InetAddress ia_node, int port) {
        System.out.println("DEBUG: setSocket called.");
        try {
            this.my_sn = new Socket(ia_node, port);
        } catch (IOException ex) {
            this.my_sn = null;
            System.out.println("Unable to set socket connection to " + ia_node.toString() + ":" + port);
        }
    }

    public synchronized void setNodeInfo(NodeInfo best) {
        this.best_sn = best;
    }

    public synchronized void setMyInfo(NodeInfo info){
        this.my_info = info;
    }

    public synchronized Socket getSocket() {
        return this.my_sn;
    }

    public synchronized int getNumQuery() {
        return this.num_query;
    }

    public synchronized NodeInfo getBestSn() {
        return this.best_sn;
    }

    public synchronized NodeInfo getMyInfo(){
        return this.my_info;
    }

    public synchronized void increaseNumQuery() {
        this.num_query++;
    }

    public void print() {
        System.out.println("My supernode: " + this.my_sn.toString());
        System.out.println("My queries to my supernode: " + this.num_query);
    }

    public synchronized void update(Observable o, Object arg) {
        if (o instanceof SupernodeList) {
            SupernodeList list = (SupernodeList) o;
            System.out.println("Lista dentro update:");
            list.print();
            if ((this.my_sn == null) && (list.getList().size() > 0)) {
                System.out.println("Dimensione lista: " + list.getList().size());

                NodeInfo best = list.getBest();


                System.out.println("Best: " + best.getId());

                this.setSocket(best.getIaNode(), best.getDoor());
                this.best_sn = best;
                try {

                    this.output_object = new ObjectOutputStream(this.my_sn.getOutputStream());

                    OrdinarynodeFriendRequest friend_request = new OrdinarynodeFriendRequest();
                    friend_request.setRelationship(true);
                    friend_request.setInfo(my_info);
                    output_object.writeObject(friend_request);

                } catch (IOException ex) {
                    Logger.getLogger(OrdinarynodeRefSn.class.getName()).log(Level.SEVERE, null, ex);
                }

                this.print();

            }
        }
    }
}
