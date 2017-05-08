/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.io.*;
import java.util.*;

/**
 *
 * @author Jason
 */
public class HttpdConf extends ConfigurationReader
{
    private boolean DEBUG = false;
    
    private HashMap<String, String> aliases;
    private HashMap<String, String> scriptAliases;
    private String documentRoot;
    private String serverRoot;
    private int portNum;
    private String logFile;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private String currentLine;
    
    public HttpdConf(String fileName) throws IOException
    {
        if(DEBUG) System.out.println("File name: " + fileName);
        aliases = new HashMap<String, String>();
        scriptAliases = new HashMap<String, String>();
        fileReader = new FileReader(fileName);
        bufferedReader = new BufferedReader(fileReader);
    }
    
    @Override
    public void load() {
        try {
            //Read config file.
            String splitLine[];
            while (hasMoreLines()) {
                splitLine = currentLine.split(" ", 2);
                if(DEBUG) System.out.println("Reading config file: " + splitLine[0] + splitLine[1]);
                switch(splitLine[0])
                {
                    case "ServerRoot":
                                serverRoot = stripQuotes(splitLine[1]); 
                                break;
                    case "DocumentRoot":
                                documentRoot = stripQuotes(splitLine[1]); 
                                break;
                    case "Listen":
                                portNum = Integer.parseInt(splitLine[1]);
                                break;
                    case "LogFile":
                                logFile = stripQuotes(splitLine[1]);
                                break;
                    default:    //Aliases are in the format of Alias <name> <path>.
                                //So the line needs to be split again.
                                String secondSplit[];
                                secondSplit = splitLine[1].split(" ", 2);
                                if (secondSplit.length == 2) {
                                    aliases.put(secondSplit[0], stripQuotes(secondSplit[1]));
                                }
                                else {
                                    System.out.println(currentLine + " is not a valid config entry.");
                                }
                                break;
                                
                }
            }
            
            bufferedReader.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Caught FileNotFoundException: " + e.getMessage());
        }
        catch(IOException e)
        {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }
    
    @Override
    public Boolean hasMoreLines() throws IOException
    {
        Boolean result = false;
        
        if (nextLine() != null) {
            if(DEBUG) System.out.println("Current line: " + currentLine);
            result = true;
        }
        
        if(DEBUG) System.out.println("Has more lines?: " + result);
        return result;
    }
    
    @Override
    public String nextLine() throws IOException {
        return (currentLine = bufferedReader.readLine());
    }
    
    public String getServerRoot()
    {
        return serverRoot;
    }
    
    public int getPort()
    {
        return portNum;
    }
    
    public String getLoggerFile()
    {
        if(DEBUG) { System.out.println("Logger file is: " + logFile); }
        return logFile;
    }
    
    public String checkAliases(String uri)
    {
        return aliases.get(uri);
    }
    
    public String checkScriptAliases(String uri)
    {
        return scriptAliases.get(uri);
    }
    
    public String stripQuotes(String string) {
        return string.replace("\"", "");
    }
}