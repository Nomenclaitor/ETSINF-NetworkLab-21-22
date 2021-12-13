package practica4;

import java.net.*;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class ClienteSMTP {

        static void error(String cadena) {
		System.out.println(cadena);
		System.exit(0);
	}

	public static void main(String[] args) {
	try{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		System.setProperty ("line.separator","\r\n");
		Socket mailSocket = new Socket("serveis-rdc.redes.upv.es", 25);
		System.out.println("Conectado al servidor SMTP de serveis-rdc");
		PrintWriter socketOut = new PrintWriter(mailSocket.getOutputStream());
		Scanner socketIn = new Scanner(mailSocket.getInputStream());

		String socketAnswer = socketIn.nextLine();
		System.out.println(socketAnswer);
		if (!socketAnswer.startsWith("220")) {error(socketAnswer);}

		socketOut.println("HELO ecomp07.disca.upv.es");
		socketOut.flush();
		socketAnswer = socketIn.nextLine();
		System.out.println(socketAnswer);
		if (!socketAnswer.startsWith("250")) {error(socketAnswer);}

		socketOut.println("MAIL FROM:<redes07@redes.upv.es>");
		socketOut.flush();
		socketAnswer = socketIn.nextLine();
		System.out.println(socketAnswer);
		if (!socketAnswer.startsWith("250")) {error(socketAnswer);}

		socketOut.println("RCPT TO:<redes07@redes.upv.es>");
		socketOut.flush();
		socketAnswer = socketIn.nextLine();
		System.out.println(socketAnswer);
		if (!socketAnswer.startsWith("250")) {error(socketAnswer);}

		socketOut.println("DATA");
		socketOut.flush();
		socketAnswer = socketIn.nextLine();
		System.out.println(socketAnswer);
		if (!socketAnswer.startsWith("354")) {error(socketAnswer);}

		// **completar** 
		// Aqui van varios println con todo el correo 
		// electronico incluidas las cabeceras

		socketOut.println("To: redes07@redes.upv.es");
		socketOut.println("From: redes07@redes.upv.es");
		socketOut.println("Subject: Prueba desde java");
		socketOut.println("");
		socketOut.println("prueba desde java");
		socketOut.println(".");
		socketOut.flush();
		socketAnswer = socketIn.nextLine();
		System.out.println(socketAnswer);
		if (!socketAnswer.startsWith("250")) {error(socketAnswer);}

		socketOut.println("QUIT");
		socketOut.flush();
		socketAnswer = socketIn.nextLine();
		System.out.println(socketAnswer);
		if (!socketAnswer.startsWith("221")) {error(socketAnswer);}

		mailSocket.close();
		System.out.println("Desconectado");

	} catch (UnknownHostException e) {
		System.out.println("Host desconocido");
		System.out.println(e);
	} catch (IOException e) {
		System.out.println("No se puede conectar");
		System.out.println(e);
	}
	}
}
