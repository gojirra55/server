/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

/**
 *
 * @author Josh and Jason
 */
public class ResponseFactory {
    public Response getResponse(Request request, Resource resource){
        Response response = new Response(resource);
        
        int code;
        switch (request.getVerb())
        {
            case "PUT":     code = 201;
                            break;
            case "DELETE":  code = 201;
                            break;
            case "GET":     code = 201;
                            break;
            case "POST":    code = 201;
                            break;
            case "HEAD":    code = 201;
                            break;
            default:        code = 400;
                            break;
        }
        response.setCode(code);
        
        return response;
    }
}
