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
 * @author Josh
 */
public class HttpdConf extends ConfigurationReader
{
    private Dictionary aliases; //assumption:need library for this to work
    private Dictionary scriptAliases; //assumption:need library for this to work
    
    
    public HttpdConf(String fileName) throws IOException
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            aliases = new Hashtable<String, String>();
            String line;
            String splitLine[];
            
            //Read config file.
            while ((line = bufferedReader.readLine()) != null)
            {
                splitLine = line.split("", 2);
                aliases.put(splitLine[0], splitLine[1]);
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
    
    public void load()
    {
        
    }
}
