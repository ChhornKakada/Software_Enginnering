import java.util.Scanner;

import models.Role;
import models.User;
import orms.UserORM;
import utils.MD5;

public class UserListing {

  // get user by ID
  public static User getUser(int Id) {
    UserORM users = new UserORM();
    User user = null;
    for (var u : users.listAll()) {
      if (u.getId() == Id) {
        user = u;
        break;
      }
    }
    return user;
  }

  // get user by username and passwd
  public static User getUser(String username, String passwd) {
    UserORM users = new UserORM();
    User user = null;
    passwd = MD5.getMd5(passwd);
    for (var u : users.listAll()) {
      // u need to convert passwd once again bcuz when it fetch from db to the object
      // is cross the constructor
      if (u.getUsername().equals(username) && u.getPass().equals(MD5.getMd5(passwd))) {
        user = u;
        break;
      }
    }
    return user;
  }

  // check whether the username has already exists or not
  public static boolean isUsernameExist(String username) {
    UserORM users = new UserORM();
    for (var user : users.listAll()) {
      if (user.getUsername().equals(username))
        return true;
    }
    return false;
  }

  public static void main(String[] args) {

    Scanner sn = new Scanner(System.in);
    UserORM users = new UserORM();

    while (true) {
      Util.clsScr();
      System.out.println("""
          \n\t---------- Users Option ----------
          \t---------------------------------
          \t\t1. List all users
          \t\t2. Add new user
          \t\t3. Delete user by id
          \t\t4. End the program
          \t---------------------------------
            """);

      System.out.print("\tYour option : ");
      int option = Util.getInt("\tX - Integer only. Input again : ");

      // List all users
      if (option == 1) {

        // Customer
        if (CE1.isMainMenu || (CE1.mainUser == null || CE1.mainUser.getRole().getRole().equals("Customer"))) {
          // your code here
          System.out.println("\n\tAll users...");
          System.out.println("""
              \n\t ID |    Username     |   Role   | Discount |     Avatar     |        Email
              \t----+-----------------+----------+----------+----------------+---------------------""");
          for (var user : users.listAll()) {
            Short discount = user.getDiscount();
            System.out.printf("\t %2d | %-15s | %-8s |   %3d%%   | %-14s | %-19s \n",
                user.getId(), Util.trimTo(user.getUsername(), 15),
                Util.trimTo(user.getRole().getRole(), 8),
                discount, Util.trimTo(user.getAvatar(), 14),
                Util.trimTo(user.getEmail(), 19));
          }
        }

        // Admin
        else if (CE1.isMainMenu || (CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Admin")) ) {
          System.out.println("\n\tX - Sorry, this option is only for Customer role.");
        }
      }

      // Add new users
      else if (option == 2) {

        // Admin
        if (CE1.isMainMenu || (CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Admin")) ) {
          // your code here
          System.out.print("\n\tEnter...\n");
          System.out.print("\tUsername    : ");
          String username = sn.nextLine();
          System.out.print("\tPassword    : ");
          String passwd = sn.nextLine();
          System.out.print("\tRole        : ");
          String role = sn.nextLine();
          System.out.print("\tEmail       : ");
          String email = sn.nextLine();
          System.out.print("\tAvatar      : ");
          String avatar = sn.nextLine();
          System.out.print("\tDiscount(%) : ");
          Integer discount;
          while (true) {
            discount = Util.getInt("\tX - Integer only. Input again : ");
            if (discount >= 0 && discount <= 100) {
              break;
            } else
              System.out.print("\tX - Only integer number between 0-100. Input again : ");
          }

          Role roleTmp = RoleListing.getRole(role);
          if (roleTmp == null) {
            System.out.printf("\n\tX - Add unsuccessfully. Since, this role '%s' does not exist.\n", role);
          } else {
            if (UserListing.isUsernameExist(username)) {
              System.out.println("\n\tX - Cannot add. This username has already exists.\n");
            } else {
              users.add(new User(0, username, passwd, email, roleTmp, discount.shortValue(), avatar));
              System.out.printf("\n\t--> User '%s' has been added successfully.\n", username);
            }
          }
        }

        // Customer
        else if (CE1.isMainMenu || (CE1.mainUser == null || CE1.mainUser.getRole().getRole().equals("Customer"))) {
          System.out.println("\n\tX - Sorry, this option is only for Admin role.");
        }
      }

      // Delete users by ID
      else if (option == 3) {

        // Admin
        if (CE1.isMainMenu || (CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Admin")) ) {
          // your code here
          System.out.print("\tEnter user ID : ");
          int userID = Util.getInt("\tX - Integer only. Input again : ");
          User user = UserListing.getUser(userID);
          if (user == null) {
            System.out.println("\n\tX - Deleted unsuccessfully. User not found.\n");
          } else {
            users.delete(userID);
            System.out.printf("\n\t--> User '%s' has been deleted successfully.\n", user.getUsername());
          }
        }

        // Customer
        else if (CE1.isMainMenu || (CE1.mainUser == null || CE1.mainUser.getRole().getRole().equals("Customer"))) {
          System.out.println("\n\tX - Sorry, this option is only for Admin role.");
        }
      }

      // End the program
      else if (option == 4) {
        System.out.println("\n\t--> Thanks for using this program.\n\n");
        break;
      }

      // Invalid option
      else {
        System.out.println("\tX - Invalid option.");
      }

      Util.pause("\n\t");
    }

  }
}
