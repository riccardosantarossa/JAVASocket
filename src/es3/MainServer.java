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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//SANTAROSSA RICCARDO 5BIA 21/10/2021

public class MainServer 
{
	private static ServerSocket ws = null; // welcoming socket
	public static boolean chiusura = true;
	public static ExecutorService pool = Executors.newFixedThreadPool(3);
	
	public static void main(String[] args) 
	{
		try 
		{
			ws = new ServerSocket(7979);
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
			System.out.println("Errore nel welcoming socket");
		}
		
		while(chiusura)
		{
			Socket s = null;
			try 
			{
				s = ws.accept();
				/*ThreadClient c= new ThreadClient(s);
				Thread t = new Thread(c);
				t.start();*/
				pool.execute(new ThreadClient(s));
			} 
			
			catch(SocketException e)
			{
				chiusura=false;
			}
			
			catch (IOException e) 
			{
				e.printStackTrace();
			}		 
		}
	}
	
	public static void ChiudiServer()
	{
		try 
		{
			ws.close();
			pool.shutdownNow();
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