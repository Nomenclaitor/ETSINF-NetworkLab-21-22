package practica5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class serverTrd {
    public static void main(String[] args) {
        try {
            ServerSocket defaultServSocket = new ServerSocket(8000);
            while (true) {
                Socket connectionSocket = defaultServSocket.accept();
                System.out.println("Client connected");

                Scanner clientSocketIn = new Scanner(connectionSocket.getInputStream());
                PrintWriter clientSocketOut = new PrintWriter(connectionSocket.getOutputStream());

                clientSocketOut.println("HTTP/1.0 200 ok");
                clientSocketOut.println("Content-Type: text/plain\r\n");
                clientSocketOut.flush();

                String clientString = clientSocketIn.nextLine();
                while(clientString.length() != 0) {
                    clientSocketOut.println(clientString);
                    clientSocketOut.flush();
                    clientString = clientSocketIn.nextLine();
                }


                connectionSocket.close();
                System.out.println("Connection closed\n");
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage() + " " + e.getCause());
        }
    }
}
