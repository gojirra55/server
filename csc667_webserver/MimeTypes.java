/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Dictionary;
/**
 *
 * @author Josh and Jason
 */
public class MimeTypes extends ConfigurationReader
{
    private Dictionary types;
    
    public MimeTypes(String fileNames) throws IOException
    {
    	super(fileNames);
        this.types = new Hashtable();
        this.load();
    }

    public void load()
    {
        
    }

    public String lookup(String extension)
    {
        
        return "";
    }
}
