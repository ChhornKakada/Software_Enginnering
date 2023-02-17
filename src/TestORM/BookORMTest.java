package TestORM;

import java.util.Scanner;

import Model.Book;
import Model.Group;
import Model.Publishers;
import Model.User;
import ORM.BookORM;
import Util.Util;

public class BookORMTest {

  public static Book getBook(int ID) {
    BookORM roles = new BookORM();
    Book role = null;
    for (var r : roles.listAll()) {
      if (r.getId() == ID) {
        role = r;
      }
    }
    return role;
  }

  public static void main(String[] args) {

    String onlyInt = "\tX - Integer only. Input again : ";
    Scanner sn = new Scanner(System.in);

    BookORM books = new BookORM();

    while (true) {
      Util.clsScr();
      System.out.println("""
          \t------------ Books ------------
          \t-------------------------------
          \t    1. List all Books
          \t    2. Add new Book
          \t    3. Delete Books by ID
          \t    4. Update Books by ID
          \t    5. End the program
          \t-------------------------------
          """);

      System.out.print("\tYour Option : ");
      int option = Util.getInt(onlyInt);

      if (option == 1) {
        System.out.print("\tAll Books...\n");
        for (var book : books.listAll()) {
          System.out.printf("\t %2d, %s, %s, %s, %s, %s\n",
              book.getId(), book.getBookName(), book.getPath(), book.getUser().getUsername(),
              book.getGroup().getgroupName(), book.getPublisher().getPublisherName());
        }
      }

      else if (option == 2) {
        System.out.print("\tTitle : ");
        String title = sn.nextLine();
        System.out.print("\tpath : ");
        String path = sn.nextLine();
        System.out.print("\tUser ID : ");
        int userID = Util.getInt(onlyInt);
        System.out.print("\tGroup : ");
        int groupID = Util.getInt(onlyInt);
        System.out.print("\tPublisher : ");
        int pubID = Util.getInt(onlyInt);

        User user = UserORMTest.getUser(userID);
        Group group = GroupORMTest.getGroup(groupID);
        Publishers pub = PublisherORMTest.getPublisher(pubID);

        if (user == null || group == null || pub == null) {
          System.out.println("\n\tX - User, group or category not found.");
        } else {
          books.add(new Book(0, title, path, user, group, pub));
          System.out.println("\n\t--> Add successfully");
        }
      }

      else if (option == 3) {
        System.out.print("\tEnter Book ID : ");
        int bookID = Util.getInt(onlyInt);

        Book book = BookORMTest.getBook(bookID);
        if (book == null) {
          System.out.println("\n\tX - Book not found.");
        } else {
          books.delete(bookID);
          System.out.println("\n\t--> Deleted successfully");
        }
      }

      else if (option == 4) {
        System.out.print("\tBook ID : ");
        int id = Util.getInt(onlyInt);
        System.out.print("\tTitle : ");
        String title = sn.nextLine();
        System.out.print("\tpath : ");
        String path = sn.nextLine();
        System.out.print("\tUser ID : ");
        int userID = Util.getInt(onlyInt);
        System.out.print("\tGroup : ");
        int groupID = Util.getInt(onlyInt);
        System.out.print("\tPublisher : ");
        int pubID = Util.getInt(onlyInt);

        User user = UserORMTest.getUser(userID);
        Group group = GroupORMTest.getGroup(groupID);
        Publishers pub = PublisherORMTest.getPublisher(pubID);

        if (user == null || group == null || pub == null) {
          System.out.println("\n\tX - User, group or category not found.");
        } else {
          books.update(new Book(id, title, path, user, group, pub));
          System.out.println("\n\t--> Updated successfully");
        }
      }

      else if (option == 5) {
        System.out.println("\n\t--> Thanks for using this program.\n\n");
        break;
      }

      else {
        System.out.println("\tX - Invalid option.");
      }
      Util.pause("\n\t");
    }
  }
}