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

	}

}
