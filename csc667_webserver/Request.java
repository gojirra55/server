/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.io.*;
import java.util.HashMap;

/**
 *
 * @author Josh and Jason
 */
public class Request
{
    private String uri;
    private String body; //UML Diagram shows type "?"... ?
    private String verb;
    private String httpVersion;
    private HashMap headers;
    private String[] requestLine;
    private String headerLine;
    private String messageBody;
    private ResponseFactory responseFactory = new ResponseFactory();
    private Response response;
    private BufferedReader bufferedReader;
    public Request(String test)
    {
        
    }
    
    public Request(InputStream client) throws BadRequest, IOException
    {
        bufferedReader = new BufferedReader(new InputStreamReader(client));
        getRequestLine();
        getHeaders();
        
        
        //
        headerLine = bufferedReader.readLine();
        
        messageBody = "";
        while (!messageBody.contains("END"))
        {
            messageBody += bufferedReader.readLine();
        }
        
        
        
        
        uri = line[1];
        body = line[2];
    }
    
    public void parse() throws IOException
    {
        HttpdConf httpdConf = new HttpdConf(body);
        Resource resource = new Resource(uri, httpdConf);
        response = responseFactory.getResponse(this, resource);
    }
    
    private void getRequestLine() throws BadRequest, IOException
    {
        String line;
        if((line = bufferedReader.readLine()) == null)
        {
            throw new BadRequest("Error 400: Bad Request.");
        } 
        //Request Line: Method Request-URI HTTP-Version CRLF.
        requestLine = line.split(" ", 3);
        verb = requestLine[0];
        uri = requestLine[1];
        httpVersion = requestLine[2];
    }
    
    private void getHeaders()
    {
        String line;
        while((line = bufferedReader.readLine() != null)
        {
            String headerLine = headers.split
            headers.put()
        }
        
            
        
    }
    
    //Accessors
    public String getUri()
    {
        return uri;
    }
    
    public String getVerb()
    {
        return verb;
    }
    
    public String getHttpVersion()
    {
        return httpVersion;
    }
}
