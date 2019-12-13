package com.cs455.project2.serverside;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.cs455.project2.serverside.api.IHashUtility;

public class HashUtility implements IHashUtility {

	@Override
	public String encrypt(String plainText, int salt) {
		// TODO Auto-generated method stub
		String cipherText = "";
		MessageDigest digest;
		plainText += salt;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(plainText.getBytes(StandardCharsets.UTF_8));
			cipherText = bytesToHex(encodedhash);
			} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cipherText;
	}
	
	private String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}


}
