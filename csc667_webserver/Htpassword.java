/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Base64;
import java.nio.charset.Charset;
import java.security.MessageDigest;

import java.io.IOException;

/**
 *
 * @author Josh and Jason
 */

public class Htpassword extends ConfigurationReader {

    private HashMap<String, String> passwords;
    //private HashMap<String, String> users;

    public Htpassword(String filename) throws IOException {
        System.out.println("Password file: " + filename);

        this.passwords = new HashMap<String, String>();
        this.load();//calls configReader load()
    }

    protected void parseLine(String line) {
        String[] tokens = line.split(":");

        if (tokens.length == 2) {
            passwords.put(tokens[0], tokens[1].replace("{SHA}", "").trim());
        }
    }
    public void load() throws FileNotFoundException{
        try{
            FileReader fileReader = new FileReader(this.getFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            String line = "";
            String splitLine[];
            
            while(hasMoreLines()){ //this "should" pass splitLine elements 0,1 into the parseLine respectively; Then store each in hashmap password, accordingly
                splitLine = line.split(":",2);
                if(splitLine[1].equals("{SHA}")){
                    parseLine(splitLine[1]);
                }else{
                    parseLine(splitLine[0]);
                }                
            }
            bufferedReader.close();
        } catch(FileNotFoundException e){
            System.err.println("Caught FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }

    public boolean isAuthorized(String authInfo) {
        // authInfo is provided in the header received from the client
        // as a Base64 encoded string.
        String credentials = new String(
                Base64.getDecoder().decode(authInfo),
                Charset.forName("UTF-8")
        );

        // The string is the key:value pair username:password
        String[] tokens = credentials.split(":");

        // TODO: implement this
        
        return false;
    }

    private boolean verifyPassword(String username, String password) {
        // encrypt the password, and compare it to the password stored
        // in the password file (keyed by username)
        // TODO: implement this
        return false;
    }

    private String encryptClearPassword(String password) {
        // Encrypt the cleartext password (that was decoded from the Base64 String
        // provided by the client) using the SHA-1 encryption algorithm
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
            byte[] result = mDigest.digest(password.getBytes());

            return Base64.getEncoder().encodeToString(result);
        } catch (Exception e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        return "";
    }
}