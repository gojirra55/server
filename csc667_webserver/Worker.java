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
 * @author Jason
 */
public class Worker extends Thread
{
    private final Socket connection;
    private final HttpdConf config;
    private MimeTypes mimes;
    private final OutputStream outputStream;
    private final InputStream inputStream;
    private final Logger logger;
    private Request request;
    private Resource resource;
    private ResponseFactory responseFactory = new ResponseFactory();
    private Response response;
    
    public Worker(Socket connection, HttpdConf config, MimeTypes mimes, OutputStream outputStream, InputStream inputStream, Logger logger){
        this.connection = connection;
        this.config = config;
        this.mimes= mimes;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        this.logger = logger;
    }
    public void run()
    {
        try
        {
            try
            {
                //Create and parse request.
                request = new Request(inputStream);
                request.parse();
                //Create resource.
                resource = new Resource(request.getUri(), config);
                //Create and log response.
                response = responseFactory.getResponse(request, resource);
                System.out.println("Request: " + request + ". Response: " + response.getCode());
                logger.write(request, response);
                response.send(outputStream);
                
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
