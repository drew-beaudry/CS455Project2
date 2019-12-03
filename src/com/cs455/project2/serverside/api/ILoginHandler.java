package com.cs455.project2.serverside.api;

public interface ILoginHandler {
	/**
	 * Checks the provided uid and pass for a match in the database file
	 * 
	 * @param uid
	 * @param pass
	 * @return <code>true</code> if login was successful
	 */
	boolean performLogin(String uid, String pass);
}
