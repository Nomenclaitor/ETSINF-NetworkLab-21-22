package practica5;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.Scanner;

public class serverFst {
    public static void main(String[] args) {
        try {
            ServerSocket defaultServSocket = new ServerSocket(7777);
            while (true) {
                Socket connectionSocket = defaultServSocket.accept();
                System.out.println("Client connected");
                //
                //
                Scanner clientSocketIn = new Scanner(connectionSocket.getInputStream());
                PrintWriter clientSocketOut = new PrintWriter(connectionSocket.getOutputStream());
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
