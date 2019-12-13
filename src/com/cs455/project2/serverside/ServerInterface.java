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
	// generates main menu
	public String provideMainMenu() {
		return "Welcome! Please pick from the following options\n" + "1: Login\n" + "2: Register New User\n"
				+ "3: Change Password\n" + "4: Exit\n" + "EOS\n";
	}

	@Override
	// generates registration menu
	public String provideRegistrationMenu() {
		// TODO Auto-generated method stub
		return "Registration: \n" + "Enter your username, full name, email, and password (seperate each by comma)\n"
				+ "EOS\n";
	}

	@Override
//generates password change menu
	public String providePasswordChangeMenu() {
		// TODO Auto-generated method stub
		return "Password Change: \n" + "Enter your username, old password, and new password (seperate each by comma)\n"
				+ "EOS\n";
	}

	@Override
	//generates login menu
	public String provideLoginMenu() {
		// TODO Auto-generated method stub
		return "Sign in: \n" + "Enter your username and password (seperate each by comma)\n" + "EOS\n";
	}

	@Override
	//gets input for user main menu choice
	public String handleRegistrationMenuResponse(String message) {
		String uid, fullName, email, password;
		String[] input = message.split(",");
		//sets user input to input array
		uid = input[0];
		fullName = input[1];
		email = input[2];
		password = input[3];
		String returnMessage = "Success: User registered\nEOS\n";
		RegistrationHandler registrationhandler = new RegistrationHandler();
		try {
			// Create a new user object given the passed parameters
			
			//tries to register user and encrypts the password
			User user = registrationhandler.registerUser(uid, password, fullName, email);
			HashUtility hashUtil = new HashUtility();
			int salt = (new Random()).nextInt(90000000) + 10000000;
			String hashedPass = hashUtil.encrypt(user.getPass(), salt);
			user.setPass(hashedPass);
			FileIO fileio = new FileIO();
			//writes to file
			fileio.appendToFile(user.getUid() + ":" + user.getPass() + "*" + salt + ":" + user.getFullName() + ":"
					+ user.getEmail());
			//exceptions 
		} catch (UIDAlreadyRegisteredException e) {
			returnMessage = "Error: username already taken\nEOS\n";
			e.printStackTrace();
		} catch (EmailAlreadyRegisteredException e) {
			returnMessage = "Error: Email already taken\nEOS\n";
			e.printStackTrace();
		} catch (PasswordLengthException e) {
			returnMessage = "Error: Password must be 8 or more characters\nEOS\n";
			e.printStackTrace();
		}

		return returnMessage;

		// TODO Auto-generated method stub

	}

	@Override
	//gets input for user input on password change menu
	public String handlePasswordChangeResponse(String message) {
		// gets user input and sets to array
		String uid, oldPass, newPass;
		String[] input = message.split(",");
		uid = input[0];
		oldPass = input[1];
		newPass = input[2];
		String returnMessage = "Success: Password was changed succesfully\nEOS\n";
		PasswordChangeUtility passwordutility = new PasswordChangeUtility();
		//exceptions for possible errors
		try {
			passwordutility.changePassword(uid, newPass, oldPass);
		} catch (UIDNotFoundException e) {
			returnMessage = "Error: Username not found\nEOS\n";
			e.printStackTrace();
		} catch (InvalidPasswordException e) {
			returnMessage = "Error: Old password is invalid\nEOS\n";
			e.printStackTrace();
		} catch (PasswordLengthException e) {
			returnMessage = "Error: New password must be 8 or more characters\nEOS\n";
			e.printStackTrace();
		}

		return returnMessage;
	}

	@Override
	public String handleLoginMenuResponse(String message) {
		LoginHandler loginHandler = new LoginHandler();
		//handles input for login and sets to message array
		String[] messageArray = message.split(",");
		String uid = messageArray[0];
		String pass = messageArray[1];

		boolean success = loginHandler.performLogin(uid, pass);
		if (success)
			return "Log In Success\nEOS\n";
		return "Log In Failure. Username or Password not valid.\nEOS\n";
	}
}
