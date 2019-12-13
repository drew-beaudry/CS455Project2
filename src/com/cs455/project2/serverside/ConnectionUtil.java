package com.cs455.project2.serverside;

import java.io.DataOutputStream;
import java.io.IOException;

import com.cs455.project2.shared.EncryptDecryptString;
import com.cs455.project2.shared.MessageIDCounter;

public class ConnectionUtil {

  /**
   * Send encrypted data to the client
   *
   * @param outToClient
   * @param request
   * @throws IOException
   */
  public void performRequest(DataOutputStream outToClient, String request) throws IOException {
    //Add nonce to request
    request = MessageIDCounter.getInstance().getNextNonce() + "*" + request;
    //Encrypt request
    String encryptedRequest = EncryptDecryptString.encrypt(request);
    //Send request
    outToClient.writeBytes(encryptedRequest + "\n");
  }
}
