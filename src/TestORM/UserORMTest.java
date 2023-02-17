package TestORM;

import java.util.Scanner;

import Model.Group;
import Model.Role;
import Model.User;
import ORM.UserORM;
import Util.Util;

public class UserORMTest {

  public static User getUser(int ID) {
    UserORM groups = new UserORM();
    User group = null;
    for (var g : groups.listAll()) {
      if (g.getId() == ID) {
        group = g;
      }
    }
    return group;
  }

  public static void main(String[] args) {

    String onlyInt = "\tX - Integer only. Input again : ";
    Scanner sn = new Scanner(System.in);

    UserORM users = new UserORM();

    while (true) {
      Util.clsScr();
      System.out.println("""
          \t------------ Users ------------
          \t-------------------------------
          \t    1. List all users
          \t    2. Add new role
          \t    3. Delete users by ID
          \t    4. Update users by ID
          \t    5. End the program
          \t-------------------------------
          """);

      System.out.print("\tYour Option : ");
      int option = Util.getInt(onlyInt);

      if (option == 1) {
        System.out.print("\tAll Users...\n");
        // System.out.println("""
        //   \n\t ID | Group
        //   \t----+--------------""");
        for (var user : users.listAll()) {
          System.out.printf("\t %d, %s, %s, %s, %s, %s, %s, %s \n", 
            user.getId(), user.getUsername(), user.getRole().getRoleName(),
            user.getToken(), user.getGroup().getgroupName(), user.getRemoteAddr(),
            user.getForwardAddr(), user.getImage());
        }
      }

      // add
      else if (option == 2) {
        System.out.println("\n\tEnter info...\n");
        System.out.print("\tUsername : ");
        String username = sn.nextLine();
        System.out.print("\tPassword : ");
        String passwd = sn.nextLine();
        System.out.print("\tGroup : ");
        String groupName = sn.nextLine();
        System.out.print("\tRole : ");
        String roleName = sn.nextLine();
        System.out.print("\tToken : ");
        String token = sn.nextLine();
        System.out.print("\tRemode address : ");
        String remote = sn.nextLine();
        System.out.print("\tForward address : ");
        String address = sn.nextLine();
        System.out.print("\tImage : ");
        String img = sn.nextLine();

        Group group = GroupORMTest.geCountry(groupName);
        Role role = RoleORMTest.getRole(roleName);

        if (group == null || role == null) {
          System.out.println("\n\tX - Cannot add. Role or Group not found.\n");
        } else {
          User User = new User(0, username, passwd, role, token, group, remote, address, img);
          users.add(User);
          System.out.println("\n\t--> Add successfully");
        }

      }

      // delete
      else if (option == 3) {
        System.out.print("\tEnter user ID : ");
        int userID = Util.getInt(onlyInt);

        User user = UserORMTest.getUser(userID);
        if (user == null) {
          System.out.println("\n\tX - User not found.");
        } else {
          users.delete(userID);
          System.out.printf("\n\t--> User '%s' has been deleted successfully.", user.getUsername());
        }
      }

      else if (option == 4) {
        System.out.println("\n\tEnter info...\n");
        System.out.print("\tUser ID : ");
        int id = Util.getInt(onlyInt);
        System.out.print("\tUsername : ");
        String username = sn.nextLine();
        System.out.print("\tPassword : ");
        String passwd = sn.nextLine();
        System.out.print("\tGroup : ");
        String groupName = sn.nextLine();
        System.out.print("\tRole : ");
        String roleName = sn.nextLine();
        System.out.print("\tToken : ");
        String token = sn.nextLine();
        System.out.print("\tRemode address : ");
        String remote = sn.nextLine();
        System.out.print("\tForward address : ");
        String address = sn.nextLine();
        System.out.print("\tImage : ");
        String img = sn.nextLine();

        Group group = GroupORMTest.geCountry(groupName);
        Role role = RoleORMTest.getRole(roleName);

        if (group == null || role == null) {
          System.out.println("\n\tX - Cannot update. Role or Group not found.\n");
        } else {
          User User = new User(id, username, passwd, role, token, group, remote, address, img);
          users.update(User);
          System.out.println("\n\t--> update successfully");
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