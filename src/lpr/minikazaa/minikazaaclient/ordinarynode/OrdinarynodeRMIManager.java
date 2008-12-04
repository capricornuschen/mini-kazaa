package lpr.minikazaa.minikazaaclient.ordinarynode;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import lpr.minikazaa.bootstrap.BootStrapServerInterface;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.NodeConfig;
import lpr.minikazaa.minikazaaclient.SupernodeList;
import lpr.minikazaa.minikazaaclient.supernode.SupernodeCallbacksImpl;
import lpr.minikazaa.minikazaaclient.supernode.SupernodeCallbacksInterface;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 13-nov-2008
 * @file OrdinarynodeRMIManager.java
 */
public class OrdinarynodeRMIManager implements Runnable{

    NodeConfig my_conf;
    NodeInfo my_infos;
    SupernodeList sn_list;
    Socket sn_connection;
    NodeInfo best_sn;

    public OrdinarynodeRMIManager(NodeConfig conf, NodeInfo info, SupernodeList list, Socket sock, NodeInfo best){
        this.my_conf = conf;
        this.my_infos = info;
        this.sn_list = list;
        this.sn_connection = sock;
        this.best_sn = best;
    }

    public void run() {
        Registry bootstrap_service;
        BootStrapServerInterface rmi_stub;
        SupernodeCallbacksInterface callbacks_stub;
        BootStrapServerInterface callbacks_remote;

        try {

            System.out.println("BootStrapAddress: "+my_conf.getBootStrapAddress()+"\nPort: 2008");
            bootstrap_service = LocateRegistry.getRegistry(my_conf.getBootStrapAddress(),2008);

            //Logical division of remote porcedure calls.
            rmi_stub = (BootStrapServerInterface) bootstrap_service.lookup("BootStrap");
            callbacks_remote = (BootStrapServerInterface) bootstrap_service.lookup("BootStrap");

            ArrayList<NodeInfo> ni_list = rmi_stub.getSuperNodeList();

            System.out.println("List of node info: ");
            Iterator l = ni_list.iterator();
            while(l.hasNext()){
                System.out.println("-"+l.next().toString());
            }

            sn_list.refreshList(ni_list);
            //Refreshing pings.
            sn_list.refreshPing();

            //Istantiate socket to the best supernode.
            best_sn = sn_list.getBest();
            try {
                this.sn_connection = new Socket(best_sn.getIaNode().toString().substring(1), best_sn.getDoor());
            } catch (UnknownHostException ex) {
                Logger.getLogger(OrdinarynodeRMIManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(OrdinarynodeRMIManager.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Managing callbacks.
            SupernodeCallbacksImpl callback_obj = new SupernodeCallbacksImpl(this.sn_list, this.my_conf);
            callbacks_stub = (SupernodeCallbacksInterface) UnicastRemoteObject.exportObject(callback_obj, 0);


            try {
                my_infos = new NodeInfo(InetAddress.getByName(my_conf.getMyAddress()), my_conf.getPort(), callbacks_stub,my_conf.getIsSN());
            } catch (UnknownHostException ex) {
                Logger.getLogger(OrdinarynodeRMIManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            callbacks_remote.addOrdinaryNode(my_infos);

        } catch (RemoteException ex) {
            OrdinarynodeWarning snw = new OrdinarynodeWarning("Can't find bootstrap server.", "bs_address", my_conf);
            snw.setLocationRelativeTo(null);
            snw.setVisible(true);
        } catch (NotBoundException ex) {
            OrdinarynodeWarning snw = new OrdinarynodeWarning("Can't find bootstrap server.", "bs_address", my_conf);
            snw.setLocationRelativeTo(null);
            snw.setVisible(true);
        }
    }

}
