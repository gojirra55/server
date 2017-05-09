/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Jason
 */
public class Response {
    
    Boolean DEBUG = true;
    private int code;
    public String reasonPhrase;
    public Resource resource;
    public String response;
    
    public Response(Resource resource)
    {
        this.resource = resource;
    }
    
    public void send(OutputStream outputStream)
    {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        String dateString = dateFormat.format(date);
        if (DEBUG) System.out.println("The date is: " + dateString);
        
        String header = "HTTP/1.0 200 OK\r\n";
        String dateLine = "Date: " + dateString + "\r\n";
        //String server = "Server: Jason's Server\r\n";
        String contentType = "Content-Type: text/html\r\n";
        String body = "<html><body><h1>TEST</h1></body></html>\r\n";
        //String contentLength = "Content-Length:" + body.getBytes().length + "\r\n";
        
        response = header + dateLine + /*server + /*contentLength +*/ contentType + "\r\n" + body;
        if (DEBUG) System.out.print("Ressponse:\n" + response);
        
        try {
            outputStream.write("Hello!".getBytes());
            
            /*
            dataOutputStream.writeBytes(response);
            dataOutputStream.close();
            outputStream.close();*/
        }
        catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }
    
    public void setCode(int code)
    {
        this.code = code;
    }
    
    public int getCode()
    {
        return code;
    }
}
