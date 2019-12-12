package com.cs455.project2.serverside;

import com.cs455.project2.serverside.api.IServerInterface;

public class ServerInterface implements IServerInterface {

	@Override
	public String provideMainMenu() {
	  return "Welcome! Please pick from the following options\n"
	      + "1: Login\n"
	      + "2: Register New User\n"
	      + "3: Change Password";
	}

	@Override
	public String provideRegistrationMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String providePasswordChangeMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String provideLoginMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleMainMenuResponse(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleRegistrationMenuResponse(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handlePasswordChangeResponse(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleLoginMenuResponse(String message) {
	  LoginHandler loginHandler = new LoginHandler();
	  String[] messageArray = message.split("*");
	  String uid = messageArray[0];
	  String pass = messageArray[1];
	  
	  boolean success = loginHandler.performLogin(uid, pass);
	}

}
