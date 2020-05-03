package modul2;

import java.net.*; 
import java.io.*; 
 
public class Server{ 
	public static void main(String args[]){ 
	
		DatagramSocket aSocket = null; 
		try{ 
			aSocket = new DatagramSocket(1234); 
			byte[] buffer = new byte[256]; System.out.println("Server Running............... "); 
			while(true){ 
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(request);
				String hsl = sum(new String(request.getData()));
				DatagramPacket reply = new DatagramPacket(hsl.getBytes(),hsl.length(), request.getAddress(), request.getPort());
				aSocket.send(reply);
			}
		} catch (SocketException e){
			System.out.println("Socket:"+e.getMessage());
		} catch (IOException e){
			System.out.println("IO:"+e.getMessage());
		} finally {
			if(aSocket != null)
				aSocket.close();
		}
	}
	private static String sum(String data) {
		String[] ab = data.split(" ");
		int a = Integer.parseInt(ab[0].trim());
		int b = Integer.parseInt(ab[1].trim());
		return String.valueOf(a+b);
	}
}
