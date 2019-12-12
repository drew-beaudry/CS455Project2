package com.cs455.project2.serverside;

import java.util.Random;

import com.cs455.project2.serverside.api.IServerInterface;
import com.cs455.project2.serverside.exception.EmailAlreadyRegisteredException;
import com.cs455.project2.serverside.exception.InvalidPasswordException;
import com.cs455.project2.serverside.exception.PasswordLengthException;
import com.cs455.project2.serverside.exception.UIDAlreadyRegisteredException;
import com.cs455.project2.serverside.exception.UIDNotFoundException;

public class ServerInterface implements IServerInterface {

	@Override
	public String provideMainMenu() {
		return "Welcome! Please pick from the following options\n" + "1: Login\n" + "2: Register New User\n"
				+ "3: Change Password";
	}

	@Override
	public String provideRegistrationMenu() {
		// TODO Auto-generated method stub
		return "Registration: \n" + "Enter your username, full name, email, and password (seperate each by comma)";
	}

	@Override
	public String providePasswordChangeMenu() {
		// TODO Auto-generated method stub
		return "Password Change: \n" + "Enter your username, old password, and new password";
	}

	@Override
	public String provideLoginMenu() {
		// TODO Auto-generated method stub
		return "Sign in: \n" + "Enter your username and password";
	}


	@Override
	public String handleRegistrationMenuResponse(String message) {
		String uid,fullName,email,password;
		String[] input = message.split(",");
		uid = input[0];
		fullName = input[1];
		email = input[2];
		password = input[3];
		String returnMessage = "Success: User registered";
		RegistrationHandler registrationhandler = new RegistrationHandler();
		try {
			//Create a new user object given the passed parameters
			User user = registrationhandler.registerUser(uid, password, fullName, email);
			HashUtility hashUtil = new HashUtility();
			int salt = (new Random()).nextInt(90000000) + 10000000;
			String hashedPass = hashUtil.encrypt(user.getPass(), salt);
			user.setPass(hashedPass);
			FileIO fileio = new FileIO();
			fileio.appendToFile(user.getUid()+":"+user.getPass()+":"+user.getFullName()+":"+user.getEmail());
			
			
		} catch (UIDAlreadyRegisteredException e) {
			returnMessage = "Error: username already taken";
			e.printStackTrace();
		} catch (EmailAlreadyRegisteredException e) {
			returnMessage = "Error: Email already taken";
			e.printStackTrace();
		} catch (PasswordLengthException e) {
			returnMessage = "Error: Password must be 8 or more characters";
			e.printStackTrace();
		}
		
		return returnMessage;
		
		// TODO Auto-generated method stub

	}

	@Override
	public String handlePasswordChangeResponse(String message) {
		// TODO Auto-generated method stub
		String uid, oldPass, newPass;
		String[] input = message.split(" ");
		uid = input[0];
		oldPass = input[1];
		newPass = input[2];
		String returnMessage="Success: Password was changed succesfully";
		PasswordChangeUtility passwordutility = new PasswordChangeUtility();
		try {
			passwordutility.changePassword(uid, newPass, oldPass);
		} catch (UIDNotFoundException e) {
			returnMessage = "Error: Username not found";
			e.printStackTrace();
		} catch (InvalidPasswordException e) {
			returnMessage = "Error: Old password is invalid";
			e.printStackTrace();
		} catch (PasswordLengthException e) {
			returnMessage = "Error: New password must be 8 or more characters";
			e.printStackTrace();
		}

		return returnMessage;
	}

	@Override
	public String handleLoginMenuResponse(String message) {
		LoginHandler loginHandler = new LoginHandler();
		String[] messageArray = message.split("*");
		String uid = messageArray[0];
		String pass = messageArray[1];

		boolean success = loginHandler.performLogin(uid, pass);
		return "";
	}

}
