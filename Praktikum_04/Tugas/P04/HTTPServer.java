import com.socket.http.HTTPClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class HTTPServer implements Runnable {
    private ServerSocket serversocket = null;
    public static void main(String[] args){
        new HTTPServer();
    }
    public HTTPServer(){
        Thread server_thread = new Thread(this);
        server_thread.start();
    }
    public void run(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Server is Running...................");
            serversocket = new ServerSocket(8080);
            String n = "";
            while (true){
                try {
                    new HTTPClient(serversocket.accept());
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }

    }
}