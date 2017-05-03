/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;


/**
 * @author Josh and Jason
 */
public abstract class ConfigurationReader
{
    private File file; //Needed?
    private Dictionary dictionary; //change ot HashMap<S,S>
    private BufferedReader bufferedReader;
    
    public ConfigurationReader(String fileName) throws IOException
    {
        try
        {
            dictionary = new Hashtable();
            bufferedReader = new BufferedReader(new FileReader(fileName));
            int currentLineNum = 0;
            while (hasMoreLines())
            {
                dictionary.put(nextLine(), currentLineNum++);
            }
        }
        catch (IOException e)
        {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }

    public boolean hasMoreLines() throws IOException
    {
        boolean result = false;

        if (nextLine() != null)
        {
            result = true;
        }
        
        return result;
    }

    public String nextLine() throws IOException
    {
        String result = bufferedReader.readLine();
        return result;
    }

    abstract public void load() throws IOException;
    /* //load logic based off httpdconf constructor
    dictionary = new Hashtable<String,String>();
    bufferedReader = new BufferedReader(new FileReader(file));
    String line, splitLine[];
    while((line = bufferedReader.readLine()) != null)
    {
    splitLine = line.split("", 2);
    dictionary.put(splitLine[0], splitLine[1]);
    }*/
        
    public File getFile(){
        return this.file;
    }
    
    public BufferedReader getBufferedReader(){
        return this.bufferedReader;
    }
}
