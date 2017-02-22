/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.io.*;

/**
 *
 * @author Josh and Jason
 */
public class Logger {
    private File file;
    private FileWriter fileWriter;
    
    public Logger(String fileName) throws IOException
    {
        file = new File(fileName);
        file.createNewFile();
        fileWriter = new FileWriter(file, true);
        
    }
    
    public void write(Request request, Response response) throws IOException
    {
        String line = "Request: " + request.getUri() + " " + request.getVerb() + " " + request.getHttpVersion() + "\n";
        fileWriter.write(line);
        line = Integer.toString(response.code);
        fileWriter.write(line);
    }
}
