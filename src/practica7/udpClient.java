package practica7;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class udpClient {
    public static void main(String[] args) {
        try {
            Scanner keyboard = new Scanner(System.in);

            // Send data
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress dir = InetAddress.getByName("localHost");
            String outString = keyboard.nextLine();
            byte[] buffer = new byte[512];
            buffer = outString.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, dir, 7777);
            clientSocket.send(packet);


            // Receive data
            //Ej4
            clientSocket.receive(packet);
            String answer = new String (packet.getData(), 0, packet.getLength());
            System.out.println(answer);
            clientSocket.close();
        } catch (SocketException e) {
            System.out.println(e.getMessage() + " " + e.getCause());
        } catch (UnknownHostException e) {
            System.out.println("Host unknown");
        } catch (IOException e) {
            System.out.println("Error receiving / sending data");
        }
    }
}
