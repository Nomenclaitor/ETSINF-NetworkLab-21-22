package practica7;

import java.net.DatagramSocket;
import java.net.SocketException;

public class datagramSocketPort {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            System.out.println(clientSocket.getLocalPort());
        } catch(SocketException e) {
            System.out.println(e.getMessage() + " " + e.getCause());
        }
    }
}
