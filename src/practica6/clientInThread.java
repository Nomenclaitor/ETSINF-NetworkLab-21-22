package practica6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class clientInThread extends Thread{
    int threadId;
    private final Socket clientSocket;
    private Scanner socketIn;

    public clientInThread(int threadId, Socket clientSocket) {
        this.threadId = threadId;
        this.clientSocket = clientSocket;
        try {
            socketIn = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            System.out.println(e.getCause() + " " + e.getMessage());
        }
    }

    public void run() {
        try {
            System.out.println("Client listening");
            String serverMessage = socketIn.nextLine();
            while (!serverMessage.equals("quit")) {
                System.out.println(serverMessage);
                serverMessage = socketIn.nextLine();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Disconnection from server");
            try {
                clientSocket.close();
            } catch (IOException e1) {
                System.out.println("Socket close error");
            }
        }
    }


}
