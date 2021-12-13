import java.net.*;
import java.io.*;

class clienteTCP {
	public static void main(String[] args) {
		try {
			Socket upvSocket = new Socket("www.upv.es", 80);
			System.out.println("Connected");
		} catch (IOException e) {
			System.out.println("Unknown host, conection aborted");
		}
	}
}