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
    private Request request;
    
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
            try
            {
                request = new Request(stream);
                request.parse();
            }
            catch(BadRequest e)
            {
                System.err.println("Caught BadRequest exception: " + e.getMessage());
            }
        }
        catch(IOException e)
        {
            System.err.println("Caught exception: " + e.getMessage());
        }
    }
}
