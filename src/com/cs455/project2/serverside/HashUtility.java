package com.cs455.project2.serverside;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.cs455.project2.serverside.api.IHashUtility;

public class HashUtility implements IHashUtility {

	@Override
	public String encrypt(String plainText, int salt) {
		//Gets plain text and salt and encrypts 
		String cipherText = "";
		MessageDigest digest;
		plainText += salt;
		try {
			//Create SHA-256 Digest
			digest = MessageDigest.getInstance("SHA-256");
			//Encode Hash to Byte Array
			byte[] encodedhash = digest.digest(plainText.getBytes(StandardCharsets.UTF_8));
			//Convert byte array to String
			cipherText = bytesToHex(encodedhash);
			} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return cipherText;
	}
	
	/**
	 * Convert byte array to String
	 * @param byte[]
	 * @return String
	 */
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
