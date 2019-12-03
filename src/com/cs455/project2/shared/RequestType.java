package com.cs455.project2.shared;

/**
 * Indicates the type of request being transmitted
 * <p>
 * GET requests should be used when retrieving a menu of options<br>
 * POST requests should be used when responding to the menu with data
 * 
 * @author dbeaudry
 *
 */
public enum RequestType {
	GET_MAIN_MENU(), POST_MAIN_MENU(), GET_REGISTRATION_MENU(), POST_REGISTRATIN_MENU(), GET_PASSWORD_CHANGE_MENU(),
	POST_PASSWORD_CHANGE_MENU(), GET_LOGIN_MENU(), POST_LOGIN_MENU();
}
