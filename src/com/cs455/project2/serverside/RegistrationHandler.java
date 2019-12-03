package com.cs455.project2.serverside;

import com.cs455.project2.serverside.api.IRegistrationHandler;
import com.cs455.project2.serverside.exception.EmailAlreadyRegisteredException;
import com.cs455.project2.serverside.exception.PasswordLengthException;
import com.cs455.project2.serverside.exception.UIDAlreadyRegisteredException;

public class RegistrationHandler implements IRegistrationHandler {

	@Override
	public User registerUser(String uid, String pass, String fullName, String email)
			throws UIDAlreadyRegisteredException, EmailAlreadyRegisteredException, PasswordLengthException {
		// TODO Auto-generated method stub
		return null;
	}

}
