package TestORM;

import java.util.Calendar;
import java.util.Scanner;

import Model.Book;
import Model.Download;
import Model.User;
import ORM.DownloadORM;
import Util.Util;

public class DownloadORMTest {

  public static Download getDownload(int ID) {
    DownloadORM roles = new DownloadORM();
    Download role = null;
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

    DownloadORM downloadORM = new DownloadORM();

    while (true) {
      Util.clsScr();
      System.out.println("""
          \t------------ Downlaods ------------
          \t-------------------------------
          \t    1. List all Downlaods
          \t    2. Add new Downlaod
          \t    3. Delete Downlaods by ID
          \t    4. Update Downlaods by ID
          \t    5. End the program
          \t-------------------------------
          """);

      System.out.print("\tYour Option : ");
      int option = Util.getInt(onlyInt);

      if (option == 1) {
        System.out.print("\tAll Downloads...\n");
        for (var d : downloadORM.listAll()) {
          System.out.printf("\t %2d, %s, %s, %s\n",
              d.getId(), d.getUser().getUsername(), d.getBook().getTitle(), d.getDownloadAt());
        }
      }

      else if (option == 2) {
        System.out.print("\tUser ID : ");
        int userID = Util.getInt(onlyInt);
        System.out.print("\tBook ID : ");
        int bookID = Util.getInt(onlyInt);
        Calendar now = Calendar.getInstance();
        java.sql.Timestamp currentDate = new java.sql.Timestamp((now.getTime()).getTime());

        Book book = BookORMTest.getBook(bookID);
        User user = UserORMTest.getUser(userID);

        if (book == null || user == null) {
          System.out.println("\n\tX - Download cannot add. Book or user not found.");
        } else {
          downloadORM.add(new Download(0, user, book, currentDate));
          System.out.println("\n\t--> Added successfully");
        }
      }

      else if (option == 3) {
        System.out.print("\tDownload ID : ");
        int downloadID = Util.getInt(onlyInt);

        Download dw = DownloadORMTest.getDownload(downloadID);
        if (dw == null) {
          System.out.println("\n\tX - Download not found.");
        } else {
          downloadORM.delete(downloadID);
          System.out.println("\n\t--> Deleted successfully");
        }
      }

      else if (option == 4) {
        System.out.print("\tDownload ID : ");
        int downloadID = Util.getInt(onlyInt);
        System.out.print("\tUser ID : ");
        int userID = Util.getInt(onlyInt);
        System.out.print("\tBook ID : ");
        int bookID = Util.getInt(onlyInt);
        Calendar now = Calendar.getInstance();
        java.sql.Timestamp currentDate = new java.sql.Timestamp((now.getTime()).getTime());

        Book book = BookORMTest.getBook(bookID);
        User user = UserORMTest.getUser(userID);

        if (book == null || user == null) {
          System.out.println("\n\tX - Download cannot add. Book or user not found.");
        } else {
          downloadORM.update(new Download(downloadID, user, book, currentDate));
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