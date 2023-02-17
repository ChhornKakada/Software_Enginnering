package TestORM;

import java.util.Scanner;

import Model.Role;
import ORM.RoleORM;
import Util.Util;

public class RoleORMTest {

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
    RoleORM roles = new RoleORM();
    Scanner sn = new Scanner(System.in);

    while (true) {
      Util.clsScr();
      System.out.println("""
          \t------------ Roles ------------
          \t-------------------------------
          \t    1. List all roles
          \t    2. Add new role
          \t    3. Delete role by ID
          \t    4. Update role by ID
          \t    5. End the program
          \t-------------------------------
          """);

      System.out.print("\tYour Option : ");
      int option = Util.getInt(onlyInt);

      // list all roles
      if (option == 1) {
        System.out.print("\tAll roles...\n");
        System.out.println("""
          \n\t ID | Role
          \t----+--------------""");
        for (var role : roles.listAll()) {
          System.out.printf("\t %2d | %s\n", role.getId(),role.getRoleName());
        }
      }

      // Add new roles
      else if (option == 2) {
        System.out.print("\tEnter new role : ");
        String roleName = sn.nextLine();  
        roles.add(new Role(0, roleName));
        System.out.printf("\tRole '%s' has been add successfully.\n", roleName);
      }

      // Delete role by ID
      else if (option == 3) {
        System.out.print("\tEnter role ID : ");
        int roleID = Util.getInt(onlyInt);

        Role role = RoleORMTest.getRole(roleID);
        if (role == null) {
          System.out.println("\n\tX - Role not found.");
        } else {
          roles.delete(roleID);
          System.out.println("\n\t--> Role has been deleted successfully.");
        }

      }


      // update
      else if (option == 4) {
        System.out.print("\tEnter role ID : ");
        int roleID = Util.getInt(onlyInt);
        System.out.print("\tEnter new role : ");
        String roleName = sn.nextLine();  

        Role role = RoleORMTest.getRole(roleID);
        if (role == null) {
          System.out.println("\n\tX - Role not found.");
        } else {
          roles.update(new Role(roleID, roleName));
          System.out.println("\n\t--> Role has been update successfully.");
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