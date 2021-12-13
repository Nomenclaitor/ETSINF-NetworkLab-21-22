package practica6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class clientOutThread extends Thread{
    int threadId;
    private final Socket clientSocket;
    private final Scanner keyboard = new Scanner(System.in);
    private PrintWriter socketOut;

    public clientOutThread(int threadId, Socket clientSocket) {
        this.threadId = threadId;
        this.clientSocket = clientSocket;
        try {
            socketOut = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getCause() + " " + e.getMessage());
        }
    }

    public void run() {
        System.out.println("Client speaking");
        try {
            String keyboardIn = keyboard.nextLine();
            while (true) {
                if (!keyboardIn.equals("quit")) {
                    socketOut.println("Qiyao: " + keyboardIn);
                    keyboardIn = keyboard.nextLine();
                } else {
                    socketOut.println(keyboardIn);
                    clientSocket.close();
                    break;
                }
                socketOut.flush();
            }
        } catch (IOException e) {
            System.out.println("Socket closing error");
        }
    }


}
