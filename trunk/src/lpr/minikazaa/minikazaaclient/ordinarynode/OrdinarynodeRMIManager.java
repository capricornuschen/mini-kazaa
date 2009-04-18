package lpr.minikazaa.minikazaaclient.ordinarynode;

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
    private OrdinarynodeRefSn my_sn_ref;


    public OrdinarynodeRMIManager(
            NodeConfig conf,
            NodeInfo info,
            SupernodeList list,
            BootstrapRMIWrapper rmi,
            OrdinarynodeRefSn ref) {
        this.my_conf = conf;
        this.my_infos = info;
        this.sn_list = list;
        this.rmi_stub = rmi;
        this.my_sn_ref = ref;

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
      


            //Managing callbacks.
            SupernodeCallbacksImpl callback_obj = new SupernodeCallbacksImpl(this.sn_list, this.my_conf);
            callbacks_stub = (SupernodeCallbacksInterface) UnicastRemoteObject.exportObject(callback_obj, 0);


            try {
                my_infos.setInetAddress(InetAddress.getByName(my_conf.getMyAddress()));
                my_infos.setDoor(my_conf.getPort());
                my_infos.setCallbacksInterface(callbacks_stub);
                my_infos.setIsSn(my_conf.getIsSN());
                my_infos.setId(this.my_conf.getMyAddress()+":"+this.my_conf.getPort());
                this.my_sn_ref.setMyInfo(this.my_infos);
            } catch (UnknownHostException ex) {
                Logger.getLogger(OrdinarynodeRMIManager.class.getName()).log(Level.SEVERE, null, ex);
            }


            callbacks_remote.addOrdinaryNode(my_infos);

             sn_list.refreshList(ni_list);
             sn_list.refreshPing();

            /*NodeInfo best = this.sn_list.getBest();
            this.my_sn_ref.setSocket(best.getIaNode(), best.getDoor());
            if(this.my_sn_ref.getSocket() != null){
            this.my_sn_ref.setNodeInfo(best);
            try {
            ObjectOutputStream output_object = new ObjectOutputStream(this.my_sn_ref.getSocket().getOutputStream());
            OrdinarynodeFriendRequest friend_request  = new OrdinarynodeFriendRequest();
            output_object.writeObject(friend_request);
            } catch (IOException ex) {
            System.out.println("DEBUG: exception mentre chiedo friendship.");
            Logger.getLogger(OrdinarynodeRMIManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            }

            this.my_sn_ref.print();*/

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

