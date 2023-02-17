package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Scanner;

import Util.MyFile;
import Util.Util;

public class Library {
  private static String separator = "~`~";
  private String bookPath = "Storage/BookList.txt";
  private String CategoryPath = "Storage/CategoryList.txt";
  private String BooksAvailablePath = "Storage/BooksAvailable.txt";
  private String stuBorrowBookPath = "Storage/StudentsBorrowBooks.txt";

  public void listAllCategory() {
    // String filePath = "Storage/CategoryList.txt";
    BufferedReader reader;
    try {
			reader = new BufferedReader(new FileReader(this.CategoryPath));
			String line = reader.readLine();
      int i = 1;
      System.out.println("\n\tAll categories in the library.\n");
      Category.header();
			while (line != null) {
        Category ctg = Category.lineToCategory(line);
        System.out.printf("\t  %02d    ", i);
        ctg.displayAsLine();
        System.out.print("\n");
				line = reader.readLine();
        i++;
			}
			reader.close();
      System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }

  public void listBookByCategory(String category) {
    int tmpCtgID = Category.getID(category);
    if(tmpCtgID == -1) {
      System.out.println("\tX - Category not found.\n");
    } else {
      Category ctg = Category.getByID(tmpCtgID);
      ctg.listAllBooks();
    }
  }

  // Method to list all books
  public void listAllBooks() {
    BufferedReader reader;
    try {
			reader = new BufferedReader(new FileReader(bookPath));
			String line = reader.readLine();

      Book.header();
			while (line != null) {
        Book tmpBook = Book.lineToBook(line);
        tmpBook.displayAsLine();
        System.out.println("\n");
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }

  // List books by year
  public void listBooksByYear(int year) {
    BufferedReader reader;
    try {
			reader = new BufferedReader(new FileReader(bookPath));
			String line = reader.readLine();

      Book.header();
			int i = 1;
      while (line != null) {
        Book tmpBook = Book.lineToBook(line);
        Calendar cal = Calendar.getInstance();
        cal.setTime(tmpBook.getPubDate());
        if(cal.get(Calendar.YEAR) == year) {
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

  // Method to list all books
  public void listAvailableBooks() {
    BufferedReader reader;
    try {
			reader = new BufferedReader(new FileReader(bookPath));
			String line = reader.readLine();
      Book.header();
      int i = 1;
			while (line != null) {
        Book tmpBook = Book.lineToBook(line);
        if(tmpBook.NumberOfAvailable() > 0) {
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

  // Add new book
  public Book addNewBook() {
    Book tmpBook = Book.dataInput(-1);
    return tmpBook;
  }

  // Add new copies of book
  public Boolean addNewCopyOfBook(String IBSN, int number) {
    return this.isDecreaseNumberOfBookSuccess(IBSN, -number);
  }

  // Decrease book by isbn
  public Boolean isDecreaseNumberOfBookSuccess(String IBSN, int number) {
    Book tmpBook = Book.getBook(IBSN);
    if(tmpBook == null) {
      // System.out.print("\tBook not found!");
      return null;
    }
    else {
      int oldNum = tmpBook.NumberOfAvailable();
      int newNum = oldNum - number;
      if(newNum >= 0) {
        String oldLine = tmpBook.getISBN() + "," +oldNum;
        String newLine = tmpBook.getISBN() + "," +newNum;
        MyFile.modifyFile(this.BooksAvailablePath, oldLine, newLine);
        return true;
      } else {
        // System.out.print("\tCannot decrease " +number+ " book(s), because book have only "+oldNum+ ".");
        return false;
      }
      
    }
    // return false;
  }

  // Remove book by ISBN
  public Boolean removeBook(String IBSN) {
    Book book = Book.getBook(IBSN);
    if(book == null) {
      return false;
    } else {
      String lineToRemove = book.toLine();
      MyFile.deleteLine("Storage/BookList.txt", lineToRemove);
      String lineToRemove1 = book.getISBN()+ "," +book.NumberOfAvailable();
      MyFile.deleteLine(BooksAvailablePath, lineToRemove1);
      return true;
    }
  }

  // get student who borrowed the book by ID
  public Student getStudentThatBorrowdTheBook(String studentID) {
    BufferedReader reader;
    try {
			reader = new BufferedReader(new FileReader(stuBorrowBookPath));
			String line = reader.readLine();
			while (line != null) {
        String[] tmp = line.split(separator);
        if(tmp[0].equals(studentID)) {
          Student stu = Student.getByID(tmp[0]);
          reader.close();
          return stu;
        }
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    return null;
  }

  // make book not availible for borrow
  public Boolean MarkBookNotAvailableToBorrow(String IBSN) {
    Book book = Book.getBook(IBSN);
    if(book == null) { 
      return false;
    } else {
      String oldLine = book.toLine();
      System.out.println(book.toLine());
      book.setCannotBorrow();
      String newLine = book.toLine();
      System.out.println(book.toLine());
      MyFile.modifyFile("Storage/BookList.txt", oldLine, newLine);
      return true;
    } 
  }

  // Let student borrow the book
  public void letStudentBorrowBook() {
    Scanner input = new Scanner(System.in);

    System.out.print("\tEnter student's ID: ");
    String stuID = input.nextLine();
    LinkedList <Book> bookTitles = new LinkedList<>();

    var stu = Student.getByID(stuID);
    // Check if the ID is exist
    if(stu == null) {
      System.out.println("\n\tX - Student not found!\n");
    } else {
      if(this.isStudentBorrowingBook(stuID)) {
        System.out.println("""
          \n\tSorry! You cannot borrow the books.
          \tSince, you are not yet return the previous books.
            """);
      } else {
        System.out.print("\tHow many books you want to borrow? : ");
        int numberOfBooks = Util.getInt("\tError - input only integer number : ");
        for(int i=0; i<numberOfBooks; i++) {
          System.out.printf("\tTitle #%d : ", i+1);
          String bookTitleTmp = input.nextLine();

          // System.out.println("wegsfg");
          Book book = Book.getBook(bookTitleTmp);
          if(book == null) {
            System.out.print("\tSorry! Book not found!\n");
          } else {
            if(book.isCanBorrow() == false) {
              System.out.print("\tSorry, This book is not availiable for borrow!\n");
            } else {
              if(book.NumberOfAvailable() == 0) {
                System.out.print("\tSorry, this book is out of stock!\n");
              } else {
                bookTitles.addLast(book);
              }
            }
          }
        }

        Calendar tmpCal = Calendar.getInstance();
        String borrowDate = Util.formatDate(tmpCal.getTime(), "dd.MM.yyyy HH:mm:ss");
        tmpCal.add(Calendar.DATE, 7);
        String returnDate = Util.formatDate(tmpCal.getTime(), "dd.MM.yyyy HH:mm:ss");

        System.out.println("\n\tYou have been borrowing " +bookTitles.size()+ " book(s).");
        System.out.println("\tStart borrowing Date  :  " +borrowDate);
        System.out.println("\tDate need to return   :  " +returnDate);

        String toStore = stuID + separator + borrowDate + separator + returnDate;
        for(var book : bookTitles) {
          String oldLine = book.toLine();
          this.isDecreaseNumberOfBookSuccess(book.getISBN(), 1);
          String newline = book.toLine();
          MyFile.modifyFile(this.bookPath, oldLine, newline);
          toStore += separator + book.getISBN();
        }
        MyFile.appendLine(this.stuBorrowBookPath, toStore);
        
      }
    }
  }

  // Check if the student is borrowing the book
  public boolean isStudentBorrowingBook(String studentID) {
    BufferedReader reader;
    try {
			reader = new BufferedReader(new FileReader(this.stuBorrowBookPath));
			String line = reader.readLine();

			while (line != null) {
        String[] tmp = line.split("~`~");
        if(tmp[0].equals(studentID)) {
          reader.close();
          return true;
        }
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    return false;
  }

  // List students who borrow the books
  public void listStudentBorrowTheBook() {
    BufferedReader reader;
    try {
			reader = new BufferedReader(new FileReader(stuBorrowBookPath));
			String line = reader.readLine();
      Student.header();

      int i = 1;
			while (line != null) {
        String[] tmp = line.split(separator);
        Student stu = Student.getByID(tmp[0]);
        System.out.printf("\t  %02d  ", i);
        stu.displayAsLine();
        System.out.println();
				line = reader.readLine();
        i++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }

  // Let student to return book
  public Boolean isDoneToReturnBook(String studentID) {
    BufferedReader reader;
    try {
			reader = new BufferedReader(new FileReader(stuBorrowBookPath));
			String line = reader.readLine();
      
			while (line != null) {
        // Student stu = Student.lineToStudent(line);
        String[] tmp = line.split("~`~");
        if(tmp[0].equals(studentID)) {
          for(int i=3; i<tmp.length; i++) {
            Book book = Book.getBook(tmp[i]);
            String oldLine = Book.getAvaBookAsLine(book.getISBN());
            String[] str = oldLine.split(",");
            int number = Integer.parseInt(str[1]) + 1;
            String newLine = str[0] + "," +number;
            MyFile.modifyFile(BooksAvailablePath, oldLine, newLine); 
          }
          reader.close();
          MyFile.deleteLine("Storage/StudentsBorrowBooks.txt", line);
          return true;
        }
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    return false;
  }

  // show the information about borrowing book by student ID
  public void displayInfoAboutBorrowingBook(String studentID) {
    BufferedReader reader;
    try {
			reader = new BufferedReader(new FileReader(this.stuBorrowBookPath));
			String line = reader.readLine();

			while (line != null) {
        String[] tmp = line.split("~`~");
        if(tmp[0].equals(studentID)) {
          Student stu = Student.getByID(studentID);
          stu.dataOutput();
          System.out.printf("""
              \n\t\tBorrowing Date  :  %s
              \t\t   Return Date  :  %s""", tmp[1], tmp[2]);
          for(int i=3; i<tmp.length; i++) {
            Book book = Book.getBook(tmp[i]);
            System.out.printf("\n\t\t       Title %d  :  %s",i-2, book.getTitle());
          }
        }
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
  }

}

