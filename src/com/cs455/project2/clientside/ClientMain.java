package com.cs455.project2.clientside;

public class ClientMain {

  public static void main(String[] args) {
    System.out.println("Client is running");
    while (true) {
      ClientInterface clientInterface = new ClientInterface();
      clientInterface.handleUserInput();
    }
  }
}
