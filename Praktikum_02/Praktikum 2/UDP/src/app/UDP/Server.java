package app.UDP;


import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    private DatagramSocket socket = null;
    private InetAddress host = null;
    private int port;

    private int shirtPrice = 55000;
    private int pantsPrice = 50000;

    public Server(String host, int port) {
        try {
            this.socket = new DatagramSocket(port);
            this.host = InetAddress.getByName(host);
            this.port = port;

            String totalShirtPrice = receive(shirtPrice);
            System.out.println("Total Harga Baju : " + totalShirtPrice);

            String totalPantsPrice = receive(pantsPrice);
            System.out.println("Total Harga Celana : " + totalPantsPrice);

        } catch (SocketException e) {
            System.out.println("Failed to create datagram socket\n" +e.getMessage());
        } catch (UnknownHostException e) {
            System.out.println("Can't find host: " +host);
        } catch (IOException e) {
            System.out.println("Error when transmitting data\n" +e.getMessage());
        } finally {
            if(socket != null)
                socket.close();
        }

    }


    private String receive(int price) throws IOException,NumberFormatException {
        byte[] buffer = new byte[Config.MAX_BYTE];
        DatagramPacket receiver = new DatagramPacket(buffer, buffer.length);
        this.socket.receive(receiver);

        String quantity = new String(receiver.getData(),receiver.getOffset(),receiver.getLength());
        String totalPrice = Integer.toString(Integer.parseInt(quantity)*price);

        DatagramPacket replyer = new DatagramPacket(totalPrice.getBytes(), totalPrice.length(),receiver.getAddress(),receiver.getPort());
        this.socket.send(replyer);
        return totalPrice;
    }

    public static void main(String[] args) {
        new Server(Config.HOST, Config.PORT);
    }
}
