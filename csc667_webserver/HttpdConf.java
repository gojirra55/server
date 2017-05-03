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
 * @author Josh and Jason
 */
public class HttpdConf extends ConfigurationReader
{
    private HashMap<String, String> aliases;
    private HashMap<String, String> scriptAliases;
    private String serverRoot;
    private int portNum;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private String currentLine;
    private String loggerFile; //Needed for Logger, get from config file.
    
    public HttpdConf(String fileName) throws IOException
    {
        aliases = new HashMap<String, String>();
        scriptAliases = new HashMap<String, String>();
        fileReader = new FileReader(fileName);
        bufferedReader = new BufferedReader(fileReader);
    }
    
    @Override
    public void load()
    {
        try
        {
            aliases = new HashMap<String, String>();
            String splitLine[];
            
            //Read config file.
            while (hasMoreLines())
            {
                splitLine = currentLine.split(" ", 2);
                if (splitLine[0].equals("Listen")){
                    portNum = Integer.parseInt(splitLine[1]);
                }
                else if (splitLine[0].equals("ServerRoot"))
                {
                    serverRoot = splitLine[0];
                }
                else{
                    aliases.put(splitLine[0], splitLine[1]);
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
        Boolean result = true;
        
        if (nextLine() != null)
        {
            result = false;
        }
        
        return result;
    }
    
    @Override
    public String nextLine() throws IOException
    {
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
        return loggerFile;
    }
    
    public String checkAliases(String uri)
    {
        return aliases.get(uri);
    }
    
    public String checkScriptAliases(String uri)
    {
        return scriptAliases.get(uri);
    }
}