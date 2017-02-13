/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.net.Socket;

/**
 *
 * @author Josh
 */
public class Worker extends Thread
{
    private Socket client;
    private MimeTypes mimes;
    private HttpdConf config;
    
    public Worker(Socket socket, HttpdConf config, MimeTypes mimes){
        
    }
    public void run(){
        
    }
}
