/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;


/**
 *
 * @author Josh and Jason
 */
public class ConfigurationReader
{
    private File file;
    private Dictionary dictionary;
    private BufferedReader bufferedReader;
    
    public ConfigurationReader(String fileName) throws IOException
    {
        try
        {
            dictionary = new Hashtable();
            bufferedReader = new BufferedReader(new FileReader(file));
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

    public void load(){
        
    }
}
