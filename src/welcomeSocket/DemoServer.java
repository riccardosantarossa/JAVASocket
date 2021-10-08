package welcomeSocket;

import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class DemoServer {

	private static ServerSocket ws = null;  // welcoming socket
	private static Socket s = null;			// connection socket
	
	public static void main(String[] args) {
		// Demo Server TCP
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			// Welcoming Socket (per 3-way handshake)
			ws = new ServerSocket(7979);
		} catch (IOException e) {			
			e.printStackTrace();
			System.out.println("Welcoming socket error!");
		}
		
		try {
			s = ws.accept();  // create connection socket
		} catch (IOException e) {			
			e.printStackTrace();
			System.out.println("Connection Accept error!");
		}
		
		try {
			//in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true);
			out.println("Che supersballo!");
			System.out.println("remote host: "+s.getInetAddress());
		} catch (IOException e) {			
			e.printStackTrace();
			System.out.println("Unable to get I/O stream on socket!");
		}
		System.out.println("Quitting server!");
		
		/*
		try {
			out.println("Benvenuto in KeSuperSballo.Yeah.Ok!");
			
			// Per leggere da socket:
			String str = in.readLine();
			out.println(str); // ECHO
			System.out.println(str);  // DEBUG
			
			// Per scrivere su socket:
			out.println("Arrivederci, e' stato un piacere!");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Communication Error!");
		} */
		
		try {
			//in.close();
			out.close();  // close PrintWriter
			s.close();    // close connection socket
			ws.close();   // close welcoming socket
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error closing socket!");
		}
		

	}  // fine main

}
