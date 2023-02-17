package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class MyFile {

  // modify the specific line in the line.
  public static void modifyFile(String filePath, String oldLine, String newLine) {
    try {
      //Instantiating the Scanner class to read the file
      Scanner sc = new Scanner(new File(filePath));
      //instantiating the StringBuffer class
      StringBuffer buffer = new StringBuffer();
      //Reading lines of the file and appending them to StringBuffer
      while (sc.hasNextLine()) {
        buffer.append(sc.nextLine()+System.lineSeparator());
      }
      String fileContents = buffer.toString();
      // System.out.println("Contents of the file: "+fileContents);
      //closing the Scanner object
      sc.close();
      //Replacing the old line with new line
      fileContents = fileContents.replaceAll(oldLine, newLine);
      //instantiating the FileWriter class
      FileWriter writer = new FileWriter(filePath);
      System.out.println("");
      // System.out.println("new data: "+fileContents);
      writer.append(fileContents);
      writer.flush();
    } catch(Exception e) {
      // System.out.println(e.getClass());
      System.out.println(e.getMessage());
    }
  }

  // Delete the specifix line in the file
  public static void deleteLine(String filePath, String lineToRemove) {
    try {
      // String InputFile = "testOverwriteline.txt";
      File inputFile = new File(filePath);
      File tempFile = new File("myTempFile.txt");
  
      BufferedReader reader = new BufferedReader(new FileReader(inputFile));
      BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
  
      // String lineToRemove = "Enjoy the free content";
      String currentLine;
  
      while((currentLine = reader.readLine()) != null) {
          // trim newline when comparing with lineToRemove
          String trimmedLine = currentLine.trim();
          if(trimmedLine.equals(lineToRemove)) continue;
          writer.write(currentLine + System.getProperty("line.separator"));
      }
      writer.close(); 
      reader.close(); 
      // boolean se = inputFile.delete();
      // boolean successful = tempFile.renameTo(inputFile);
      inputFile.delete();
      tempFile.renameTo(inputFile);
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }

  // Append line into file
  public static void appendLine(String filePath, String lineToAppend) {
    try {
      lineToAppend += "\n";
      Files.write(Path.of(filePath, new String[]{}), lineToAppend.getBytes(), StandardOpenOption.APPEND);
    } catch(Exception e) {
      System.err.println(e.getClass());
      // System.out.println(e.getMessage() + " (The system cannot find the path specified)");
    }
  }

  // Count number of lines in the file
  public static int countLine(String filePath) {
    try {
      List <String> textList = Files.readAllLines(Path.of(filePath, new String[]{}));
      int i = 0;
      for(String txt : textList) {
        if(!txt.trim().equals("")) i++;
      }
      return i;
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
    return -1;
  }

}

class exFile {
  public static void main(String[] args) {
    String filePath = "Storage/testOverwriteLine.txt";
    String newLine = "";
    String oldLine = "We don't force our readers to sign up with us or submit their details either.";
    // MyFile.appendLine(filePath, oldLine);
    MyFile.deleteLine("Storage/testOverwriteLine.txt", oldLine);
    // MyFile.modifyFile("Storag/testOverwriteLine.txt", "Hello Kakada", "Hello-Kakada-Chhorn");
    // MyFile.deleteLine("Storage/testOverwriteLine.txt", "Hello-Kakada-Chhorn");
    // MyFile.deleteLine("Storage/testOverwriteLine.txt", "");
  }
}
