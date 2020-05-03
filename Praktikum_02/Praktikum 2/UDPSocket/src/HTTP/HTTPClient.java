/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HTTP;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.Socket;
public class HTTPClient extends Thread {
    Socket socket;
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    DataOutputStream out = null;

    public HTTPClient(Socket socket){
        this.socket = socket;
        setPriority(NORM_PRIORITY-1);
        start();
    }

    public void run(){
        try {
            BufferedReader requestReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ByteArrayOutputStream requestBA = new ByteArrayOutputStream();
            int rch = socket.getInputStream().available();
            System.out.println(rch);
            while(rch-->0){
                requestBA.write(requestReader.read());
            }
            HTTPRequest request = new HTTPRequest(requestBA.toByteArray());
            System.out.println("[REQUEST URL ] " + request.getRequestURI());
            File resource = new File("src/com/socket/http/www"+request.getRequestURI());
            System.out.println(resource.getPath());
            FileInputStream fis = new FileInputStream(resource.getPath());
            byte[] data = new byte[(int) resource.length()];
            fis.read(data,0,data.length);
            System.out.println(new String(data).toString());
            HTTPResponse response = new HTTPResponse(200,socket);
            response.setContentType("text/html");
            response.write(data);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}