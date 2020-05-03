package app.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class HTTPResponse {
    private byte[] content = null;
    private String contentType = "text/html";
    private DataOutputStream client = null;
    private Socket clientSock = null;
    private int httpCode = 200;
    private int contentLength = 0;
    private String CRLF = "\r\n";

    public HTTPResponse(int responseCode, Socket sock) {
        this.clientSock = sock;
        this.httpCode = responseCode;
    }

    public void setContentType(String type) {
        this.contentType = type;
    }

    public String getContentType() {
        return this.contentType;
    }

private String buildResponsHeader(byte[] content){
int content_length= content.length;
String content_type = this.getContentType();
Date today = new Date();
String expire_date = today.toString();
String header = "HTTP "+httpCode+" OK\r\nServer:Personal Web server\r\n";
header = header +"Content-Type: "+content_type+"\r\nContent-Length: "+content_length+"\r\n";

header = header + "Connection: close\r\nExpires:"+expire_date+"\r\n\r\n";
return header;
}

    public void write(byte[] writeme) throws IOException {
        client = new DataOutputStream(clientSock.getOutputStream());
        client.write(buildResponsHeader(writeme).getBytes());
        client.write(writeme);
        client.flush();
        client.close();
        clientSock.close();
    }
}
