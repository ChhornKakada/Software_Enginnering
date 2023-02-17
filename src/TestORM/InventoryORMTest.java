package TestORM;

import java.util.Calendar;
import java.util.Scanner;

import Model.Book;
import Model.Inventory;
import ORM.InventoriesORM;
import Util.Util;

public class InventoryORMTest {


  public static Inventory getInventory(int ID) {
    InventoriesORM roles = new InventoriesORM();
    Inventory role = null;
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

    InventoriesORM inventories = new InventoriesORM();

    while (true) {
      Util.clsScr();
      System.out.println("""
          \t------------ Inventories ------------
          \t-------------------------------
          \t    1. List all Inventories
          \t    2. Add new Inventory
          \t    3. Delete Inventory by ID
          \t    4. Update Inventory by ID
          \t    5. End the program
          \t-------------------------------
          """);

      System.out.print("\tYour Option : ");
      int option = Util.getInt(onlyInt);

      if (option == 1) {
        System.out.print("\tAll Inventories...\n");
        for (var invaen : inventories.listAll()) {
          System.out.printf("\t %2d, %s, %d, %s, %s\n",
            invaen.getId(), invaen.getBook().getBookName(), invaen.getCopies(),
            invaen.getSrcurl(), invaen.getCreateAt());
        }
      }

      else if (option == 2) {
        System.out.print("\tBook ID : ");
        int bookID = Util.getInt(onlyInt);
        System.out.print("\tCopies : ");
        int copies = Util.getInt(onlyInt);
        System.out.print("\tSrcurl : ");
        String src = sn.nextLine();
        Calendar now = Calendar.getInstance();
        java.sql.Timestamp currentDate = new java.sql.Timestamp((now.getTime()).getTime());

        Book book = BookORMTest.getBook(bookID);

        if (book == null) {
          System.out.println("\n\tX - Inventory cannot add. Book not found.");
        } else {
          inventories.add(new Inventory(0, book, copies, src, currentDate));
          System.out.println("\n\t--> Add successfully");
        }

      }

      else if (option == 3) {
        System.out.print("\tEnter Inventory ID : ");
        int invenID = Util.getInt(onlyInt);

        Inventory inven = InventoryORMTest.getInventory(invenID);
        if (inven == null) {
          System.out.println("\n\tX - Inventory not found.");
        } else {
          inventories.delete(invenID);
          System.out.println("\n\t--> Deleted successfully");
        }
      }

      else if (option == 4) {
        System.out.print("\tInventory ID : ");
        int invenID = Util.getInt(onlyInt);
        System.out.print("\tBook ID : ");
        int bookID = Util.getInt(onlyInt);
        System.out.print("\tCopies : ");
        int copies = Util.getInt(onlyInt);
        System.out.print("\tSrcurl : ");
        String src = sn.nextLine();
        Calendar now = Calendar.getInstance();
        java.sql.Timestamp currentDate = new java.sql.Timestamp((now.getTime()).getTime());

        Book book = BookORMTest.getBook(bookID);

        if (book == null) {
          System.out.println("\n\tX - Inventory cannot Update. Book not found.");
        } else {
          inventories.update(new Inventory(invenID, book, copies, src, currentDate));
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