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
        String message = requestArray[1];

        ServerInterface serverInterface = new ServerInterface();
        
        switch (requestType) {
          case RequestType.GET_MAIN_MENU:
            String mainMenu = serverInterface.provideLoginMenu();
            break;
          case RequestType.POST_MAIN_MENU:
            serverInterface.handleMainMenuResponse(message);
            break;
          case RequestType.GET_REGISTRATION_MENU:
            String registrationMenu = serverInterface.provideRegistrationMenu();
            break;
          case RequestType.POST_REGISTRATIN_MENU:
            serverInterface.handleRegistrationMenuResponse(message);
            break;
          case RequestType.GET_PASSWORD_CHANGE_MENU:
            String passwordChangeMenu = serverInterface.providePasswordChangeMenu();
            break;
          case RequestType.POST_PASSWORD_CHANGE_MENU:
            serverInterface.handlePasswordChangeResponse(message);
            break;
          case RequestType.GET_LOGIN_MENU:
            String loginMenu = serverInterface.provideLoginMenu();
            break;
          case RequestType.POST_LOGIN_MENU:
            serverInterface.handleLoginMenuResponse(message);
            break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
