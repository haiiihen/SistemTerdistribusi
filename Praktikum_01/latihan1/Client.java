package latihan1;

import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.InetAddress; 
import java.net.Socket; 
import java.util.Scanner; 

public class Client{ 
	public static void main(String[] args) throws IOException { 
		InetAddress ip = InetAddress.getLocalHost(); 
		int port = 4443; 
		Scanner sc = new Scanner(System.in); 

		Socket s = new Socket(ip, port); 

		DataInputStream dis = new DataInputStream(s.getInputStream()); 
		DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 

		while (true) { 
			System.out.print("Enter the equation in the form: "); 
			System.out.println("numeric operator numeric'"); 

			String inp = sc.nextLine();  

			dos.writeUTF(inp); 

			String ans = dis.readUTF(); 
			System.out.println("Answer=" + ans); 
			break;
		}
	} 
} 
