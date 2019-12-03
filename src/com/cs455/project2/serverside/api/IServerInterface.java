package com.cs455.project2.serverside.api;

public interface IServerInterface {
	 String provideMainMenu();
	 String provideRegistrationMenu();
	 String providePasswordChangeMenu();
	 String provideLoginMenu();
	
	 void handleMainMenuResponse(String response);
	 void handleRegistrationMenuResponse(String response);
	 void handlePasswordChangeResponse(String response);
	 void handleLoginMenuResponse(String response);
}
