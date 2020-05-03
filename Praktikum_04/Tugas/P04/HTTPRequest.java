package com.socket.http;
import java.io.ByteArrayOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
public class HTTPRequest {
    private Hashtable headers = new Hashtable();
    private Hashtable parameters = new Hashtable();
    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    private byte[] content = null;
    private String requestURI="";
    private String method ="";
    public HTTPRequest(byte[] headers){
        this.parseHeaders(headers);
    }
    private void parseHeaders(byte[] rawHeaders){
        int eoh = findEOH(rawHeaders);
        String heads = new String(rawHeaders,0,eoh);
        StringTokenizer st = new StringTokenizer(heads,"\r\n");
        StringTokenizer st_firstLine = new StringTokenizer(st.nextToken()," ");
        this.method = st_firstLine.nextToken().trim();
        this.requestURI = st_firstLine.nextToken().trim();
        while (st.hasMoreTokens()){
            String requestLine = st.nextToken();
            int separator = requestLine.indexOf(": ");
            String header = requestLine.substring(0,separator);
            String value = requestLine.substring(separator+1,requestLine.length());
            headers.put(header.trim(), value.trim());
        }
        if ( this.getMethod().equals("POST")){
            this.content = new byte[Integer.parseInt(this.getHeader("Content-Length"))];
            System.arraycopy(rawHeaders,eoh+4, this.content, 0, this.content.length);
        } else{
            System.out.println(new String(rawHeaders));
        }
        System.out.println(new String(rawHeaders));
    }
    public static int findEOH(byte[] headers){
        String heads = new String(headers);
        return heads.indexOf("\r\n\r\n");
    }
    public String getRequestURI(){
        return this.requestURI;
    }
    public String getMethod(){
        return this.method;
    }
    public String getParamater(String name){
        return (String) this.parameters.get(name);
    }
    public String getHeader(String name){
        return (String) this.headers.get(name);
    }
    public Enumeration getHeaders(){
        return headers.elements();
    }
    public byte[] getContent(){
        return this.content;
    }
}