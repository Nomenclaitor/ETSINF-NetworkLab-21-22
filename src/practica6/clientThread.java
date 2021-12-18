package practica6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.IOException;


public class clientThread extends Thread{
    // Thread ID to identify one thread from another
    int threadId;
    private Socket clientSocket;
    // Scanner to receive data from the client
    private Scanner socketIn;
    // PrintWriter to send data to the client
    private PrintWriter socketOut;

    /**
     * Method used to create a thread
     * @param threadId ID to assign to the thread
     * @param clientSocket  Socket used to connect with the client
     */
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

    /**
     * Contains everything the thread will perform
     */
    public void run() {
        socketOut.println("Echo line: ");
        socketOut.flush();
        String clientInput = socketIn.nextLine();
        // The server will keep sending echoing the client until the client
        // sends ./0Exit
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

    @Deprecated
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
