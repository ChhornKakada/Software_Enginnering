package TestORM;

import java.util.Scanner;

import Model.Book;
import Model.BookCategory;
import Model.Category;
import Model.Role;
import ORM.BookCategoryORM;
import ORM.RoleORM;
import Util.Util;

public class BookCategoryORMTest {

  public static Role getRole(int ID) {
    RoleORM roles = new RoleORM();
    Role role = null;
    for (var r : roles.listAll()) {
      if (r.getId() == ID) {
        role = r;
      }
    }
    return role;
  }

  public static Role getRole(String roleName) {
    RoleORM roleORM = new RoleORM();
    Role role = null;
    roleName = roleName.toLowerCase();
    for (var c : roleORM.listAll()) {
      if (c.getRoleName().toLowerCase().equals(roleName)) {
        role = c;
      }
    }
    return role;
  }

  public static void main(String[] args) {

    String onlyInt = "\tX - Integer only. Input again : ";
    BookCategoryORM bookCategoryORM = new BookCategoryORM();
    Scanner sn = new Scanner(System.in);

    while (true) {
      Util.clsScr();
      System.out.println("""
          \t------------ Roles ------------
          \t-------------------------------
          \t    1. List all roles
          \t    2. Add new role
          \t    3. End the program
          \t-------------------------------
          """);

      System.out.print("\tYour Option : ");
      int option = Util.getInt(onlyInt);

      // list all roles
      if (option == 1) {
        System.out.print("\tAll book categories...\n");
        for (var bc : bookCategoryORM.listAll()) {
          System.out.printf("\t %2s | %s\n", 
            bc.getBook().getBookName(), bc.getCategory().getCategoryName());
        }
      }

      // Add new roles
      else if (option == 2) {
        System.out.print("\tEnter book ID : ");
        int bookID = Util.getInt(onlyInt);
        System.out.print("\tEnter Category ID : ");
        int cagID = Util.getInt(onlyInt);

        Book book = BookORMTest.getBook(bookID);
        Category ctg = CategoryORMTest.getCategory(cagID);

        if (book == null || ctg == null) {
          System.out.println("\tX - Book or category not found.");
        } else {
          bookCategoryORM.add(new BookCategory(null, null));
          System.out.println("\tAdded successfully.\n");
        }
      }


      else if (option == 3) {
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