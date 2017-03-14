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
    private final HttpdConf config;
    private MimeTypes mimes;
    private final OutputStream out;
    private final Logger logger;
    private Request request;
    private Resource resource;
    private ResponseFactory responseFactory = new ResponseFactory();
    private Response response;
    
    public Worker(Socket socket, HttpdConf config, MimeTypes mimes, OutputStream out, Logger logger){
        client = socket;
        this.config = config;
        this.mimes= mimes;
        this.out = out;
        this.logger = logger;
    }
    public void run()
    {
        try
        {
            InputStream stream = client.getInputStream();
            try
            {
                //Create and parse request.
                request = new Request(stream);
                request.parse();
                //Create resource.
                resource = new Resource(request.getUri(), config);
                //Create and log response.
                response = responseFactory.getResponse(request, resource);
                logger.write(request, response);
                response.send(out);
                
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
