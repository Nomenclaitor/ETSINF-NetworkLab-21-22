package practica6;

import jdk.jshell.spi.ExecutionControl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class threadServer1 {
    public static void main(String[] args) {
        try {
            // TCP Socket for the initial connection with teh server
            ServerSocket beaconSocket = new ServerSocket(8000);
            while(true) {
                // "Permanent" connection Socket with the client
                Socket connectionSocket = beaconSocket.accept();
                System.out.println("Client connected");
                String socketString = connectionSocket.getInetAddress().getHostAddress().replace(".", "");

                // Creation of a new thread
                clientThread client = new clientThread(Integer.parseInt(socketString), connectionSocket);
                // Starting run method in the thread
                client.start();
            }
        } catch (IOException e) {
            System.out.println("ServerSocket error:  " + e.getCause());
        }
    }
}
