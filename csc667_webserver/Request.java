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
 * @author Jason
 */
public class Request
{
    private String uri;
    private String verb;
    private String httpVersion;
    private Map<String, String> headers;
    private String[] headerLine;
    private String[] requestLine;
    private String messageBody;
    private BufferedReader bufferedReader;
    
    public Request(String test)
    {
        
    }
    
    public Request(InputStream client) throws BadRequest, IOException
    {
        bufferedReader = new BufferedReader(new InputStreamReader(client));
        headers = new HashMap<String, String>();
    }
    
    public void parse() throws BadRequest, IOException
    {
        generateRequestLine();
        generateHeaders();
        generateBody();
    }
    
    private void generateRequestLine() throws BadRequest, IOException
    {
        String line = getStringCheckNull();
        //Request Line: Method Request-URI HTTP-Version CRLF.
        requestLine = line.split(" ", 3);
        verb = requestLine[0];
        uri = requestLine[1];
        httpVersion = requestLine[2];
    }
    
    private void generateHeaders() throws BadRequest, IOException
    {
        //An empty line seperates headers from body. Read lines until empty line is found.
        String line;
        while(!(line = getStringCheckNull()).equals(""))
        {
            headerLine = line.split(":", 2);
            headers.put(headerLine[0], headerLine[1]);
        }
    }
    
    private void generateBody() throws IOException
    {
        //Is it possible to get the body this way without using content-length header? What is content-length needed for?
        messageBody = "";
        String line;
        while ((line = bufferedReader.readLine()) != null)
        {
            messageBody += line;
        }
    }
    
    private String getStringCheckNull() throws BadRequest, IOException
    {
        String line = bufferedReader.readLine();
        if(line == null)
        {
            throw new BadRequest("Error 400: Bad Request.");
        }
        
        return line;
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
    
    public String getBody()
    {
        return messageBody;
    }
}
