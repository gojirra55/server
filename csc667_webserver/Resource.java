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

        //Check if uri is Alias, Script Alias, or path.
        if ((absolutePath = config.checkAliases(uri)) != null)
        {
            isScript = false;
        }
        else if ((absolutePath = config.checkScriptAliases(uri)) != null) //Add function that checks if string contains alias to HttpdConf.
        {
            isScript = true;
        }
        else
        {
            absolutePath = config.getDocRoot(); + uri;
        }
        
        //Resolve path (DOC_ROOT + URI);
        //Check if file.
        //If not File, append DirIndex, else absolute path.
        //Check if protected. Check if directory contains Htaccess.
    }
    public String absolutePath()
    {
        return absolutePath;
    }
    
    public boolean isScript()
    {
        return isScript;
    }
    
    public boolean isProtected()
    {
        return isProtected;
    }
}
