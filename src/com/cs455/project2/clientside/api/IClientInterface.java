package com.cs455.project2.clientside.api;

import com.cs455.project2.shared.RequestTypeEnum;

/**
 * Console input should be handled in this class. Scanners and such should exist
 * here.
 * 
 * @author dbeaudry
 *
 */
public interface IClientInterface {

	void handleUserInput();

	void validateUserInput(RequestTypeEnum requestType, String input);

}
