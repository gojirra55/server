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
    public Resource(String uri, HttpdConf config){
        
    }
    public String absolutePath(){
        return "";
    }
    public boolean isScript(){
        return true;
    }
    public boolean isProtected(){
        return true;
    }
}
