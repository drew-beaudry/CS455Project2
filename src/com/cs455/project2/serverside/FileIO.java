package com.cs455.project2.serverside;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cs455.project2.serverside.api.IFileIO;

public class FileIO implements IFileIO {

	private static final String RESOURCES_USERS_TXT = "resources/users.txt";

  @Override
	public String[] readFromFile() {
		List<String> lines = new ArrayList<>(); 
		Scanner scanner;
		try {
		  scanner = new Scanner(new File(RESOURCES_USERS_TXT));
		  while(scanner.hasNextLine()) {
		    lines.add(scanner.nextLine());
		  }
		  scanner.close();
		}catch (FileNotFoundException e) {
		  e.printStackTrace();
		}
		return (String[]) lines.toArray();
	}

	@Override
	public void appendToFile(String line) {
	  try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(RESOURCES_USERS_TXT));
      writer.write(line + "\n");
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
	}

	@Override
	public void writeToFile(String[] lines) {
	   try {
	      BufferedWriter writer = new BufferedWriter(new FileWriter(RESOURCES_USERS_TXT));
	      for(String line: lines) {
	        writer.write(line + "\n");
	      }
	      writer.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	   //TODO This method should overwrite the file instead of appending to it
	}

}
