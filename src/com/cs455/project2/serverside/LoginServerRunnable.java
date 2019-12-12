package com.cs455.project2.serverside;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.cs455.project2.shared.EncryptDecryptString;
import com.cs455.project2.shared.RequestType;

public class LoginServerRunnable implements Runnable {

  private Socket socket;

  /**
   * Creates the login server runnable so that the server may accept connections on multiple threads
   *
   * @param connectionSocket
   */
  public LoginServerRunnable(Socket connectionSocket) { // This method signature can change
    this.socket = connectionSocket;
  }

  @Override
  public void run() {
    try {
      processRequest();
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
    }
  }

  /** All server request processing code should go here */
  private void processRequest() {
    // Create input stream attached to the connection socket
    BufferedReader inFromClient;
    try {
      inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      // Create output stream attached to the connection socket
      DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());

      String encryptedRequest = inFromClient.readLine();

      if (!encryptedRequest.isEmpty()) {
        String request = EncryptDecryptString.decrypt(encryptedRequest);

        String[] requestArray = request.split(":");
        String requestType = requestArray[0];
        String message = "";
        if(requestArray.length > 1)
          message = requestArray[1];

        ServerInterface serverInterface = new ServerInterface();
        String statusMessage;
        switch (requestType) {
          case RequestType.GET_MAIN_MENU:
            String mainMenu = serverInterface.provideMainMenu();
            outToClient.writeBytes(mainMenu);
            break;
          case RequestType.GET_REGISTRATION_MENU:
            String registrationMenu = serverInterface.provideRegistrationMenu();
            outToClient.writeBytes(registrationMenu);
            break;
          case RequestType.POST_REGISTRATION_MENU:
            statusMessage = serverInterface.handleRegistrationMenuResponse(message);
            outToClient.writeBytes(statusMessage);
            break;
          case RequestType.GET_PASSWORD_CHANGE_MENU:
            String passwordChangeMenu = serverInterface.providePasswordChangeMenu();
            outToClient.writeBytes(passwordChangeMenu);
            break;
          case RequestType.POST_PASSWORD_CHANGE_MENU:
            statusMessage = serverInterface.handlePasswordChangeResponse(message);
            outToClient.writeBytes(statusMessage);
            break;
          case RequestType.GET_LOGIN_MENU:
            String loginMenu = serverInterface.provideLoginMenu();
            outToClient.writeBytes(loginMenu);
            break;
          case RequestType.POST_LOGIN_MENU:
            statusMessage = serverInterface.handleLoginMenuResponse(message);
            outToClient.writeBytes(statusMessage);
            break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
