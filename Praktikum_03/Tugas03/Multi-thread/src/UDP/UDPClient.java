package UDP;

    import java.io.DataInputStream;
    import java.io.DataOutputStream;
    import java.io.EOFException;
    import java.io.IOException;
    import java.net.DatagramPacket;
    import java.net.DatagramSocket;
    import java.net.InetAddress;
    import java.net.Socket;
    import java.net.UnknownHostException;
    import java.util.Scanner;
    
    public class UDPClient {
        
        public static void main(String[] args) {
            DatagramSocket s = null;
            Scanner sc = null;
    
            try {
                int serverPort = 7896;
                String host = "127.0.0.1";
    
                s = new DatagramSocket();
    
                InetAddress aHost = InetAddress.getByName(host);
    
                sc = new Scanner(System.in);
                UDPClient.UDP();
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
                byte[] m = input.getBytes();
    
                DatagramPacket request = new DatagramPacket(m, input.length(), aHost, serverPort);
    
                s.send(request);
    
                byte[] buffer = new byte[m.length];
    
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
    
                s.receive(reply);
    
                System.out.println("Received: " + new String(reply.getData()));
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
                    s.close();
    
            }
    
        }
    
        public static void UDP() {
            System.out.println("============= Mulai Program (UDP) =============");
        }
    }