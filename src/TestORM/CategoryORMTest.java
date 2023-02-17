package TestORM;

import java.util.Scanner;

import Model.Category;
import ORM.CategoryORM;
import Util.Util;

public class CategoryORMTest {

  public static Category getCategory(int ID) {
    CategoryORM roles = new CategoryORM();
    Category role = null;
    for (var r : roles.listAll()) {
      if (r.getId() == ID) {
        role = r;
      }
    }
    return role;
  }

  public static Category getCategory(String categoryName) {
    CategoryORM roles = new CategoryORM();
    Category role = null;
    categoryName = categoryName.toLowerCase();
    for (var c : roles.listAll()) {
      if (c.getCategoryName().toLowerCase().equals(categoryName)) {
        role = c;
      }
    }
    return role;
  }

  public static void main(String[] args) {

    String onlyInt = "\tX - Integer only. Input again : ";
    CategoryORM categoryORM = new CategoryORM();
    Scanner sn = new Scanner(System.in);

    while (true) {
      Util.clsScr();
      System.out.println("""
          \t------------ Categories ------------
          \t-------------------------------
          \t    1. List all Categories
          \t    2. Add new Category
          \t    3. Delete Category by ID
          \t    4. Update Category by ID
          \t    5. End the program
          \t-------------------------------
          """);

      System.out.print("\tYour Option : ");
      int option = Util.getInt(onlyInt);

      // list all roles
      if (option == 1) {
        System.out.print("\tAll Categories...\n");
        System.out.println("""
          \n\t ID | Category
          \t----+--------------""");
        for (var role : categoryORM.listAll()) {
          System.out.printf("\t %2d | %s\n", role.getId(),role.getCategoryName());
        }
      }

      // Add new roles
      else if (option == 2) {
        System.out.print("\tEnter new category : ");
        String roleName = sn.nextLine();  
        categoryORM.add(new Category(0, roleName));
        System.out.printf("\n\tCategoryORM '%s' has been add successfully.\n", roleName);
      }

      // Delete role by ID
      else if (option == 3) {
        System.out.print("\tEnter category ID : ");
        int roleID = Util.getInt(onlyInt);

        Category role = CategoryORMTest.getCategory(roleID);
        if (role == null) {
          System.out.println("\n\tX - category not found.");
        } else {
          categoryORM.delete(roleID);
          System.out.println("\n\t--> category has been deleted successfully.");
        }

      }


      // update
      else if (option == 4) {
        System.out.print("\tEnter category ID : ");
        int roleID = Util.getInt(onlyInt);
        System.out.print("\tEnter new category : ");
        String roleName = sn.nextLine();  

        Category role = CategoryORMTest.getCategory(roleID);
        if (role == null) {
          System.out.println("\n\tX - Role not found.");
        } else {
          categoryORM.update(new Category(roleID, roleName));
          System.out.println("\n\t--> category has been update successfully.");
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