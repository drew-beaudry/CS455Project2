package com.cs455.project2.serverside;

import com.cs455.project2.serverside.api.ILoginHandler;

public class LoginHandler implements ILoginHandler {

	@Override
	public boolean performLogin(String uid, String pass) {
		FileIO fileIO = new FileIO();
		String[] file = fileIO.readFromFile();
		boolean loginStatus = false;
		int userIndex = 0;
		try {
			for (userIndex = 0; userIndex < file.length; userIndex++) {
				String[] line = file[userIndex].split(":");
				if (line[0].equals(uid)) {
					String[] passwordSalt = line[1].split("\\*");
					HashUtility hashutility = new HashUtility();
					String encryptedPass = hashutility.encrypt(pass, Integer.parseInt(passwordSalt[1]));
					if (encryptedPass.equals(passwordSalt[0])) {
						loginStatus = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return loginStatus;
	}
}
