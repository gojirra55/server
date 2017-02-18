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
    private String uri;
    private String body; //UML Diagram shows type "?"... ? Maybe should be File?
    private String verb;
    private String httpVersion;
    private Dictionary headers;
    private String[] line;
    private ResponseFactory responseFactory = new ResponseFactory();
    private Response response;
    
    public Request(String test)
    {
    }
    
    public Request(InputStream client) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client));
        
        //This combines all lines until it reaches "END" and makes one request.
        //Is that the correct implementation? - Jason
        line[0] = bufferedReader.readLine();
        line[1] = bufferedReader.readLine();
        line[2] = "";
        
        while (!line[2].contains("END"))
        {
            line[2] += bufferedReader.readLine();
        }
        
        parse();
        
        
    }
    
    public void parse()
    {
        //Read line and create approrpiate Responses from ResponseFactory?
        
        //Not sure about these. - Jason
        verb = line[0]; 
        uri = line[1];
        body = line[2];
        
        HttpdConf httpdConf = new HttpdConf(body);
        Resource resource = new Resource(uri, httpdConf);
        response = responseFactory.getResponse(this, resource);
    }
}
