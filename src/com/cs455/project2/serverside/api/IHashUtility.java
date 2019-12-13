package com.cs455.project2.serverside.api;

/**
 * Decrypts and encrypts text with a hash and salt
 * 
 * @author dbeaudry
 *
 */
public interface IHashUtility {
	/**
	 * Encrypt the provided plaintext
	 * 
	 * @param plainText
	 * @param salt
	 * @return plaintext hashed with provided salt
	 */
	String encrypt(String plainText, int salt);

}
