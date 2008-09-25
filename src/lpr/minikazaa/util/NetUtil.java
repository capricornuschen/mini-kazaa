/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpr.minikazaa.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 *
 * @author Andrea Di Grazia, Massimiliano Giovine
 * @date 22-set-2008
 * @file NetUtil.java
 */
public class NetUtil {

    public static InetAddress getAddress() throws SocketException {
        try {
            NetworkInterface ni = NetworkInterface.getByName("eth0");

            Enumeration<InetAddress> ia_en = ni.getInetAddresses();

            //We use only ipv4 address.    
            InetAddress ipv6_addr = ia_en.nextElement();
            System.out.println(ipv6_addr.toString());
            InetAddress ipv4_addr = ia_en.nextElement();
            System.out.println(ipv4_addr.toString());

            return ipv4_addr;


        } catch (NullPointerException ex0) {
            try {
                NetworkInterface ni = NetworkInterface.getByName("eth1");

                Enumeration<InetAddress> ia_en = ni.getInetAddresses();

                //We use only ipv4 address.    
                InetAddress ipv6_addr = ia_en.nextElement();
                System.out.println(ipv6_addr.toString());
                InetAddress ipv4_addr = ia_en.nextElement();
                System.out.println(ipv4_addr.toString());

                return ipv4_addr;
            } catch (NullPointerException ex1) {
                try {
                    NetworkInterface ni = NetworkInterface.getByName("eth2");

                    Enumeration<InetAddress> ia_en = ni.getInetAddresses();

                    //We use only ipv4 address.    
                    InetAddress ipv6_addr = ia_en.nextElement();
                    System.out.println(ipv6_addr.toString());
                    InetAddress ipv4_addr = ia_en.nextElement();
                    System.out.println(ipv4_addr.toString());

                    return ipv4_addr;
                    
                } catch (NullPointerException ex2) {
                    return null;
                }
            }
        }
        
    }
}
