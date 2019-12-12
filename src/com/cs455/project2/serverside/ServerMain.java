package com.cs455.project2.serverside;

public class ServerMain {

  public static void main(String[] args) {
    RequestHandler requestHandler = new RequestHandler();
    requestHandler.startServer();
  }
}
