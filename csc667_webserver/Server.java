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
    public static final int DEFAULT_PORT = 8080;
    
    public void start() throws IOException
    {
        try
        {
            //Load config file.
            configuration = new HttpdConf("./conf/httpd.conf");
            configuration.load();
            int port = configuration.getPort(); //Need to add lookup function to HttpdConf.
            //Load mime types.
            mimeTypes = new MimeTypes("./conf/mime.conf");
            //Set up logger.
            Logger logger = new Logger(configuration.getLoggerFile());
            //Set up socket.
            socket = new ServerSocket(port);
            Socket client = null;
            
            while (true)
            {
                /* ---Pulled from Java docs---
                * If the server successfully binds to its port, then the ServerSocket object is successfully created and 
                * the server continues to the next step—accepting a connection from a client (the next statement in the try-with-resources statement):
                *    clientSocket = serverSocket.accept();
                * The accept method waits until a client starts up and requests a connection on the host and port of this server. 
                */
               
                client = socket.accept();
                Thread worker = new Thread(new Worker(client, configuration, mimeTypes, logger));
                worker.start();

                PrintWriter out = new PrintWriter(client.getOutputStream(),true); //not sure if this should be handled in Response class

                client.close();
                
            }
        }
        catch (IOException e)
        {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        socket.close();        
    }
    
    public static void main(String args[])
    {
        try
        {
            Server server = new Server();
            server.start();
        }
        catch(IOException e)
        {
            System.err.println("IOException caught: " + e.getMessage());
            //Throw 500 here!
        }
    }
}
