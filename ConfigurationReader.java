/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;
import java.io.File;

/**
 *
 * @author Josh
 */
public class ConfigurationReader
{
    private File file;
    private Dictionary dictionary;
    
    public ConfigurationReader(String fileName)
    {
        dictionary = new Hashtable();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file)))
        {
            int currentLineNum = 0;
            while (hasMoreLines())
            {
                dictionary.put(nextLine(), currentLineNum++);
            }
        }
    }

    public boolean hasMoreLines()
    {
        result = false;

        if (nextLine() != null)
        {
            result = true;
        }
        
        return result;
    }

    public String nextLine()
    {
        result = bufferedReader.readLine())
        return result;
    }

    public void load(){
        
    }
}
