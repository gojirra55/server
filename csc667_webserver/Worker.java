/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author Josh and Jason
 */
public class Worker extends Thread
{
    private Socket client = null;
    private MimeTypes mimes = null;
    private HttpdConf config = null;
    private Logger logger;
    
    public Worker(Socket socket, HttpdConf config, MimeTypes mimes, Logger logger){
        this.client = socket;
        this.config = config;
        this.mimes = mimes;
        this.logger = logger;
    }
    public void run()
    {
        
        
        try
        {
            InputStream stream = client.getInputStream();
            Request request = new Request(stream);
            request.parse();
            
            
            //Old code
            /*InputStream input = client.getInputStream();
            OutputStream output = client.getOutputStream();
            long timer = System.currentTimeMillis();
            output.write(("HTTP/1.1 200 OK\n\nWorker: "+ this.config + "==" + timer).getBytes());
            
            output.close();
            input.close();
            
            System.out.println("Request was processed in: " + timer);*/
        }
        catch (IOException e)
        {
            //Return 400 here?
        }
    }
}
