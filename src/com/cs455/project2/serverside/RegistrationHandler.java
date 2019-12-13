package com.cs455.project2.serverside;

import com.cs455.project2.serverside.api.IRegistrationHandler;
import com.cs455.project2.serverside.exception.EmailAlreadyRegisteredException;
import com.cs455.project2.serverside.exception.PasswordLengthException;
import com.cs455.project2.serverside.exception.UIDAlreadyRegisteredException;

public class RegistrationHandler implements IRegistrationHandler {

	@Override
	public User registerUser(String uid, String pass, String fullName, String email)
			throws UIDAlreadyRegisteredException, EmailAlreadyRegisteredException, PasswordLengthException {
		//reads in file
		FileIO fileio = new FileIO();
		String[] file = fileio.readFromFile();
		//uid:pass:fullname:email
		if(file.length>0) {
		for(int i = 0; i <file.length;i++) {
			//splits file into uid,pass,email,fullname
			String[] line = file[i].split(":");
			if(line.length==4) {
			if(line[0].equals(uid)) {
				//exception when user ID already exists
				throw new UIDAlreadyRegisteredException();
			}
			if(line[3].equals(email)) {
				//exception when email is already taken
				throw new EmailAlreadyRegisteredException();
			}
			}
		}
		}
		if(pass.length()<8) {
			//exception when password is too short
			throw new PasswordLengthException();
		}
		
		return new User(uid,pass,fullName,email);
	}

}
