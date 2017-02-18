/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.util.*;
/**
 *
 * @author Josh
 */
class Htpassword
{
    private Dictionary users;
    
    public void load(){
        
    }
    public boolean isAuthorized(String username, String password){
        return true;
    }
}
