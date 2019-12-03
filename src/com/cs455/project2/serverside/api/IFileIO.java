package com.cs455.project2.serverside.api;

/**
 * Reads and writes from the password file
 * 
 * @author dbeaudry
 *
 */
public interface IFileIO {
	/** Return contents of file in String Array */
	String[] readFromFile();

	/**
	 * Add the line to the file
	 * 
	 * @param line
	 */
	void appendToFile(String line);

	/**
	 * Write the lines to the file overwriting any existing information in the file
	 * 
	 * @param lines
	 */
	void writeToFile(String[] lines);
}
