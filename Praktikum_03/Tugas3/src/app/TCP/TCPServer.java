package app.TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    static final int SERVER_PORT = 1212;

        try{
            int serverPort = 1212;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("Server sedang running...........");
            while(true){
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }

        }catch(IOException e){
            System.out.println("Listen :" + e.getMessage());
        }
    }

}