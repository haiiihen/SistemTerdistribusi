package app.main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPClient {
    public static void main(String args[]){
        DatagramSocket aSocket = null;
        try{
            aSocket = new DatagramSocket();
            Scanner sc = new Scanner(System.in);
            System.out.println("Inputkan message yang akan di submit : ");
            String message = sc.nextLine();
            byte [] m = message.getBytes();
            InetAddress aHost = InetAddress.getByName("127.0.0.1");

            int serverPort = 6789;
            DatagramPacket request = new DatagramPacket(m, message.length(), aHost, serverPort);
            aSocket.send(request);
            byte[] buffer = new byte[m.length]; 
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println("Reply :" + new String(reply.getData()));
        }catch(SocketException e){
            System.out.println("Socket :" + e.getMessage());
        }catch(IOException e){
            System.out.println("IO : " + e.getMessage());
        }finally{
            if(aSocket != null)
            aSocket.close();
        }
    }

}