/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

/**
 *
 * @author Jason
 */
public class BadRequest extends Exception
{
    public BadRequest(){}
    
    public BadRequest(String message)
    {
        super(message);
    }
}
