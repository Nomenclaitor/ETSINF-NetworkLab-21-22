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
            // Creation of UDP Socket with port number: 7777
            DatagramSocket clientSocket = new DatagramSocket(7777);

            // Gets the IP address of the PC
            InetAddress dir = InetAddress.getByName("localHost");
            String outString = keyboard.nextLine();

            // Creation and value assignment of the buffer which will be sent
            // using "clientSocket"
            byte[] buffer = outString.getBytes(StandardCharsets.UTF_8);

            // Converts "buffer" int a UDP datagram
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, dir, 7777);
            clientSocket.send(packet);


            // Receive data
            //Ej4
            // Reception of data
            clientSocket.receive(packet);

            // Converts the Datagram (packet) into a String
            String answer = new String (packet.getData(), 0, packet.getLength());
            System.out.println(answer);

            // CLoses the UDP Socket
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
