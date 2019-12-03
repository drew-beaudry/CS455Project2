package com.cs455.project2.serverside.api;

import com.cs455.project2.serverside.exception.InvalidPasswordException;
import com.cs455.project2.serverside.exception.PasswordLengthException;
import com.cs455.project2.serverside.exception.UIDNotFoundException;

public interface IPasswordChangeUtility {
	/**
	 * Change the password for a registered user
	 * 
	 * @param uid
	 * @param newPass
	 * @param oldPass
	 * @throws UIDNotFoundException
	 * @throws InvalidPasswordException
	 * @throws PasswordLengthException
	 */
	void changePassword(String uid, String newPass, String oldPass)
			throws UIDNotFoundException, InvalidPasswordException, PasswordLengthException;
}
