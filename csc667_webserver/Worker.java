/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josh
 */
public class Worker extends Thread
{
    private Socket client = null;
    private MimeTypes mimes = null;
    private HttpdConf config = null;
    
    public Worker(Socket socket, HttpdConf config, MimeTypes mimes){
        this.client = socket;
        this.config = config;
        this.mimes = mimes;
    }
    public void run(){
        try{
            InputStream input = client.getInputStream();
            OutputStream output = client.getOutputStream();
            long timer = System.currentTimeMillis();
            output.write(("HTTP/1.1 200 OK\n\nWorker: "+ this.config + "==" + timer).getBytes());
            
            output.close();
            input.close();
            
            System.out.println("Request was processed in: " + timer);
        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex); //auto filled from compiler
        }
    }
}
