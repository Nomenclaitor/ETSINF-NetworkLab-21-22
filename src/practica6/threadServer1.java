package practica6;

import jdk.jshell.spi.ExecutionControl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class threadServer1 {
    public static void main(String[] args) {
        try {
            ServerSocket beaconSocket = new ServerSocket(8000);
            while(true) {
                Socket connectionSocket = beaconSocket.accept();
                System.out.println("Client connected");
                String socketString = connectionSocket.getInetAddress().getHostAddress().replace(".", "");
                clientThread client = new clientThread(Integer.parseInt(socketString), connectionSocket);
                client.start();
            }
        } catch (IOException e) {
            System.out.println("ServerSocket error:  " + e.getCause());
        }
    }
}
