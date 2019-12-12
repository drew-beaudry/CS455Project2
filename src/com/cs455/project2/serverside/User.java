package com.cs455.project2.serverside;

/**
 * Model object for a registered user
 * 
 * @author dbeaudry
 *
 */
public class User {
	private String uid;
	private String pass;
	private String fullName;
	private String email;

	public User(String uid, String pass, String fullName, String email) {
		this.uid = uid;
		this.pass = pass;
		this.fullName = fullName;
		this.email = email;
	}

	public String getUid() {
		return uid;
	}

	public String getPass() {
		return pass;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
