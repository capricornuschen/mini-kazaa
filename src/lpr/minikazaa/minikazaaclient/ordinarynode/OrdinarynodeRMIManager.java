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
import lpr.minikazaa.bootstrap.BootstrapRMIWrapper;
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
public class OrdinarynodeRMIManager implements Runnable {

    private NodeConfig my_conf;
    private NodeInfo my_infos;
    private SupernodeList sn_list;
    private Socket sn_connection;

    private BootstrapRMIWrapper rmi_stub;
    

    public OrdinarynodeRMIManager(
            NodeConfig conf,
            NodeInfo info,
            SupernodeList list,
            BootstrapRMIWrapper rmi
            ) {
        this.my_conf = conf;
        this.my_infos = info;
        this.sn_list = list;
        this.rmi_stub = rmi;
        
    }

    public void run() {
        Registry bootstrap_service;

        BootStrapServerInterface callbacks_remote;

        SupernodeCallbacksInterface callbacks_stub;

        try {

            System.out.println("BootStrapAddress: " + my_conf.getBootStrapAddress() + "\nPort: 2008");
            bootstrap_service = LocateRegistry.getRegistry(my_conf.getBootStrapAddress(), 2008);

            System.out.println("Client Registry:" + bootstrap_service.toString());

            //Logical division of remote porcedure calls.
            rmi_stub.setStub((BootStrapServerInterface) bootstrap_service.lookup("BootStrap"));
            callbacks_remote = (BootStrapServerInterface) bootstrap_service.lookup("BootStrap");

            ArrayList<NodeInfo> ni_list = rmi_stub.getStub().getSuperNodeList();

            System.out.println("List of node info: ");
            Iterator l = ni_list.iterator();
            while (l.hasNext()) {
                System.out.println("-" + l.next().toString());
            }

            sn_list.refreshList(ni_list);
            //Refreshing pings.
            sn_list.refreshPing();
           

            //Managing callbacks.
            SupernodeCallbacksImpl callback_obj = new SupernodeCallbacksImpl(this.sn_list, this.my_conf);
            callbacks_stub = (SupernodeCallbacksInterface) UnicastRemoteObject.exportObject(callback_obj, 0);


            try {
                my_infos.setInetAddress(InetAddress.getByName(my_conf.getMyAddress()));
                my_infos.setDoor(my_conf.getPort());
                my_infos.setCallbacksInterface(callbacks_stub);
                my_infos.setIsSn(my_conf.getIsSN());
                my_infos.setId(this.my_conf.getMyAddress()+":"+this.my_conf.getPort());
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
