package es3;

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

//SANTAROSSA RICCARDO 5BIA 21/10/2021

public class MainServer 
{
	private static ServerSocket ws = null; // welcoming socket
	private static boolean chiusura = true;
	
	public static void main(String[] args) 
	{
		
		try {
			ws = new ServerSocket(7979);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Errore nel welcoming socket");
		}
		
		while(chiusura)
		{
			Socket s = null;
			try 
			{
				s = ws.accept();
				ThreadClient c= new ThreadClient(s);
				Thread t = new Thread(c);
				t.start();
			} 
			
			catch(SocketException e)
			{
				chiusura=false;
			}
			
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				 
		}
		
	}
	
	public static void ChiudiServer()
	{
		try 
		{
			ws.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}