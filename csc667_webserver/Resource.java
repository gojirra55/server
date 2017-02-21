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
public class Resource {
    private String absolutePath;
    private boolean isScript;
    private boolean isProtected;
    
    public Resource(String uri, HttpdConf config){

        //Check if uri is script aliased.
        if (config.checkScriptAliases(uri)) //Add function that checks if string contains alias to HttpdConf.
        {
            isScript = true;
        }
        else
        {
            absolutePath = uri;
        }
        
        //Check if protected.
    }
    public String absolutePath(){
        return absolutePath;
    }
    
    public boolean isScript()
    {
        return isScript;
    }
    
    public boolean isProtected(){
        return isProtected;
    }
}
