package app.UDP;

import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
	private DatagramSocket socket = null;
	private Scanner scanner = null;
	private InetAddress host = null;
	private int port;
	
	public Client(String host, int port) {
		this.scanner = new Scanner(System.in);
		try {
			this.socket = new DatagramSocket();
			this.host = InetAddress.getByName(host);
			this.port = port;
			
			String quantity = "";
			System.out.print("Masukan Jumlah Baju  : ");
			quantity = scanner.nextLine();
			
			String shirtPrice = request(quantity);
			System.out.println("Total Harga Baju   : Rp " + shirtPrice);
			
			System.out.print("Masukan Jumlah Celana: ");
			quantity = scanner.nextLine();
			
			String pantsPrice = request(quantity);
			System.out.println("Total Harga Baju   : Rp " + pantsPrice);

			int totalPrice = Integer.parseInt(shirtPrice)+Integer.parseInt(pantsPrice);

			System.out.println("Total Harga Celana : Rp " +(totalPrice));
		} catch (SocketException e) {
			System.out.println("Failed to create datagram socket\n" +e.getMessage());
		} catch (UnknownHostException e) {
			System.out.println("Can't find host: " +host);
		} catch (IOException e) {
			System.out.println("Error when transmitting data\n" +e.getMessage());
		} catch (NumberFormatException e){
			System.out.println("Error Casting\n" + e.getMessage());
		}finally {
			if(scanner != null)
				scanner.close();
			if(socket != null)
				socket.close();
		}
		
	}
	
	
	private String request(String message) throws IOException {
		byte[] data = message.getBytes(); 
		DatagramPacket request = new DatagramPacket(data, message.length(), this.host, this.port); 
		this.socket.send(request);
		byte[] buffer = new byte[Config.MAX_BYTE];
		DatagramPacket reply = new DatagramPacket(buffer, buffer.length); 
		this.socket.receive(reply);
		return new String(reply.getData(),reply.getOffset(),reply.getLength());
	}
	
	public static void main(String[] args) {
		new Client(Config.HOST, Config.PORT);
	}
}
