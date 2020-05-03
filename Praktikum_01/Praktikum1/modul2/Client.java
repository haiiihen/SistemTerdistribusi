package modul2;

import java.net.*; 
import java.util.Scanner; 
import java.io.*; 

public class Client{ 
	static DatagramSocket aSocket = null;
	public static void main(String args[]){ 
		try { 
			aSocket = new DatagramSocket(); 
			Scanner sc = new Scanner(System.in); 
			System.out.print("Input bilangan pertama : "); 
			int first = sc.nextInt();
			System.out.print("Input bilangan kedua : "); 
			int second = sc.nextInt(); 
			String message = (first+" "+second); 
			byte [] m = message.getBytes(); 
			InetAddress aHost = InetAddress.getByName("127.0.0.1"); 
			int serverPort = 1234; 
			DatagramPacket request = new DatagramPacket(m, message.length(), aHost, serverPort); 
			aSocket.send(request); 
			byte[] buffer = new byte[m.length]; 
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length); 
			aSocket.receive(reply); 
			System.out.println("Reply: " + new String(reply.getData())); 
		} catch (SocketException e) { 
			System.out.println("Socket: " + e.getMessage()); 
		} catch (IOException e) { 
			System.out.println("IO: " + e.getMessage()); 
		} finally { 
			if(aSocket != null) aSocket.close(); 
		} 
	}
}

