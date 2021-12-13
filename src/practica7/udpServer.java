package practica7;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class udpServer {
    public static void main(String[] args) {
        try {
            DatagramSocket udpSocket = new DatagramSocket(7777);
            DatagramPacket pack = new DatagramPacket(new byte[512], 512);
            while(true) {
                udpSocket.receive(pack);
                String auxString = new String(pack.getData(), 0, pack.getLength());
                auxString += " received at:  " + getTime();
                InetAddress ipAddress = pack.getAddress();
                byte[] buffer = auxString.getBytes(StandardCharsets.UTF_8);
                udpSocket.send(new DatagramPacket(buffer, buffer.length, ipAddress, 7777));

            }
        } catch (SocketException e) {
            System.out.println("Socket exception");
        } catch (IOException e) {
            System.out.println("Error receiving data");
        }
    }

    public static String getTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dTFormatter = DateTimeFormatter.ofPattern("E, dd/MM/yyyy HH:mm:ss");
        return dateTime.format(dTFormatter);
    }
}
