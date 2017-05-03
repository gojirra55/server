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
import java.util.HashMap;
import java.util.Hashtable;

/**
 * @author Jason
 */
public abstract class ConfigurationReader
{
    private File file; //Needed?
    private HashMap<String, String> dictionary; //change ot HashMap<S,S>
    private BufferedReader bufferedReader;

    abstract public Boolean hasMoreLines() throws IOException;
    
    abstract public String nextLine() throws IOException;
    
    abstract public void load();
}