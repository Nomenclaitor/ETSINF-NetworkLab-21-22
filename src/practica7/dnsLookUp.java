package practica7;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class dnsLookUp {
    public static void main(String[] args) {
        try {
            InetAddress ipAddress = InetAddress.getByName("www.upv.es");
            System.out.println(ipAddress.toString());
            ipAddress = InetAddress.getByName("www.eltiempo.es");
            System.out.println(ipAddress.toString());
        } catch (IOException e) {
            System.out.println(e.getCause() + " " + e.getMessage());
        }
    }
}
