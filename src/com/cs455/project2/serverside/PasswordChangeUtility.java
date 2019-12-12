package com.cs455.project2.serverside;

import com.cs455.project2.serverside.api.IPasswordChangeUtility;
import com.cs455.project2.serverside.exception.InvalidPasswordException;
import com.cs455.project2.serverside.exception.PasswordLengthException;
import com.cs455.project2.serverside.exception.UIDNotFoundException;

public class PasswordChangeUtility implements IPasswordChangeUtility {

	@Override
	public void changePassword(String uid, String newPass, String oldPass)
			throws UIDNotFoundException, InvalidPasswordException, PasswordLengthException {
		// TODO Auto-generated method stub
		FileIO fileio = new FileIO();
		String[] file = fileio.readFromFile();
		//uid:pass:fullname:email
		boolean userValid,passValid = false;
		for(int i = 0;i<file.length;i++) {
			String[] line = file[i].split(":");
			if(line[0].equals(uid)) {
				userValid = true;
				if(line[1].equals(oldPass)) {
					passValid = true;
				}
				else {
					throw new InvalidPasswordException();
				}
			}
		}
		
		if(newPass.length()<8) {
			throw new PasswordLengthException();
		}
		
		

	}

}
