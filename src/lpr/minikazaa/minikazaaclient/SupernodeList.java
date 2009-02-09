/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.minikazaaclient;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lpr.minikazaa.bootstrap.NodeInfo;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 19-set-2008
 * @file SupernodeList.java
 */
public class SupernodeList extends Observable {

    private ArrayList<NodeInfo> sn_list;
    private ArrayList<NodeInfo> sub_set_list;

    public SupernodeList() {
        this.sn_list = new ArrayList();
        this.sub_set_list = null;
    }

    public void print() {
        for (NodeInfo info : this.sn_list) {
            System.out.println("Nodo: " + info.getId());
        }
    }

    public synchronized void refreshList(ArrayList<NodeInfo> list) {
        this.sn_list = list;

        this.setChanged();
        this.notifyObservers();
    }

    public synchronized void addNewNode(NodeInfo node) {
        this.sn_list.add(node);

        this.setChanged();
        this.notifyObservers();
    }

    public synchronized void removeOldNode(NodeInfo node) {
        this.sn_list.remove(node);

        this.setChanged();
        this.notifyObservers();
    }

    public synchronized ArrayList<NodeInfo> getList() {
        return this.sn_list;
    }

    public synchronized void refreshPing(InetAddress ia, int port, long new_ping) {
        for (NodeInfo n : sn_list) {
            //Now we compare the 2 toString methods, comparing 2 strings.
            if (n.getIaNode().toString().equals(ia.toString())) {
                if (n.getDoor() == port) {
                    n.setPing(new_ping);
                }
            }
        }
    }

    public synchronized void refreshPing() {
        //Thread pool
        ThreadPoolExecutor my_thread_pool =
                new ThreadPoolExecutor(10, 15, 50000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        if (this.sn_list.size() >= 1) {
            for (NodeInfo n : sn_list) {
                NodePing pinging = new NodePing(n.getIaNode(), n.getDoor(), this);

                my_thread_pool.execute(pinging);
            }
        }
        my_thread_pool.shutdown();

        this.setChanged();
        this.notifyObservers();
    }

    public synchronized void subSet(int set_size, long threshold) {


        ArrayList<NodeInfo> neighbors = new ArrayList();

        for (NodeInfo n : this.sn_list) {
            if (n.getPing() != -1) {

                if (n.getPing() <= threshold) {
                    neighbors.add(n);
                    if (neighbors.size() == set_size) {
                        this.sub_set_list = neighbors;
                    }
                }
            }
        }

        this.sub_set_list = neighbors;
    }

    public synchronized ArrayList<NodeInfo> getSubSet() {

        if (this.sub_set_list == null) {
            subSet(10, 100);
        }

        return this.sub_set_list;
    }

    public synchronized NodeInfo getBest() {
        NodeInfo best = new NodeInfo();

        System.out.println("DEBUG: getbest() snlist.size = "+this.sn_list.size());

        for (NodeInfo candidate : this.sn_list) {
            if (best.getIaNode() == null) {
                best.setInetAddress(candidate.getIaNode());
                best.setCallbacksInterface(candidate.getCallbackInterface());
                best.setDoor(candidate.getDoor());
                best.setId(candidate.getId());
                best.setUsername(candidate.getUsername());
                best.setPing(candidate.getPing());
            } else {
                if (candidate.getPing() < best.getPing()) {
                    best.setInetAddress(candidate.getIaNode());
                    best.setCallbacksInterface(candidate.getCallbackInterface());
                    best.setDoor(candidate.getDoor());
                    best.setId(candidate.getId());
                    best.setUsername(candidate.getUsername());
                    best.setPing(candidate.getPing());
                }
            }
        }
        System.out.println("DEBUG: best found "+best.getId());
        return best;
    }
}
