package TestORM;

import java.util.Scanner;

import Model.Address;
import ORM.AddressORM;
import Util.Util;

public class AddressesORMTest {

  public static Address getAddress(int ID) {
    AddressORM groups = new AddressORM();
    Address group = null;
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

    AddressORM addressORM = new AddressORM();

    while (true) {
      Util.clsScr();
      System.out.println("""
          \t------------ Addresses ------------
          \t-------------------------------
          \t    1. List all Addresses
          \t    2. Add new Addresses
          \t    3. Delete Addresses by ID
          \t    4. Update Addresses by ID
          \t    5. End the program
          \t-------------------------------
          """);

      System.out.print("\tYour Option : ");
      int option = Util.getInt(onlyInt);

      if (option == 1) {
        System.out.print("\tAll Addresses...\n");
        for (var add : addressORM.listAll()) {
          System.out.printf("\t %d, %s, %s, %s, %s, %s, %s, %s, %s, %s, %d \n", 
            add.getId(), add.getHouseno(), add.getStreetno(), add.getStreetname(),
            add.getVillagename(), add.getDistrictname(), add.getCommunnename(), add.getProvincename(),
            add.getCityname(), add.getCountryname(), add.isIscurrent());
        }
      }

      // add
      else if (option == 2) {
        System.out.print("\tHouse no: ");
        String houseNo = sn.nextLine();

        System.out.print("\tStreet no: ");
        String streetNo = sn.nextLine();

        System.out.print("\tStreet name: ");
        String street = sn.nextLine();

        System.out.print("\tDistrict name: ");
        String district = sn.nextLine();

        System.out.print("\tCommune name: ");
        String commune = sn.nextLine();

        System.out.print("\tProvine name: ");
        String provine = sn.nextLine();

        System.out.print("\tCity name: ");
        String city = sn.nextLine();

        System.out.print("\tCountry name: ");
        String country = sn.nextLine();

        System.out.print("\tIs current ( 0/1 ): ");
        Integer isCurrent = sn.nextInt();

        Address Address = new Address(0, houseNo, streetNo, streetNo, street, district, commune, provine, city, country, isCurrent);
        addressORM.add(Address);
        System.out.println("\n\t--> Address added successfully!");
      }

      else if (option == 3) {
        System.out.print("\tEnter address ID : ");
        int addressID = Util.getInt(onlyInt);

        Address address = AddressesORMTest.getAddress(addressID);
        if (address == null) {
          System.out.println("\n\tX - Address not found.");
        } else {
          addressORM.delete(addressID);
          System.out.print("\n\t--> Address has been deleted successfully.");
        }
      }

      else if (option == 4) {
        System.out.print("\tAddress ID : ");
        int id = Util.getInt(onlyInt);
        System.out.print("\tHouse no: ");
        String houseNo = sn.nextLine();

        System.out.print("\tStreet no: ");
        String streetNo = sn.nextLine();

        System.out.print("\tStreet name: ");
        String street = sn.nextLine();

        System.out.print("\tDistrict name: ");
        String district = sn.nextLine();

        System.out.print("\tDommune name: ");
        String commune = sn.nextLine();

        System.out.print("\tProvine name: ");
        String provine = sn.nextLine();

        System.out.print("\tCity name: ");
        String city = sn.nextLine();

        System.out.print("\tCountry name: ");
        String country = sn.nextLine();

        System.out.print("\tIs current ( 0/1 ): ");
        Integer isCurrent = sn.nextInt();

        Address Address = new Address(id, houseNo, streetNo, streetNo, street, district, commune, provine, city, country, isCurrent);
        addressORM.update(Address);
        System.out.println("\n\t--> Address Updated successfully!");
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