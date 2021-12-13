package practica5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class serverSnd {
    public static void main(String[] args) {
        try {
            ServerSocket defaultServSocket = new ServerSocket(7780);
            while (true) {
                Socket connectionSocket = defaultServSocket.accept();
                System.out.println("Client connected");
                //
                //
                Scanner clientSocketIn = new Scanner(connectionSocket.getInputStream());
                PrintWriter clientSocketOut = new PrintWriter(connectionSocket.getOutputStream());
                //
                //

                clientSocketOut.println("Entry socket: " + defaultServSocket.getInetAddress());
                clientSocketOut.println("Entry socket port: " + defaultServSocket.getLocalPort() + "\n");
                clientSocketOut.println("Current connection socket: " + connectionSocket.getInetAddress());
                clientSocketOut.println("Current connection port: " + connectionSocket.getPort());
                clientSocketOut.flush();

                //
                //
                String clientMessage = clientSocketIn.nextLine();
                if (clientMessage.length() <2 ) {
                    clientSocketOut.println("Write something useful idiot");
                    clientSocketOut.flush();
                } else {
                    clientSocketOut.println(clientMessage);
                    clientSocketOut.flush();
                }
                connectionSocket.close();
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage() + " " + e.getCause());
        }
    }
}
