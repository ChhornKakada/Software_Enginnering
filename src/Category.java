package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Util.MyFile;
import Util.Util;

public class Category {
  private int id;
  private String name, desc;
  private ArrayList <Book> bookList = new ArrayList<>();

  // ID
  public int getId() { return id; }
  public void setId(int id) { this.id = id; }

  // name
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  // description
  public String getDesc() { return desc; }
  public void setDesc(String desc) { this.desc = desc; }

  // list of books
  public ArrayList<Book> getBookList() { return bookList; }
  public void setBookList(ArrayList<Book> bookList) { this.bookList = bookList; }

  // Constructor
  public Category() { this.id = MyFile.countLine("Storage/CategoryList.txt") + 1; }

  // Count books in this category
  public int countBooks() {
    String filePath = "Storage/BookList.txt";
    BufferedReader reader;
    int numberOfBooks = 0;
    try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();

			while (line != null) {
        Book tmpBook = Book.lineToBook(line);
        if(tmpBook.getCategory().id == this.id) numberOfBooks++;
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    return numberOfBooks;
  }

  // Method to list all books
  public void listAllBooks() {
    String filePath = "Storage/BookList.txt";
    BufferedReader reader;
    try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
      Book.header();
			int i = 1;
      while (line != null) {
        Book tmpBook = Book.lineToBook(line);
        if(tmpBook.getCategory().id == this.id) {
          System.out.printf("\t  %02d   ", i);
          tmpBook.displayAsLine();
          System.out.println();
          i++;
        }
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }


  // Remove book by ISBN or title
  public boolean removeBook(String ISBNorTitle) {
    String filePath = "Storage/BookList.txt";
    Book tmpBook = Book.getBook(ISBNorTitle);
    if(tmpBook == null) return false;
    else {
      MyFile.deleteLine(filePath, tmpBook.toLine());
      return true;
    }
  }

  // line to Category
  public static Category lineToCategory(String line) {
    Category tmpCtg = new Category();
    String[] lines = line.split("~`~");
    tmpCtg.id = Integer.parseInt(lines[0]);
    tmpCtg.name = lines[1];
    tmpCtg.desc = lines[2];
    return tmpCtg;
  }

  // Category to line
  public String toLine() {
    return this.id+ "~`~" +this.name+ "~`~" +this.desc;
  }


  // Get category by ID
  public static Category getByID(int id) {
    Category tmpCtg = new Category();
    
    try {
      BufferedReader reader;
			reader = new BufferedReader(new FileReader("Storage/CategoryList.txt"));
			String line = reader.readLine();

			while (line != null) {
        tmpCtg = lineToCategory(line);
        if(tmpCtg.id == id) break;
				// read next line
				line = reader.readLine();
			}
			reader.close();
      return tmpCtg;
		} catch (IOException e) {
			e.printStackTrace();
		}
    return null;
  }

  // Count all number of the books in the category
  public static int countAllCategory() {
    return MyFile.countLine("Storage/CategoryList.txt");
  }

  // Return the ID of the category
  public static int getID(String categoryName) {
    try {
      List <String> textList = Files.readAllLines(Path.of("Storage/CategoryList.txt", new String[]{}));
      categoryName = categoryName.trim().toLowerCase();
      for(String txt : textList) {
        if(!txt.trim().equals("")) {
          String[] temp = txt.trim().split("~`~");
          temp[1] = temp[1].trim().toLowerCase();
          if(temp[1].equals(categoryName)) return Integer.parseInt(temp[0]);
        }
      }
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
    return -1;
  }

  // Data input
  public static Category dataInput() {
    Scanner input = new Scanner(System.in);
    Category ctg = new Category();
    String filePath = "Storage/CategoryList.txt";
    try {
      System.out.print("\tName : ");
      ctg.name = input.nextLine();
      System.out.print("\tDescription : ");
      ctg.desc = input.nextLine();

      String tmp = ctg.toLine();
      MyFile.appendLine(filePath, tmp);
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
    // input.close();
    return ctg;
  }

  // Add book to category
  public Book addBook() {
    Book tmpBook = Book.dataInput(this.id);
    return tmpBook;
  } 

  // Display the category as a line
  public void displayAsLine() {
    System.out.printf("%-10s     %-43s", Util.trimTo(this.name, 10), Util.trimTo(this.desc, 43));
  }

  public static void header() {
    System.out.println("""
      \t| No |   Category   |                 Description                 | 
      \t+----+--------------+---------------------------------------------+""");
  }

}
