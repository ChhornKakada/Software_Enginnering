package TestORM;

import java.util.Scanner;

import Model.Address;
import Model.Publishers;
import ORM.PublishersORM;
import Util.Util;

public class PublisherORMTest {

  public static Publishers getPublisher(int ID) {
    PublishersORM roles = new PublishersORM();
    Publishers role = null;
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

    PublishersORM pubs = new PublishersORM();

    while (true) {
      Util.clsScr();
      System.out.println("""
          \t------------ Publisher ------------
          \t-------------------------------
          \t    1. List all Publisher
          \t    2. Add new Publishe
          \t    3. Delete Publisher by ID
          \t    4. Update Publisher by ID
          \t    5. End the program
          \t-------------------------------
          """);

      System.out.print("\tYour Option : ");
      int option = Util.getInt(onlyInt);

      if (option == 1) {
        System.out.print("\tAll Publishers...\n");
        for (var p : pubs.listAll()) {
          System.out.printf("\t %d, %d, %s\n", p.getId(),p.getAddress().getId(), p.getPublisherName());
        }
      }

      else if (option == 2) {
        System.out.print("\tEnter new Publisher : ");
        String pubName = sn.nextLine();  
        System.out.print("\tEnter address ID : ");
        int addID = Util.getInt(onlyInt);

        Address add = AddressesORMTest.getAddress(addID);

        if (add == null) {
          System.out.println("\n\tX - Cannot add. Address not found.");
        } else {
          pubs.add(new Publishers(0, pubName, add));
          System.out.printf("\n\tPublisher '%s' has been add successfully.\n", pubName);
        }        
      }

      else if (option == 3) {
        System.out.print("\tEnter Publisher ID : ");
        int addressID = Util.getInt(onlyInt);

        Publishers address = PublisherORMTest.getPublisher(addressID);
        if (address == null) {
          System.out.println("\n\tX - Publisher not found.");
        } else {
          pubs.delete(addressID);
          System.out.print("\n\t--> Publisher has been deleted successfully.");
        }
      }

      else if (option == 4) {
        System.out.print("\tEnter Publisher ID : ");
        int pubID = Util.getInt(onlyInt);
        System.out.print("\tEnter new Publisher : ");
        String pubName = sn.nextLine();  
        System.out.print("\tEnter address ID : ");
        int addID = Util.getInt(onlyInt);

        Address add = AddressesORMTest.getAddress(addID);

        if (add == null) {
          System.out.println("\n\tX - Cannot add. Address not found.");
        } else {
          pubs.update(new Publishers(pubID, pubName, add));
          System.out.printf("\n\tPublisher '%s' has been updated successfully.\n", pubName);
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