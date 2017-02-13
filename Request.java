/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.util.Dictionary;
import java.util.stream.Stream;

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
    
    public Request(Stream client)
    {
    }
    
    public void parse()
    {
        
    }
    
    //Accessors will go here.
}
