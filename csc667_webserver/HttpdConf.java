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
    private Map<String, String> aliases; //HashMap<String,String> aliases according to ilearn
    private Map<String, String> scriptAliases; //HashMap<String,String> scriptAliases according to ilearn
    private String serverRoot;
    private int portNum;
    private BufferedReader bufferedReader;
    private String loggerFile; //Get logger file from config file.
    
    public HttpdConf(String fileName) throws IOException
    {
        super(fileName);
        aliases = new HashMap<String, String>();
        scriptAliases = new HashMap<String, String>();
        bufferedReader = getBufferedReader();
    }
    
    @Override
    public void load()
    {
        try
        {
            //FileReader fileReader = new FileReader(this.getFile());
            //BufferedReader bufferedReader = new BufferedReader(fileReader);
            aliases = new HashMap();
            String line = "";
            String splitLine[];
            
            //Read config file.
            while (hasMoreLines())
            {
                splitLine = line.split(" ", 2);
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