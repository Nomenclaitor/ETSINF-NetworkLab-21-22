import java.net.*;
import java.io.*;
import java.util.Scanner;

class clientDayTime {
	public static void main(String[] args) {
		try {
			Socket zoltarSocket = new Socket("zoltar.redes.upv.es", 13);
			Scanner socketIn = new Scanner(zoltarSocket.getInputStream());
			System.out.println("Connected to zoltar at: " + socketIn.nextLine());
		} catch (IOException e) {
			System.out.println("IOException occurred");
		}
	}
}