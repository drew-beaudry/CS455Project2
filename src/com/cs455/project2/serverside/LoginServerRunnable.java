package com.cs455.project2.serverside;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.cs455.project2.serverside.api.IServerInterface;
import com.cs455.project2.serverside.exception.ReplayException;
import com.cs455.project2.shared.EncryptDecryptString;
import com.cs455.project2.shared.MessageIDCounter;
import com.cs455.project2.shared.RequestType;

public class LoginServerRunnable implements Runnable {

  private Socket socket;

  /**
   * Creates the login server runnable so that the server may accept connections on multiple threads
   *
   * @param connectionSocket
   */
  public LoginServerRunnable(Socket connectionSocket) {
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

  /** All server request processing code should go here 
   * @throws ReplayException */
  private void processRequest() throws ReplayException {
    // Create input stream attached to the connection socket
    BufferedReader inFromClient;
    try {
      inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      // Create output stream attached to the connection socket
      DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());

      //Get encrypted request from client
      String encryptedRequest = inFromClient.readLine();

      if (!encryptedRequest.isEmpty()) {
        //Decrypt request
        String request = EncryptDecryptString.decrypt(encryptedRequest);
        
        //Split request on delimiter
        String[] requestArray = request.split(":");
        //Check Nonce value to defend against replay
        int nonce = Integer.parseInt(requestArray[0]);
        MessageIDCounter.getInstance().checkAndSetNonce(nonce);
        
        //Get requestType
        String requestType = requestArray[1];
        String message = "";
        if(requestArray.length > 2)
          message = requestArray[2];

        IServerInterface serverInterface = new ServerInterface();
        ConnectionUtil connectionUtil = new ConnectionUtil();
        String statusMessage;
        nonce = MessageIDCounter.getInstance().getNextNonce();
        switch (requestType) {
          case RequestType.GET_MAIN_MENU:
            String mainMenu = serverInterface.provideMainMenu();
            connectionUtil.performRequest(outToClient, mainMenu);
            break;
          case RequestType.GET_REGISTRATION_MENU:
            String registrationMenu = serverInterface.provideRegistrationMenu();
            connectionUtil.performRequest(outToClient,registrationMenu);
            break;
          case RequestType.POST_REGISTRATION_MENU:
            statusMessage = serverInterface.handleRegistrationMenuResponse(message);
            connectionUtil.performRequest(outToClient,statusMessage);
            break;
          case RequestType.GET_PASSWORD_CHANGE_MENU:
            String passwordChangeMenu = serverInterface.providePasswordChangeMenu();
            connectionUtil.performRequest(outToClient,passwordChangeMenu);
            break;
          case RequestType.POST_PASSWORD_CHANGE_MENU:
            statusMessage = serverInterface.handlePasswordChangeResponse(message);
            connectionUtil.performRequest(outToClient,statusMessage);
            break;
          case RequestType.GET_LOGIN_MENU:
            String loginMenu = serverInterface.provideLoginMenu();
            connectionUtil.performRequest(outToClient,loginMenu);
            break;
          case RequestType.POST_LOGIN_MENU:
            statusMessage = serverInterface.handleLoginMenuResponse(message);
            connectionUtil.performRequest(outToClient,statusMessage);
            break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
