/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Josh and Jason
 */
public class Htaccess extends ConfigurationReader
{
    private Htpassword userFile;
    private String authType;
    private String authName;
    private String require;
    private BufferedReader bufferedReader;
    
    public Htaccess(String fileName) throws IOException{
        super(fileName);
        this.userFile = new Htpassword(fileName);
        this.authName = new String();
        this.authType = new String();
        this.require = new String();
        this.bufferedReader = new BufferedReader(new FileReader(getFile()));
    }
    
    public void load() throws IOException{
       
    }
    public boolean isAuthorized(String username, String password){
        return true;
    }
}
