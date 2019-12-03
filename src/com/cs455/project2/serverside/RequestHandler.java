package com.cs455.project2.serverside;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.cs455.project2.serverside.api.IRequestHandler;

public class RequestHandler implements IRequestHandler {

	@Override
	public void startServer() {
		try {
			// Create the welcoming socket at port 1111
			ServerSocket welcomeSocket = new ServerSocket(1111);
			System.out.println("Login Server is running");

			while (true) {
				// Detect the active connection
				Socket connectionSocket = welcomeSocket.accept();
				LoginServerRunnable serverRunnable = new LoginServerRunnable(connectionSocket);

				// Begin a thread for the connection
				Thread thread = new Thread(serverRunnable);
				thread.start();
			}
		} catch (IOException e) {
			System.out.println("Login Server failed to start");
			e.printStackTrace();
		}

	}

}
