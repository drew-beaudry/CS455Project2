package com.cs455.project2.serverside.api;

public interface IServerInterface {
	 String provideMainMenu();
	 String provideRegistrationMenu();
	 String providePasswordChangeMenu();
	 String provideLoginMenu();
	
	 String handleRegistrationMenuResponse(String response);
	 String handlePasswordChangeResponse(String response);
	 String handleLoginMenuResponse(String response);
}
