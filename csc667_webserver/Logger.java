/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author Jason
 */
public class Logger {
    private Path filePath;
    private File file;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    boolean DEBUG = true;
    
    public Logger(String filePath) throws IOException
    { 
        if (DEBUG) System.out.println("Trying to create file: " + filePath);
        
        try {
            file = new File("." + filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }
    
    public void write(Request request, Response response) throws IOException
    {
        fileWriter = new FileWriter(file.getAbsoluteFile(), true);
        bufferedWriter = new BufferedWriter(fileWriter);
        
        String line = "Request: " + request.getUri() + " " + request.getVerb() + " " + request.getHttpVersion() + "\n";
        fileWriter.write(line);
        line = Integer.toString(response.getCode());
        fileWriter.write(line);
        
        bufferedWriter.close();
        fileWriter.close();
    }
}
