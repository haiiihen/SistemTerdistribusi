package app.server;

import java.io.IOException;
import java.net.ServerSocket;

public class HTTPServer implements Runnable {
    private ServerSocket serversocket = null;

    public static void main(String[] args) {
        new HTTPServer();
    }

    public HTTPServer() {
        Thread server_thread = new Thread(this);
        server_thread.start();
    }

    public void run() {
        try {
            System.out.print("Server is Running...................");
            serversocket = new ServerSocket(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                new HTTPClient(serversocket.accept());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}