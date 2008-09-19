/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.bootstrap;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.UIManager;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 11-set-2008
 * @file BootStrapService.java
 */
public class BootStrapService {

    public static void main(String[] args) {

        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (Exception ex) {
        }

        try {
            Registry registry = LocateRegistry.createRegistry(2008);
            System.out.println("Registry init.");

            BootStrapGui g = new BootStrapGui();
            
            //Frame appears in the center of the screen
            g.setLocationRelativeTo(null);
            
            g.setVisible(true);

            BootStrapServer bss = new BootStrapServer(g);

            BootStrapServerInterface stub = (BootStrapServerInterface) UnicastRemoteObject.exportObject(bss, 0);

            registry.bind("BootStrap", stub);

        } catch (Exception e) {
            System.err.println("BootStrapService: " + e);
        }
    }
}
