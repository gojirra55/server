/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.io.IOException;

/**
 *
 * @author Josh
 */
public class Htaccess extends ConfigurationReader
{
    private Htpassword userFile; //assumption: need library for this to be fixed
    private String authType;
    private String authName;
    private String require;
    
    public Htaccess(String fileName) throws IOException{
        super(fileName);
    }
    
    public void load(){
        //add code here
    }
    public boolean isAuthorized(String username, String password){
        return true;
    }
}
