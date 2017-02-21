/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Dictionary;
/**
 *
 * @author Josh and Jason
 */
public class MimeTypes extends ConfigurationReader
{
    private Dictionary types; //jrob on ilearn said to do HashMap<String,String> types;
    private BufferedReader bufferedReader;
    
    public MimeTypes(String fileNames) throws IOException
    {
    	super(fileNames);
        this.types = new Hashtable(); //HashMap<>();
        this.bufferedReader = new BufferedReader(new FileReader(this.getFile()));
        this.load();
    }

    public void load() throws IOException
    {        
        types = new Hashtable<String, String>();
        
        String line;
        String splitLine[];
        
        while((line = bufferedReader.readLine()) != null){
            splitLine = line.split("",2);
            types.put(splitLine[0], splitLine[1]);
        }
    }

    public String lookup(String extension)
    {
        
        return "";
    }
}
