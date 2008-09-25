/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.minikazaaclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 24-set-2008
 * @file NodePing.java
 */
public class NodePing implements Runnable {
    
    private SupernodeList my_sn_list;

    private InetAddress host_ia;
    private int host_port;
    
    public NodePing(InetAddress host, int port, SupernodeList official_list){
        this.host_ia = host;
        this.host_port = port;
        this.my_sn_list = official_list;
    }
    
    public void run() {

        DatagramSocket my_datagram_socket = null;

        try {
            my_datagram_socket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(NodePing.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        byte [] data = new byte[32];
        
        DatagramPacket pack = new DatagramPacket(data,data.length, host_ia, host_port);
        
        //Preparing package
        pack.setData(data,0,data.length);
        pack.setLength(data.length);
        
        long start_time = System.currentTimeMillis();
        
        try {
            my_datagram_socket.send(pack);
        } catch (IOException ex) {
            Logger.getLogger(NodePing.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            my_datagram_socket.receive(pack);
        } catch (IOException ex) {
            Logger.getLogger(NodePing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        long arrive_time = System.currentTimeMillis();
        
        long ping = arrive_time - start_time;
        
        this.my_sn_list.refreshPing(host_ia, host_port, ping);
        
    }
}
