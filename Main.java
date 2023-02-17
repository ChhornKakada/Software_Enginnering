import java.util.Scanner;
import Util.Util;
import src.Library;
import src.Student;

public class Main {
  public static void main(String[] args) {
    Library library = new Library();
    Scanner input = new Scanner(System.in);

    while(true) {
      Util.clsScr();
      System.out.println("""
        \t+---------------------------------+ Program's Option +---------------------------------+
        \t+---------------------------------++++++++++++++++++++---------------------------------+
        \t\t    01. List all categories
        \t\t    02. List books by categories
        \t\t    03. List books by year
        \t\t    04. List available books
        \t\t    05. Add new books
        \t\t    06. Decrease book by isbn
        \t\t    07. Remove book by isbn
        \t\t    08. Add new copies of book
        \t\t    09. Mark book not available to borrow
        \t\t    10. Let student borrow the book (max 5 books, max 1 week)
        \t\t    11. List students that borrowed the books
        \t\t    12. Let student to return book
        \t\t    13. List all students
        \t\t    14. Display student detail by ID
        \t\t    15. Display the information of borrowing book by student ID
        \t\t    16. End the program
        \t+--------------------------------------------------------------------------------------+
          """);
  
      System.out.print("\tYour option : ");
      int option = Util.getInt("\tX - Input Integer only : ");
      Util.clsScr();
  
      if(option == 1) {
        // System.out.println("\tAll categories in the Library!");
        library.listAllCategory();
      } 

      // List all book in the category
      else if(option == 2) {
        System.out.println("\n\tList all books by category.");
        System.err.print("\tInput category name : ");
        String tmp = input.nextLine();
        System.out.println();
        library.listBookByCategory(tmp);
      } 

      // List all book in the library by public year.
      else if(option == 3) {
        System.out.println("\n\tList all books in the library by public year.");
        System.out.print("\tInput year : ");
        int year = Util.getInt("\tX - Input only Integer : ");
        System.out.println();
        library.listBooksByYear(year);
      } 
      
      // List all book that available in the library
      else if(option == 4) {
        System.err.println("\n\tAll available books in the library.\n");
        library.listAvailableBooks();
      } 

      // Add new book to the library
      else if(option == 5) {
        System.out.println("\n\tAdd new book in the library.\n");
        library.addNewBook();
      } 
      
      // 6. Decrease book by isbn
      else if(option == 6) {
        System.out.println("\n\tDecease the number of book in the library by ISBN.");
        System.out.print("\n\tEnter ISBN : ");
        String tmp = input.nextLine();
        System.out.print("\tInter decrease number : ");
        int number = Util.getInt("\tX - Input only Integer : ");
        // library.DecreaseNumberOfBook(tmp, number);
        Boolean isSuccess = library.isDecreaseNumberOfBookSuccess(tmp, number);
        if(isSuccess == null) {
          System.out.println("\n\tX - Book not found.");
        } else if(isSuccess) {
          System.out.println("\t--> Number of the books is decreased successfully!");
        } else {
          System.out.println("\n\tX - Decrease unsuccessfully, because the number of the books is smaller than your input number!");
        }
      } 
      
      // Remove book by ISBN
      else if(option == 7) {  
        System.out.println("\n\tRemove the book from library by ISBN.");
        System.out.print("\tEnter ISBN : ");
        String tmp = input.nextLine();
        Boolean isRemoveSuccess = library.removeBook(tmp);
        if(isRemoveSuccess) {
          System.out.println("\n\t--> Book was removed successfully!");
        } else System.out.println("\n\tX - Book not found by this ISBN.");
      } 
      
      // Add new copy of books
      else if(option == 8) {
        System.out.println("\n\tAdd new copy of the book by ISBN.");
        System.out.print("\tEnter ISBN : ");
        String tmp = input.nextLine();
        System.out.print("\tInter increase number : ");
        int number = Util.getInt("\tX - Input only Integer : ");
        Boolean isAddSuccess = library.addNewCopyOfBook(tmp, number);
        if(isAddSuccess == null) System.out.println("\n\tX - Book not found by this ISBN.");
        else if(isAddSuccess) System.out.println("\t--> add more copy already!");
      } 
      
      // Mark book not available to borrow
      else if(option == 9) {
        System.out.println("\n\tMark book not available to borrow.");
        System.out.print("\tEnter ISBN : ");
        String tmp = input.nextLine();
        if(library.MarkBookNotAvailableToBorrow(tmp) == false) {
          System.out.println("\n\tX - Book not found in the library.");
        } else System.out.println("\n\t--> Book was removed successfully.");
      } 
      
      // Let student borrow the book (max 5 books, max 1 week)
      else if(option == 10) {
        System.out.println("\n\tLet student borrow the book.\n");
        library.letStudentBorrowBook();
      } 
      
      //  11. List students that borrowed the books
      else if(option == 11) {
        System.out.println("\n\tAll students who borrowed books from library.\n");
        library.listStudentBorrowTheBook();
      } 
      
      // 12. Let student to return book
      else if(option == 12) {
        System.out.println("\n\tLet student to return books to the library.");
        System.out.print("\tEnter student ID : ");
        String tmp = input.nextLine();
        Student student = Student.getByID(tmp);
        if(student == null) {
          System.out.println("\n\tX - Student not found!");
        } else {
          if(library.isDoneToReturnBook(tmp)) {
            System.out.println("\n\t-> Return successfully!");
          } else System.out.println("\n\t-> This student have not been borrowing any books.");
        }
      } 

      // list all students
      else if(option == 13) {
        System.out.println("\n\tList all students.\n");
        Student.displayAllStudents();
      }
      
      // Display student detail by ID
      else if(option == 14) {
        System.out.println("\n\tDisplay information of a student by ID.");
        System.out.print("\tEnter student ID : ");
        String ID = input.nextLine();
        System.out.println();
        var student = Student.getByID(ID);
        if(student == null) {
          System.out.println("\tX - Student not found!");
        } else student.dataOutput();
      } 
  
      // Display student detail that borrowed the book
      else if(option == 15) {
        System.out.println("\n\tDisplay the information of borrowing book by student ID.");
        System.out.print("\tEnter student ID : ");
        String ID = input.nextLine();
        System.out.println();
        var stu = Student.getByID(ID);
        if(stu == null) {
          System.out.println("\tX - Student not found by this ID.");
        } else {
          var student = library.getStudentThatBorrowdTheBook(ID);
          if(student == null) {
            System.out.println("\t--> This Student have not been borrowing any book!");
          } else {
            library.displayInfoAboutBorrowingBook(ID);
          }
        }
      } 

      // End the program
      else if(option == 16) {
        System.out.println("\n\n\tThanks for using this program. *.*\n\n");
        input.close();
        break;
      }
      
      else {
        System.out.println("\n\tX - Invalid option.");
        System.out.println("\tX - There is no option " +option+ " in the option list!");
      }

      Util.pause("\n\n\t");
    }
    
  }
}
