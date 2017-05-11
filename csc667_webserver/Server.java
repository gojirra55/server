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
 * @author Jason
 */
public class Server
{
    private HttpdConf configuration;
    private MimeTypes mimeTypes;
    private ServerSocket socket;
    private Socket clientSocket;
    private Dictionary accessFiles;
    private String configurationDirectory = "./build/classes/conf/";
    
    public void start() throws IOException
    {
        try
        {
            //Load config file.
            configuration = new HttpdConf(configurationDirectory + "httpd.conf");
            configuration.load();
            int port = configuration.getPort(); //Need to add lookup function to HttpdConf.
            //Load mime types.
            mimeTypes = new MimeTypes(configurationDirectory + "mime.types");
            //Set up logger.
            Logger logger = new Logger(configuration.getLoggerFile());
            //Set up socket.
            socket = new ServerSocket(port);
            
            clientSocket = socket.accept();
            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            
            InputStream inputStream = clientSocket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            
            String inputLine, outputLine;
            
            while((inputLine = bufferedReader.readLine()) != null) {
                System.out.println("Input line: " + inputLine);
                if (inputLine.isEmpty()) {
                    System.out.println("Breaking.");
                    break;
                }
            }
            
            Thread worker = new Thread(new Worker(clientSocket, configuration, mimeTypes, outputStream, inputStream, logger));
            worker.start();
            
            while((inputLine = bufferedReader.readLine()) != null) {
                System.out.println("Input line: " + inputLine);
                if (inputLine.isEmpty()) {
                    System.out.println("Breaking.");
                    break;
                }
            }
            
            
            /*
            while (true)
            {
                clientSocket = socket.accept();
                OutputStream outputStream = connection.getOutputStream();
                InputStream inputStream = connection.getInputStream();
                Thread worker = new Thread(new Worker(clientSocket, configuration, mimeTypes, outputStream, inputStream, logger));
                worker.start();
            }*/
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
