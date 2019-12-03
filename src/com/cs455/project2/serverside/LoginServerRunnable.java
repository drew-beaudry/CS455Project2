package com.cs455.project2.serverside;

import java.net.Socket;

public class LoginServerRunnable implements Runnable {

	private Socket socket;

	/**
	 * Creates the login server runnable so that the server may accept connections
	 * on multiple threads
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

	}

}
