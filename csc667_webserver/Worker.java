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
    private final Socket client;
    private MimeTypes mimes;
    private final HttpdConf config;
    private final Logger logger;
    private Request request;
    private Resource resource;
    private Response response;
    
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
                resource = new Resource(request.getUri(), config);
                //Create response.
                //Log response.
                //Send response.
            }
            catch(BadRequest e)
            {
                System.err.println("Caught BadRequest exception: " + e.getMessage());
                logger.write(request, response);
            }
        }
        catch(IOException e)
        {
            System.err.println("Caught exception: " + e.getMessage());
        }
    }
}
