import java.net.*;
import java.io.*;
import java.util.Scanner;

class clienteEco {
	public static void main(String[] args) {
		try {
			Socket zoltarSocket = new Socket("zoltar.redes.upv.es", 13);
			Socket echoZoltarSocket = new Socket("zoltar.redes.upv.es", 7);
			Scanner zoltarScanner = new Scanner(zoltarSocket.getInputStream());
			System.out.println("Connected to Zoltar at :" + zoltarScanner.nextLine() + 
					" port: " + zoltarSocket.getPort());
			
			Scanner keyboard = new Scanner(System.in);
			Scanner echoZoltarIn = new Scanner(echoZoltarSocket.getInputStream());
			PrintWriter echoZoltarOut = new PrintWriter(echoZoltarSocket.getOutputStream());
			
			echoZoltarOut.print("Hola Zoltar\r\n");
			echoZoltarOut.flush();
			System.out.println(echoZoltarIn.nextLine());
			zoltarSocket.close();
			echoZoltarSocket.close();
		} catch (IOException e) {
			System.out.println("IOException occurred");
		}
	}
}
			