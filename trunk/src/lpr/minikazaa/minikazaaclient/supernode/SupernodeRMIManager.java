package lpr.minikazaa.minikazaaclient.supernode;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lpr.minikazaa.bootstrap.BootStrapServerInterface;
import lpr.minikazaa.bootstrap.NodeInfo;
import lpr.minikazaa.minikazaaclient.NodeConfig;
import lpr.minikazaa.minikazaaclient.SupernodeList;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 18-set-2008
 * @file SupernodeRMIManager.java
 */
public class SupernodeRMIManager implements Runnable {

    NodeConfig my_conf;
    SupernodeList sn_list;

    public SupernodeRMIManager(NodeConfig conf, SupernodeList list) {
        this.my_conf = conf;
        this.sn_list = list;
    }

    public void run() {
        Registry bootstrap_service;
        BootStrapServerInterface rmi_stub;
        SupernodeCallbacksInterface callbacks_stub;
        BootStrapServerInterface callbacks_remote;


        try {
            
            System.out.println("BootStrapAddress: "+my_conf.getBootStrapAddress()+"\nPort: 2008");
            bootstrap_service = LocateRegistry.getRegistry(my_conf.getBootStrapAddress(),2008);

            rmi_stub = (BootStrapServerInterface) bootstrap_service.lookup("BootStrap");
            callbacks_remote = (BootStrapServerInterface) bootstrap_service.lookup("BootStrap");

            ArrayList<NodeInfo> ni_list = rmi_stub.getSuperNodeList();
            sn_list.refreshList(ni_list);

            //Managing callbacks.
            SupernodeCallbacksImpl callback_obj = new SupernodeCallbacksImpl();
            callbacks_stub = (SupernodeCallbacksInterface) UnicastRemoteObject.exportObject(callback_obj, 0);

            NodeInfo my_info = null;
            try {
                my_info = new NodeInfo(InetAddress.getByName(my_conf.getMyAddress()), my_conf.getPort(), callbacks_stub);
            } catch (UnknownHostException ex) {
                Logger.getLogger(SupernodeRMIManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {

                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SupernodeRMIManager.class.getName()).log(Level.SEVERE, null, ex);
            }

            callbacks_remote.addSuperNode(my_info);

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
