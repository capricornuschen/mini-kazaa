/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.minikazaaclient.supernode;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import lpr.minikazaa.bootstrap.BootStrapServerInterface;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.NodeConfig;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 17-set-2008
 * @file SupernodeEngine.java
 */
public class SupernodeEngine implements Runnable {

    NodeConfig my_conf;
    ArrayList<NodeInfo> super_node_list;
    Registry bootstrap_service;

    public SupernodeEngine(NodeConfig conf) {
        this.my_conf = conf;
    }

    public void run() {
        System.out.println("Thread Super node engine init.");
        BootStrapServerInterface stub;
        //Init the ArrayList with a list of super close super nodes.
        try {
            bootstrap_service = LocateRegistry.getRegistry(my_conf.getBootStrapAddress());

            stub = (BootStrapServerInterface) bootstrap_service.lookup("BootStrap");

            

        } catch (RemoteException ex) {
            SupernodeWarning snw = new SupernodeWarning("Can't find bootstrap server.", "bs_address", my_conf);
            snw.setLocationRelativeTo(null);
            snw.setVisible(true);
        } catch (NotBoundException ex) {
            SupernodeWarning snw = new SupernodeWarning("Can't find bootstrap server.", "bs_address", my_conf);
            snw.setLocationRelativeTo(null);
            snw.setVisible(true);
        }

    }
}
