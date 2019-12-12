package com.cs455.project2.clientside.api;

/**
 * Console input should be handled in this class. Scanners and such should exist here.
 *
 * @author dbeaudry
 */
public interface IClientInterface {

  void handleUserInput();

  boolean validateUserInput(String requestType, String input);
}
