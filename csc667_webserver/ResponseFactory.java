/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

/**
 *
 * @author Josh
 */
public class ResponseFactory {
    public Response getResponse(Request request, Resource resource){
        Response response = new Response(resource);
        //What to use resource for? -Jason
        
        return response;
    }
}
