/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 *
 * @author Jason
 */
public class Response {
    private int code;
    public String reasonPhrase;
    public Resource resource;
    
    public Response(Resource resource)
    {
        this.resource = resource;
    }
    
    public void send(OutputStream out)
    {
        PrintStream printStream = new PrintStream(out);
        printStream.print("TESTING: HELLO!!");
    }
    
    public void setCode(int code)
    {
        this.code = code;
    }
    
    public int getCode()
    {
        return code;
    }
}
