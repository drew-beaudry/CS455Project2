package com.cs455.project2.clientside;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.cs455.project2.clientside.api.IConnectionUtil;
import com.cs455.project2.shared.EncryptDecryptString;
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
      
      //Encrypt RequestType + Request
      String encryptedRequest = EncryptDecryptString.encrypt(requestType + ":" + request);
      //Send request
      outToServer.writeBytes(encryptedRequest + "\n");
      
      //Wait for response from server
      return inFromServer.readLine();
      
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
		return null;
	}

}
