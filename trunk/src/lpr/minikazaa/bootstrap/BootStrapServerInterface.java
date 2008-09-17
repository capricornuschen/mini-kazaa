

package lpr.minikazaa.bootstrap;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 11-set-2008
 * @file BootStrapServerInterface.java
 */
public interface BootStrapServerInterface extends Remote{
    public boolean addSuperNode(NodeInfo new_node) throws RemoteException;
    public boolean removeSuperNode(NodeInfo new_node) throws RemoteException;
    public ArrayList<NodeInfo> getSuperNodeList() throws RemoteException;
    
}
