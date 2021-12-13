package practica6;

import java.io.IOException;
import java.net.Socket;

public class threadClient {
    public static void main(String[] args) {
        try {
            Socket connectionSocket = new Socket("158.42.180.200", 7777);
            String connectionSocketString = connectionSocket.getInetAddress().getHostAddress().replace(".", "");
            if (connectionSocketString.length() > 10) {
                connectionSocketString = connectionSocketString.substring(0,9);
            }
            clientInThread socketInThread = new clientInThread(Integer.parseInt(connectionSocketString), connectionSocket);
            clientOutThread socketOutThread = new clientOutThread(Integer.parseInt(connectionSocketString), connectionSocket);
            socketInThread.start();
            socketOutThread.start();
        } catch (IOException e) {
            System.out.println("Socket creation error");
        }
    }
}
