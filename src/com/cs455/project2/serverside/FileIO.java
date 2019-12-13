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

  private static final String USER_FILE_LOCATION = "resources/users.txt";
  private File usersFile;

  public FileIO() {
    usersFile = new File(USER_FILE_LOCATION);
  }

  @Override
  public String[] readFromFile() {
    List<String> lines = new ArrayList<>();
    Scanner scanner;
    try {
      // Establish resources directory
      usersFile.getParentFile().mkdirs();
      //Create file if not already existing
      if (!usersFile.exists()) {
        usersFile.createNewFile();
      }
      scanner = new Scanner(new File(USER_FILE_LOCATION));
      while (scanner.hasNextLine()) {
        lines.add(scanner.nextLine());
      }
      scanner.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return lines.toArray(new String[lines.size()]);
  }

  @Override
  public void appendToFile(String line) {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE_LOCATION,true));
      writer.write(line + "\n");
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void writeToFile(String[] lines) {
    //clear contents of file
	  try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE_LOCATION,false));
      for (String line : lines) {
        writer.write(line + "\n");
      }
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }  
  }
}
