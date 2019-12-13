package com.cs455.project2.serverside;

import com.cs455.project2.serverside.api.IRequestHandler;

public class ServerMain {

  /**
   * Start the server
   *
   * @param args
   */
  public static void main(String[] args) {
    IRequestHandler requestHandler = new RequestHandler();
    requestHandler.startServer();
  }
}
