package es3;

//SANTAROSSA RICCARDO 5BIA 21/10/2021

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Semaphore;


public class ThreadClient implements Runnable 
{

	private Socket s = null;			// connection socket
	
	public ThreadClient(Socket s)
	{
		this.s = s;
	}
	
	@Override
	public void run() {
		
		BufferedReader in = null;
		PrintWriter out = null;
		Date d = new Date();
		
		try 
		{
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true);
			System.out.println("remote host: "+s.getInetAddress());
		} 
		catch (IOException e) 
		{			
			e.printStackTrace();
			System.out.println("Unable to get I/O stream on socket!");
		}
		
		out.println("Welcome to Riccardo Santarossa's server ");
		
		String str = null;
		
			while(MainServer.chiusura)
			{	
				//Formato della data configurabile con DATEFORMAT
				DateFormat formatoData = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.ITALY);
				String data = formatoData.format(d);
				
					try 
					{
						
						//out.println("Benvenuto in KeSuperSballo.Yeah.Ok!");
						
						// Per leggere da socket:
						str = in.readLine();
						str.toLowerCase();
						if(str.equals("data"))
						{
							//Scelgo se stampare data semplice o complessa 
							//out.println(new Date().toString());
							out.println(data);
						}
						else if(str.equals("quit"))
						{
							//out.println(str); // ECHO
							out.println("Goodbye");	
							break;
						}
						else if(str.equals("shutdown"))
						{
							MainServer.ChiudiServer();
						}
						else
						{
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
				
		} 
}
