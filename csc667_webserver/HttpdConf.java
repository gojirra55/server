/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Dictionary;
/**
 *
 * @author Josh and Jason
 */
public class HttpdConf extends ConfigurationReader
{
    private Dictionary aliases; //HashMap<String,String> aliases according to ilearn
    private Dictionary scriptAliases; //HashMap<String,String> scriptAliases according to ilearn
    private int portNum;
    private BufferedReader bufferedReader;
    private String loggerFile; //Get logger file from config file.
    
    public HttpdConf(String fileName) throws IOException
    {
        super(fileName);
        this.aliases = new Hashtable();
        this.scriptAliases = new Hashtable();
        this.load();
        this.bufferedReader = new BufferedReader(getBufferedReader());
        //moved try-catch to load method
    }
    
    public void load()
    {
        try
        {
            //FileReader fileReader = new FileReader(this.getFile());
            //BufferedReader bufferedReader = new BufferedReader(fileReader);
            aliases = new Hashtable<String, String>();
            String line = "";
            String splitLine[];
            
            //Read config file.
            while (hasMoreLines())
            {
                splitLine = line.split("", 2);
                if(splitLine[0] == "Listen"){
                    portNum = Integer.parseInt(splitLine[1]);
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
    
    public int getPort()
    {
        return portNum;
    }
    
    public String getLoggerFile()
    {
        return loggerFile;
    }
}