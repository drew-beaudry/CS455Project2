package com.cs455.project2.serverside;

import java.util.Random;

import com.cs455.project2.serverside.api.IPasswordChangeUtility;
import com.cs455.project2.serverside.exception.InvalidPasswordException;
import com.cs455.project2.serverside.exception.PasswordLengthException;
import com.cs455.project2.serverside.exception.UIDNotFoundException;

public class PasswordChangeUtility implements IPasswordChangeUtility {

	@Override
	public void changePassword(String uid, String newPass, String oldPass)
			throws UIDNotFoundException, InvalidPasswordException, PasswordLengthException {
		//reads in file
		FileIO fileio = new FileIO();
		String[] file = fileio.readFromFile();
		//uid:pass:fullname:email
		boolean userValid = false;
		boolean passValid = false;
		int userIndex = 0;
		for(userIndex = 0;userIndex<file.length;userIndex++) {
			String[] line = file[userIndex].split(":");
			//checks if user exists
			if(line[0].equals(uid)) {
				userValid = true;
				String [] passwordSalt = line[1].split("\\*");
				//encrypts user input and checks to see if it matches passsword saved in file
				HashUtility hashutility = new HashUtility();
				String encryptedPass = hashutility.encrypt(oldPass, Integer.parseInt(passwordSalt[1]));
				if(passwordSalt[0].equals(encryptedPass)) {
					passValid = true;
					break;
				}
				else {
					throw new InvalidPasswordException();
				}
			}
		}
		if(userValid == false) {
			//exception when no ID matches user input
			throw new UIDNotFoundException();
		}
		if(newPass.length()<8) {
			//exception when password is not long enough
			throw new PasswordLengthException();
		}
		
		HashUtility hashUtil = new HashUtility();
		//generates salt
		int salt = (new Random()).nextInt(90000000) + 10000000;
		String hashedPass = hashUtil.encrypt(newPass, salt);
		//saves new password to string and writes to file
		String[] line = file[userIndex].split(":");
		file[userIndex]=line[0]+":"+hashedPass+"*"+salt+":"+line[2]+":"+line[3];
		
		fileio.writeToFile(file);
		
	}

}
