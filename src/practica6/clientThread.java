package practica6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.IOException;


public class clientThread extends Thread{
    int threadId;
    private Socket clientSocket;
    private Scanner socketIn;
    private PrintWriter socketOut;
    public clientThread(int threadId, Socket clientSocket) {
        this.threadId = threadId;
        this.clientSocket = clientSocket;
        try {
            socketOut = new PrintWriter(clientSocket.getOutputStream());
            socketIn = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            System.out.println(e.getCause() + " " + e.getMessage());
        }
    }

    public void run() {
        socketOut.println("Echo line: ");
        socketOut.flush();
        String clientInput = socketIn.nextLine();
        while (!clientInput.equals("./0Exit")) {
            socketOut.println(clientInput);
            socketOut.println("Anything more?");
            socketOut.flush();
            clientInput = socketIn.nextLine();
        }
        try {
            clientSocket.close();
            System.out.println("Client disconnected");
        } catch (IOException e) {
            System.out.println("Failed closing connection with client");
        }
    }

    public void echo() {
        String inputString = socketIn.nextLine();
        while(true) {
            if (!inputString.equals("./exit")) {
                socketOut.println("You said: " + inputString + "\n");
                socketOut.flush();
                inputString = socketIn.nextLine();
            } else {
                break;
            }
        }
    }


}
