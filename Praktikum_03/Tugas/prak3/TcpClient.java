package psister;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TcpClient {
	
	
	
	public static void main(String[] args) {
        Socket s = null;
        Scanner sc = null;

        try {
            int serverPort = 7896;
            String host = "127.0.0.1";
            s = new Socket(host, serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            sc = new Scanner(System.in);
            TcpClient.TCP();
            System.out.print("Masukkan jumlah angka yang ingin dijumlahkan: ");

            Integer n = sc.nextInt();

            String[] number = new String[n];

            sc.nextLine();

            for (int i = 0; i < n; i++) {
                System.out.print("Masukkan angka ke-" + (i + 1) + ": ");

                String next = sc.nextLine();

                try {
                    Integer.parseInt(next);

                    number[i] = next;
                } catch (Exception e) {
                    number[i] = "0";
                }
            }

            String input = String.join(",", number);

            out.writeUTF(input);
            String data = in.readUTF();
            System.out.println("Received: " + data);
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (sc != null)
                sc.close();

            if (s != null)
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }

        }

    }
    public static void TCP() {
        System.out.println("============= Mulai Program (TCP) =============");
    }

}