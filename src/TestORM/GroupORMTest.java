package TestORM;

import java.util.Scanner;

import Model.Group;
import ORM.GroupORM;
import Util.Util;

public class GroupORMTest {

  public static Group getGroup(int ID) {
    GroupORM groups = new GroupORM();
    Group group = null;
    for (var g : groups.listAll()) {
      if (g.getId() == ID) {
        group = g;
      }
    }
    return group;
  }

  public static Group geCountry(String groupName) {
    GroupORM groupORM = new GroupORM();
    Group group = null;
    groupName = groupName.toLowerCase();
    for (var c : groupORM.listAll()) {
      if (c.getgroupName().toLowerCase().equals(groupName)) {
        group = c;
      }
    }
    return group;
  }

  public static void main(String[] args) {

    String onlyInt = "\tX - Integer only. Input again : ";
    Scanner sn = new Scanner(System.in);

    GroupORM groups = new GroupORM();

    while (true) {
      Util.clsScr();
      System.out.println("""
          \t------------ Groups ------------
          \t-------------------------------
          \t    1. List all Groups
          \t    2. Add new Group
          \t    3. Delete Group by ID
          \t    4. Update Group by ID
          \t    5. End the program
          \t-------------------------------
          """);

      System.out.print("\tYour Option : ");
      int option = Util.getInt(onlyInt);

      if (option == 1) {
        System.out.print("\tAll roles...\n");
        System.out.println("""
          \n\t ID | Group
          \t----+--------------""");
        for (var group : groups.listAll()) {
          System.out.printf("\t %2d | %s\n", group.getId(),group.getgroupName());
        }
      }

      // add
      else if (option == 2) {
        System.out.print("\tEnter new Group : ");
        String groupName = sn.nextLine();  
        groups.add(new Group(0, groupName));
        System.out.printf("\n\t--> Group '%s' has been add successfully.\n", groupName);
      }

      // delete
      else if (option == 3) {
        System.out.print("\tEnter group ID : ");
        int groupID = Util.getInt(onlyInt);

        Group group = GroupORMTest.getGroup(groupID);
        if (group == null) {
          System.out.println("\n\tX - Group not found.");
        } else {
          groups.delete(groupID);
          System.out.printf("\n\t--> Group '%s' has been deleted successfully.", group.getgroupName());
        }
      }

      else if (option == 4) {
        System.out.print("\tEnter Group ID : ");
        int groupID = Util.getInt(onlyInt);
        System.out.print("\tEnter new Group : ");
        String newGroupName = sn.nextLine();  

        Group group = GroupORMTest.getGroup(groupID);
        if (group == null) {
          System.out.println("\n\tX - Group not found.");
        } else {
          groups.update(new Group(groupID, newGroupName));
          System.out.println("\n\t--> Group has been update successfully.");
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