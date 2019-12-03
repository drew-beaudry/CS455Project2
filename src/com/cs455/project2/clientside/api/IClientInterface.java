package com.cs455.project2.clientside.api;

import com.cs455.project2.shared.RequestType;

/**
 * Console input should be handled in this class. Scanners and such should exist
 * here.
 * 
 * @author dbeaudry
 *
 */
public interface IClientInterface {

	void handleUserInput();

	void validateUserInput(RequestType requestType, String input);

}
