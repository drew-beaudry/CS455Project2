package com.cs455.project2.serverside.api;

import com.cs455.project2.serverside.User;
import com.cs455.project2.serverside.exception.EmailAlreadyRegisteredException;
import com.cs455.project2.serverside.exception.PasswordLengthException;
import com.cs455.project2.serverside.exception.UIDAlreadyRegisteredException;

public interface IRegistrationHandler {

	/**
	 * Handles the registration of a new user
	 * 
	 * @param uid
	 * @param pass
	 * @param fullName
	 * @param email
	 * @throws UIDAlreadyRegisteredException
	 * @throws EmailAlreadyRegisteredException
	 * @throws PasswordLengthException
	 */
	public User registerUser(String uid, String pass, String fullName, String email)
			throws UIDAlreadyRegisteredException, EmailAlreadyRegisteredException, PasswordLengthException;

}
