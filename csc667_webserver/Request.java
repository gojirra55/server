/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.io.*;
import java.util.Dictionary;

/**
 *
 * @author Jason Newman
 */
public class Request
{
    private String url;
    private Object body; //UML Diagram shows type "?"... ?
    private String verb;
    private String httpVersion;
    private Dictionary headers;
    
    public Request(String test)
    {
    }
    
    public Request(InputStream client)
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client));
        String line = "";
        
        //This combines all lines until it reaches "END" and makes one request.
        //Is that the correct implementation? - Jason
        while (!line.contains("END"))
        {
            //line += bufferedReader.readLine();
        }
        
        
    }
    
    public void parse()
    {
        //Code here.
    }
}
