package es3;

//SANTAROSSA RICCARDO 5BIA 21/10/2021

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


public class ThreadClient implements Runnable {

	private static Socket s = null;			// connection socket
	public static boolean chiudiServer=true;
	
	public ThreadClient(Socket s)
	{
		this.s = s;
	}
	
	@Override
	public void run() {
		
		while(true)
		{
			BufferedReader in = null;
			PrintWriter out = null;
			Date d = new Date();
			
			
			//Formato della data configurabile con DATEFORMAT
			DateFormat formatoData = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.ITALY);
			String data = formatoData.format(d);
			
			while(chiudiServer)
			{
			
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
			
			while(chiudiConnessione)
			{
				
			try {
				//out.println("Benvenuto in KeSuperSballo.Yeah.Ok!");
				
				// Per leggere da socket:
				String str = in.readLine();
				str.toLowerCase();
				if(str.equals("data"))
				{
					//Scelgo se stampare data semplice o pi� complessa 
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
					out.close();  // close PrintWriter
					s.close();    // close connection socket
					
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error closing socket!");
				}
			
			}
		}

	}
	
	public static void main(String[] args) {}  // fine main

}