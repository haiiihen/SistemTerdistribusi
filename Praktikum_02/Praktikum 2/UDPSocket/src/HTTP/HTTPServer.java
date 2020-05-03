/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HTTP;

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
