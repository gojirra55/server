/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc667_webserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Josh and Jason
 */
public class Htaccess extends ConfigurationReader {

    private Htpassword userFile;
    private String authType;
    private String authName;
    private String require;
    private BufferedReader bufferedReader;

    public Htaccess(String fileName) throws IOException {
        super(fileName);
        this.userFile = new Htpassword(fileName);
        this.authName = new String();
        this.authType = new String();
        this.require = new String();
        //this.bufferedReader = new BufferedReader(new FileReader(getFile()));
    }

    public void load() throws IOException {
        //read in _.htaccess file
        //read each line and store it to authType,authName,require respectively
        try {
            FileReader fileReader = new FileReader(this.getFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            String splitLine[];

            while (hasMoreLines()) {
                splitLine = line.split("", 2);
                if (splitLine[0].equals("AuthUserFile")) {
                    this.userFile.parseLine(splitLine[1]);
                } else if (splitLine[0].equals("AuthType")) {
                    this.authType = splitLine[1];
                } else if (splitLine[0].equals("AuthName")) {
                    this.authName = splitLine[1];
                } else if (splitLine[0].equals("Require")) {
                    this.require = splitLine[1];
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Caught FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }

    }

    public boolean isAuthorized(String username, String password) { //might try isAuthorized(String authInfo
        boolean authCheck = false;
        
        if (Htpassword.isAuthorized(username,password)) {
            
        }

        return authCheck;
    }
}
