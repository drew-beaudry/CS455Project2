package com.cs455.project2.clientside;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.cs455.project2.clientside.api.IConnectionUtil;
import com.cs455.project2.serverside.exception.ReplayException;
import com.cs455.project2.shared.EncryptDecryptString;
import com.cs455.project2.shared.MessageIDCounter;
public class ConnectionUtil implements IConnectionUtil {

	@Override
	public String performRequest(String requestType, String request) {
	  try {
	    //Declare Socket
      @SuppressWarnings("resource")
      Socket clientSocket = new Socket("127.0.0.1", 1111);
      //Declare input stream
      BufferedReader inFromServer =
          new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      //Declare output stream
      DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
      
      //Get next nonce
      int nonce = MessageIDCounter.getInstance().getNextNonce();
      
      //Encrypt RequestType + Request
      String encryptedRequest = EncryptDecryptString.encrypt(nonce + ":" + requestType + ":" + request);
      //Send request
      outToServer.writeBytes(encryptedRequest + "\n");
      
      //Get encrypted response back from server
      String encryptedResponse = inFromServer.readLine();
      //Decrypt Response
      String plaintextResponse =  EncryptDecryptString.decrypt(encryptedResponse);
      //Split response to gain nonce value
      String[] responseArray = plaintextResponse.split("\\*");
      try {
        //Check nonce
        MessageIDCounter.getInstance().checkAndSetNonce(Integer.parseInt(responseArray[0]));
        //Return message
        return responseArray[1];
      } catch (NumberFormatException | ReplayException e) {
        e.printStackTrace();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
		return null;
	}
}
