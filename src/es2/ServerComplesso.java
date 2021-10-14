package es2;

//SANTAROSSA RICCARDO 5BIA 08/10/2021


import java.net.*;
import java.text.DateFormat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Locale;

public class ServerComplesso {

	private static ServerSocket ws = null;  // welcoming socket
	private static Socket s = null;			// connection socket
	
	public static void main(String[] args) {
		// Demo Server TCP
		
		BufferedReader in = null;
		PrintWriter out = null;
		Date d = new Date();
		boolean chiudiServer=true;
		
		//Formato della data configurabile con DATEFORMAT
		DateFormat formatoData = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.ITALY);
		String data = formatoData.format(d);
		
		while(chiudiServer)
		{
		try 
		{
			// Welcoming Socket (per 3-way handshake)
			ws = new ServerSocket(7979);
		} 
		catch (IOException e) {			
			e.printStackTrace();
			System.out.println("Welcoming socket error!");
		}
		
		try 
		{
			s = ws.accept();  // create connection socket
		} 
		catch (IOException e) {			
			e.printStackTrace();
			System.out.println("Connection Accept error!");
		}
		
		try 
		{
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true);
			out.println("Welcome to Riccardo Santarossa's server ");
			System.out.println("remote host: "+s.getInetAddress());
		} 
		catch (IOException e) 
		{			
			e.printStackTrace();
			System.out.println("Unable to get I/O stream on socket!");
		}
		
		boolean chiudiConnessione=true;
		
		while(chiudiConnessione){
			
		try {
			//out.println("Benvenuto in KeSuperSballo.Yeah.Ok!");
			
			// Per leggere da socket:
			String str = in.readLine();
			str.toLowerCase();
			if(str.equals("data"))
			{
				//Scelgo se stampare data semplice o più complessa 
				//out.println(new Date().toString());
				out.println(data);
			}
			else if(str.equals("quit"))
			{
				//out.println(str); // ECHO
				out.println("Goodbye");	
				chiudiConnessione=false;
			}
			else if(str.equals("shutdown"))
			{
				chiudiConnessione=false;
				chiudiServer=false;
			}
			else{
				StringBuilder build = new StringBuilder(str);
				build.reverse();
				out.println(build);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Communication Error!");
		} 
		}
		
		if(!chiudiServer && !chiudiConnessione)
			try 
			{
				in.close();
				out.close();  // close PrintWriter
				s.close();    // close connection socket
				ws.close();   // close welcoming socket
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error closing socket!");
			}
		
		if(!chiudiConnessione)
			try 
			{
				//in.close();
				out.close();  // close PrintWriter
				s.close();    // close connection socket
				//ws.close();   // close welcoming socket
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error closing socket!");
			}
		
		
	
		}

	}  // fine main

}

