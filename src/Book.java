package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

import Util.MyFile;
import Util.Util;

public class Book {
  private String title, desc, isbn;
  private Category category;
  private boolean canBorrow;
  private Date pubDate = new Date();
  private LinkedList <String> authors = new LinkedList<>();

  // Title 
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }

  // Description
  public String getDesc() { return desc; }
  public void setDesc(String desc) { this.desc = desc; }

  // Category
  public Category getCategory() { return category; }
  public void setCategory(Category category) { this.category = category; }

  // ISBN
  public String getISBN() { return isbn; }
  public void setISBN(String isbn) { this.isbn = isbn; }

  public static boolean isISBNValid(String ISBN) {
    if(ISBN.length() == 10 || ISBN.length() == 13) {
      for(int i=0; i<ISBN.length(); i++) {
        if(ISBN.charAt(i) < 48 || ISBN.charAt(i) > 57) return false;
      }
      return true;
    } else return false;
  }

  // Number that can borrow 
  public int NumberOfAvailable() { 
    String filePath = "Storage/BooksAvailable.txt";
    // Category tmpCtg = new Category();
    
    try {
      BufferedReader reader;
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
      String[] tmp = new String[2];
			while (line != null) {
        tmp = line.split(",");
        if(tmp[0].trim().equals(this.isbn)) break;
				// read next line
				line = reader.readLine();
			}
			reader.close();
      int result = Integer.parseInt(tmp[1]);
      return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
    return -1;
  }

  // public void NumberOfAvailable(int number) { this.numberOfCopy = numberOfCopy; }

  // Stutus of the book (can borrow or not)
  public boolean isCanBorrow() { return canBorrow; }
  public void setCanBorrow() { this.canBorrow = true; }
  public void setCannotBorrow() { this.canBorrow = false; }

  // Published Date
  public Date getPubDate() { return pubDate; }
  public void setPubDate(Date pubDate) { this.pubDate = pubDate; }

  // Authors of the book 
  public LinkedList<String> getAuthors() { return authors; }
  public void setAuthors(LinkedList<String> authors) { this.authors = authors; }

  public String authorToStr(String separater) {
    int i = 0;
    String result = "";
    for(var author : this.authors) {
      if(i == 0) result += author;
      else result += separater + author;
      i++;
    }
    return result;
  }

  // get line at DB(availible book)
  public static String getAvaBookAsLine(String ISBN) {
    BufferedReader reader;
    try {
			reader = new BufferedReader(new FileReader("Storage/BooksAvailable.txt"));
			String line = reader.readLine();

			while (line != null) {
        String[] tmp = line.split(",");
        if(tmp[0].equals(ISBN)) {
          reader.close();
          return line;
        }
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    return null;
  }
  

  // Line to book
  public static Book lineToBook(String line) {
    Book tmpBook = new Book();
    // ISBN(String),title,authors(1*2),categoryID(int),publicDate(String),Desc(String),NumberOfCopy(int),Status(true/false)
    String[] lines = line.split("~`~");
    tmpBook.isbn = lines[0];
    tmpBook.title = lines[1];
    String[] tmpAuthors = lines[2].split("%");
    for(String author : tmpAuthors) { tmpBook.authors.addLast(author); }
    int ctgID = Integer.parseInt(lines[3]);
    tmpBook.category = Category.getByID(ctgID);
    tmpBook.pubDate = Util.lineToDate(lines[4], "dd.MM.yyyy");
    tmpBook.desc = lines[5];
    // tmpBook.numberOfCopy = Integer.parseInt(lines[6]);
    if(lines[6].equals("false")) tmpBook.setCannotBorrow();
    else tmpBook.setCanBorrow();
    return tmpBook;
  }

  // Display as line
  public void displayAsLine() {
    // |     ISBN     |        Title       |      Authors       |    Category   |  Pub Date  |    Desctiption     | Ava. Number | can borrow |
    System.out.printf("%13s   %-18s   %-18s   %-13s   %-10s   %21s     %02d       %-7s",
                      this.isbn, Util.trimTo(this.title, 18), 
                      Util.trimTo(this.authorToStr(", "), 18), 
                      Util.trimTo(this.category.getName(), 13), 
                      Util.formatDate(this.pubDate, "dd.MM.yyyy"), Util.trimTo(this.desc, 21), 
                      this.NumberOfAvailable(), this.isCanBorrow());
  }

  public static void header() {
    System.out.println("""
        \t| No |     ISBN      |        Title       |      Authors       |    Category   |  Pub Date  |      Desctiption      | Number | can borrow |
        \t+----+---------------+--------------------+--------------------+---------------+------------+-----------------------+--------+------------+""");
  }

  // get Book by ISBN or Title
  public static Book getBook(String IsbnOrTitle) {
    String filePath = "Storage/BookList.txt";
    IsbnOrTitle = IsbnOrTitle.trim().toLowerCase();
    BufferedReader reader;
    Book tmpBook = new Book();
    try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();

			while (line != null) {
        tmpBook = Book.lineToBook(line);
        if(tmpBook.getISBN().trim().equals(IsbnOrTitle) 
          || tmpBook.getTitle().trim().toLowerCase().equals(IsbnOrTitle)) {
            reader.close();
            return tmpBook;
          }
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    return null;
  }

  // Convert book to line
  public String toLine() {
    // ISBN(String),title,authors(1*2),categoryID(int),publicDate(String),Desc(String),NumberOfCopy(int),Status(true/false)
    String result = this.isbn+ "~`~" +this.title+ "~`~" +this.authorToStr("%")+ "~`~" 
                    +this.category.getId()+ "~`~" +Util.formatDate(this.pubDate, "dd.MM.yyyy")
                    + "~`~" +this.desc+  "~`~" +this.canBorrow;
    return result;
  }

  // set data to book
  public static Book dataInput(int categoryID) {
    String BookPath = "Storage/BookList.txt";
    String BookAvaPath = "Storage/BooksAvailable.txt";
    Scanner input = new Scanner(System.in);
    Book book = new Book();
    try {
      System.out.println("\tWarming - Title and ISBN cannot be null.");
      System.out.println("\tEnter the info of the book...\n");
      while(true) {
        System.out.print("\tTitle : ");
        book.title = input.nextLine();
        if(book.title.equals("")) System.out.println("\twarming - Title of the book cannot be null.\n");
        else break;
      }
  
      while(true) {
        System.out.print("\tISBN : ");
        String tempISBN = input.nextLine();
        if(tempISBN.equals("")) {
          System.out.println("\twarn - ISBN is cannot be null.");
        } else {
          if(isISBNValid(tempISBN)) {
            book.isbn = tempISBN;
            break;
          } else System.out.println("\tError - Length of the ISBN is 10 or 13 digits with real number.");
        }
      }
  
      // for authors
      System.out.print("\tAuthors(a1,a2,...) : ");
      String temp = input.nextLine();
      String authors[] = temp.split(",");
      for(var au : authors) {
        book.authors.addLast(au.trim());
      }
  
      // Input Category

      if(categoryID == -1) {
        System.out.print("\tCategory : ");
        String ctgName = input.nextLine();
        int ctgIDTmp = Category.getID(ctgName);
        if(ctgIDTmp != -1) {
          Category ctgTmp = Category.getByID(ctgIDTmp);
          book.setCategory(ctgTmp);
        } else {
          Category ctgTmp = new Category();
          System.out.print("\tCategory Description : ");
          String tmpDesc = input.nextLine();
          ctgTmp.setName(ctgName);
          ctgTmp.setDesc(tmpDesc);
          book.setCategory(ctgTmp);
          String tmp = ctgTmp.toLine();
          MyFile.appendLine("Storage/CategoryList.txt", tmp);
        }
      } else {
        Category ctgTmp = Category.getByID(categoryID);
        book.setCategory(ctgTmp);
      }

      // Input public date
      book.pubDate = Util.inputDate("\tPublic Date(dd.MM.yyyy) : ", "dd.MM.yyyy");
  
      // Input Descrption
      System.out.print("\tDescrption : ");
      book.desc = input.nextLine();
  
      // Input number of book
      int numberOfAvailable = Util.getInt("\tNumber of copy : ", 
                            "X - Integer only. Input again : ");
  
      // Status (can borrow or not)
      System.out.print("\tCan borrow(1:yes, 2:no) : ");
      int tmp = Util.getOnly1or2("\tX - Only number 1 or 2. Input again : ");
      if(tmp == 1) book.canBorrow = true;
      else book.canBorrow = false;

      String tmpBook = book.toLine();
      String tmpNumberOfBooks = book.isbn+ "," +numberOfAvailable;
      MyFile.appendLine(BookPath, tmpBook);
      MyFile.appendLine(BookAvaPath, tmpNumberOfBooks);

      System.out.println("\n\t--> Book is added successfully!");

    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
    // input.close();
    return book;
  }

  // Date
  public void dataOutput() {
    String status;
    if(this.canBorrow) status = "can borrow";
    else status = "cannot borrow";
    System.out.printf("""
        \tTitle       : %s
        \tISBN        : %s
        \tAuthor(s)   : %s
        \tCategory    : %s
        \tPublic Date : %s
        \tDescrption  : %s
        \tAvailable   : %d title(s)
        \tStatus      : %s """, 
        this.title, this.isbn, this.authorToStr(", "), this.category.getName(),
        Util.formatDate(this.pubDate, "dd.MM.yyyy"), this.desc, 
        this.NumberOfAvailable(), status);
  }

}
