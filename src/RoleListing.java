import java.util.Scanner;

import models.Role;
import orms.RoleORM;

public class RoleListing {

  // get role by ID
  public static Role getRole(int ID) {
    RoleORM roles = new RoleORM();
    Role role = null;
    for (var r : roles.listAll()) {
      if (r.getId() == ID) {
        role = r;
        break;
      }
    }
    return role;
  }

  // get role by roleName
  public static Role getRole(String roleName) {
    RoleORM roles = new RoleORM();
    Role role = null;
    roleName = roleName.toLowerCase();
    for (var r : roles.listAll()) {
      if (r.getRole().toLowerCase().equals(roleName)) {
        role = r;
        break;
      }
    }
    return role;
  }

  public static void main(String[] args) {
    Scanner sn = new Scanner(System.in);
    RoleORM roles = new RoleORM();

    while (true) {
      Util.clsScr();
      System.out.println("""
          \n\t--------- Roles Option ----------
          \t---------------------------------
          \t\t1. List all users
          \t\t2. Add new user
          \t\t3. Update user by id
          \t\t4. End the program
          \t---------------------------------
            """);

      System.out.print("\tYour option : ");
      int option = Util.getInt("\tX - Integer only. Input again : ");

      // Option 1 : List all users
      if (option == 1) {

        // Customer
        if (CE1.isMainMenu || (CE1.mainUser == null || CE1.mainUser.getRole().getRole().equals("Customer"))) {
          // your code here
          System.out.println("\n\tAll roles...");
          System.out.print("""
              \n\t ID | Role
              \t----+--------------\n""");
          for (var role : roles.listAll()) {
            System.out.printf("\t %2d | %s\n", role.getId(), role.getRole());
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
          System.out.print("\tEnter new role : ");
          String newRole = sn.nextLine();
          Role role = RoleListing.getRole(newRole);
          if (role == null) {
            roles.add(new Role(0, newRole));
            System.out.printf("\n\t--> Role '%s' has been added successfully.\n", newRole);
          } else {
            System.out.println("\n\tX - Cannot add. Since, this role has already exists.\n");
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
          System.out.print("\tEnter Role ID  : ");
          int roleID = Util.getInt("\tX - Input only. Input again : ");
          System.out.print("\tEnter new role : ");
          String newRole = sn.nextLine();
          Role role = RoleListing.getRole(roleID);
          if (role == null) {
            // cannot delete
            System.out.println("\n\tX - Update unsuccessfully. Since, this role not found.\n");
          } else {
            roles.update(new Role(roleID, newRole));
            System.out.printf("\n\t--> Updated role '%s' to '%s' successfully.\n", role.getRole(), newRole);
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
