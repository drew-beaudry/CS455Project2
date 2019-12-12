package com.cs455.project2.clientside;

import java.util.Scanner;

import com.cs455.project2.clientside.api.IClientInterface;
import com.cs455.project2.shared.RequestType;

public class ClientInterface implements IClientInterface {

  @Override
  public void handleUserInput() {
    // Declare Scanner and Connection Utility
    Scanner scanner = new Scanner(System.in);
    ConnectionUtil connectionUtil = new ConnectionUtil();

    // Request and Print Main Menu
    String mainMenu = connectionUtil.performRequest(RequestType.GET_MAIN_MENU, "");
    System.out.println(mainMenu);

    // Validate the main menu choice
    // Request the chosen menu
    String nextMenu = "";
    String requestType = "";
    while (nextMenu.isEmpty()) {
      try {
        int mainMenuResponse = Integer.parseInt(scanner.nextLine());
        switch (mainMenuResponse) {
          case 1:
            requestType = RequestType.GET_LOGIN_MENU;
            nextMenu = connectionUtil.performRequest(requestType, "");
            break;
          case 2:
            requestType = RequestType.GET_REGISTRATION_MENU;
            nextMenu = connectionUtil.performRequest(requestType, "");
            break;
          case 3:
            requestType = RequestType.GET_PASSWORD_CHANGE_MENU;
            nextMenu = connectionUtil.performRequest(requestType, "");
            break;
          case 4:
            System.out.println("Goodbye!");
            scanner.close();
            System.exit(0);
          default:
            throw new Exception();
        }
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Please input an integer");
        continue;
      }
    }

    // Output the chosen menu
    System.out.println(nextMenu);
    // Get user input
    String message = scanner.nextLine();
    String statusMessage = "";
    // Send a response to the menu depending on what menu we requested
    switch (requestType) {
      case RequestType.GET_LOGIN_MENU:
        requestType = RequestType.POST_LOGIN_MENU;
        if (validateUserInput(requestType, message))
          statusMessage = connectionUtil.performRequest(requestType, message);
        else System.out.println("Invalid Input");
        break;
      case RequestType.GET_REGISTRATION_MENU:
        requestType = RequestType.POST_REGISTRATION_MENU;
        if (validateUserInput(requestType, message))
          statusMessage = connectionUtil.performRequest(requestType, message);
        else System.out.println("Invalid Input");
        break;
      case RequestType.GET_PASSWORD_CHANGE_MENU:
        requestType = RequestType.POST_PASSWORD_CHANGE_MENU;
        if (validateUserInput(requestType, message))
          statusMessage = connectionUtil.performRequest(requestType, message);
        else System.out.println("Invalid Input");
        break;
    }

    // Output the result from the performed operation
    System.out.println(statusMessage);
  }

  @Override
  public boolean validateUserInput(String requestType, String input) {
    String[] inputArray;
    inputArray = input.split(",");
    // Check that number of parameters match expected
    switch (requestType) {
      case RequestType.POST_LOGIN_MENU:
        if (inputArray.length == 2) return true;
        break;
      case RequestType.POST_REGISTRATION_MENU:
        if (inputArray.length == 4) return true;
        break;
      case RequestType.POST_PASSWORD_CHANGE_MENU:
        if (inputArray.length == 3) return true;
        break;
    }

    return false;
  }
}
