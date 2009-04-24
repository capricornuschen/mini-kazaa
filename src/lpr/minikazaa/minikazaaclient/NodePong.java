
package lpr.minikazaa.minikazaaclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 30-set-2008
 * @file NodePong.java
 */
public class NodePong implements Runnable {

    NodeConfig my_conf;

    public NodePong(NodeConfig conf) {
        this.my_conf = conf;
    }

    public void run() {
        int port = this.my_conf.getPort();
        DatagramSocket pong_sock = null;
        try {

            pong_sock = new DatagramSocket(port);
        } catch (SocketException ex) {
            Logger.getLogger(NodePong.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {

            byte[] packet = new byte[32];

            DatagramPacket pack = new DatagramPacket(packet, packet.length);

            try {

                pong_sock.receive(pack);
            } catch (IOException ex) {
                Logger.getLogger(NodePong.class.getName()).log(Level.SEVERE, null, ex);
            }
            packet = pack.getData();
            DatagramSocket send_sock = null;
            try {

                send_sock = new DatagramSocket();
            } catch (SocketException ex) {
                
            }

            byte [] send_byte = packet;
            DatagramPacket send_pack = new DatagramPacket(send_byte,send_byte.length,pack.getAddress(),pack.getPort());
            System.out.println(send_pack.toString());
            try {

                send_sock.send(send_pack);
            } catch (IOException ex) {
                Logger.getLogger(NodePong.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
