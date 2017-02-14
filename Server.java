/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.io.*;
import java.net.*;
import java.util.Dictionary;

/**
 *
 * @author Josh and Jason
 */
public class Server
{
    private HttpdConf configuration;
    private MimeTypes mimeTypes;
    private ServerSocket socket;
    private Dictionary accessFiles;
    private Request request;
    private Resource resource;
    private Response response;
    public static final int DEFAULT_PORT = 80;
    
    public void start() throws IOException
    {
        try
        {
            socket = new ServerSocket();
            Socket client = null;
            
            
            while (true)
            {
                client = socket.accept(); //What does this line do exacty?
                request = requestLine(client);
                response = ResponseFactory.getResponse(request, resource);
                
            }
        }
        catch (IOException e)
        {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        
        
    }
    
    private Request requestLine(Socket client) throws IOException
    {
        InputStream stream = client.getInputStream();
        Request request = new Request(stream);
        
        //More code parsing request?
        return request;
    }
}
