package practica5;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.Scanner;

public class serverFst {
    public static void main(String[] args) {
        try {
            // Initial connection Socket to the server
            ServerSocket beaconSocket = new ServerSocket(7777);
            while (true) {
                //Creation of the Socket used to communicate with the client
                Socket connectionSocket = beaconSocket.accept();
                System.out.println("Client connected");

                // Creating a Scanner to listen for incoming data from the client
                Scanner clientSocketIn = new Scanner(connectionSocket.getInputStream());
                // Creating a PrintWriter to send data to the client
                PrintWriter clientSocketOut = new PrintWriter(connectionSocket.getOutputStream());
                String clientMessage = clientSocketIn.nextLine();
                if (clientMessage.length() <2 ) {
                    clientSocketOut.println("Write something useful idiot");

                    //Sends the data immediately (forces any buffered data to be sent immediately)
                    clientSocketOut.flush();
                } else {
                    clientSocketOut.println(clientMessage);
                    clientSocketOut.flush();
                }

                // Closes the Socket used to communicate with the client
                connectionSocket.close();
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage() + " " + e.getCause());
        }
    }
}
