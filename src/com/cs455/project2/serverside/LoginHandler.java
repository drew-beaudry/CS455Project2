package com.cs455.project2.serverside;

import com.cs455.project2.serverside.api.ILoginHandler;

public class LoginHandler implements ILoginHandler {

	@Override
	public boolean performLogin(String uid, String pass) {
		HashUtility hashUtility = new HashUtility();
    String hashedPass = hashUtility.encrypt(pass, 12345678);

    FileIO fileIO = new FileIO();
    String[] linesFromFile = fileIO.readFromFile();
    
    for(String line: linesFromFile) {
      //TODO
    }
		return false;
	}

}
