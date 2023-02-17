import java.util.Scanner;

import models.Country;
import orms.CountryORM;

public class CountryListing {

  // private CountryORM countries = new CountryORM();

  // get country by ID
  public static Country getCountry(int ID) {
    CountryORM countries = new CountryORM();
    Country country = null;
    for (var c : countries.listAll()) {
      if (c.getId() == ID) {
        country = c;
      }
    }
    return country;
  }

  // get country by country name
  public static Country geCountry(String countryName) {
    CountryORM countries = new CountryORM();
    Country country = null;
    countryName = countryName.toLowerCase();
    for (var c : countries.listAll()) {
      if (c.getCountry().toLowerCase().equals(countryName)) {
        country = c;
      }
    }
    return country;
  }

  public static void main(String[] args) {
    Scanner sn = new Scanner(System.in);
    CountryORM countries = new CountryORM();

    while (true) {
      Util.clsScr();
      System.out.println("""
          \n\t----------------- Country Menu -----------------
          \t------------------------------------------------
          \t\t1. List all countries
          \t\t2. Add new country
          \t\t3. Delete country by ID
          \t\t4. End the program
          \t------------------------------------------------
            """);

      System.out.print("\tYour option : ");
      int option = Util.getInt("\tX - Integer only. Input again : ");

      // List all countries
      if (option == 1) {

        if (CE1.isMainMenu || (CE1.mainUser == null || CE1.mainUser.getRole().getRole().equals("Customer"))) {
          // your code here
          System.out.println("\tAll country...\n");
          System.out.println("""
              \t ID | Country
              \t----+-------------------""");
          for (var country : countries.listAll()) {
            System.out.printf("\t %2d | %s\n", country.getId(), country.getCountry());
          }
        }

        else if (CE1.isMainMenu || (CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Admin")) ) {
          // your code here
          System.out.println("\n\tX - Sorry, this option is only for Customer role.");
        }
      }

      // Add new country
      else if (option == 2) {

        if (CE1.isMainMenu || (CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Admin")) ) {
          // your code here
          System.out.print("\tEnter Country : ");
          String countryName = sn.nextLine();
          Country countryTmp = CountryListing.geCountry(countryName);
          if (countryTmp == null) {
            Country country = countries.add(new Country(0, countryName));
            System.out.println("\n\t-> Country \'" + country.getCountry() + "\' has been added successfully.");
          } else {
            System.out.println("\n\tX - Added unsuccessfully. This country is already exists.");
          }
        } 
        
        else if (CE1.isMainMenu || (CE1.mainUser == null || CE1.mainUser.getRole().getRole().equals("Customer"))) {
          // your code here
          System.out.println("\n\tX - Sorry, this option is only for Admin role.");
        }   
      }

      // Delete country by ID
      else if (option == 3) {

        if (  CE1.isMainMenu || (CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Admin")) ) {
          // your code here
          System.out.print("\tEnter Country ID : ");
          int CountryId = Util.getInt("\tX - Integer only. Input again : ");
          Country country = CountryListing.getCountry(CountryId);
          if (country == null) {
            System.out.println("\n\tX - Deleted unsuccessfully. Country not found.");
          } else {
            countries.delete(CountryId);
            System.out.println("\n\t-> Deleted Country '" + country.getCountry() + "' Successfully.");
          }
        } 
        
        else if (CE1.isMainMenu || (CE1.mainUser == null || CE1.mainUser.getRole().getRole().equals("Customer"))) {
          // your code here
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
