import java.util.Scanner;

import models.City;
import models.Country;
import orms.CityORM;

public class CityListing {

  // get City by ID
  public static City getCity(int ID) {
    CityORM cities = new CityORM();
    City city = null;
    for (var c : cities.listAll()) {
      if (c.getId() == ID) {
        city = c;
        break;
      }
    }
    return city;
  }

  // get City
  public static City getCity(String cityName, String countryName) {
    CityORM cities = new CityORM();
    City city = null;
    Country country = CountryListing.geCountry(countryName);
    if (country != null) {
      String uCity = cityName.toLowerCase() + "_" + country.getId();
      for (City c : cities.listAll()) {
        if (c.getUcity().toLowerCase().equals(uCity)) {
          city = c;
          break;
        }
      }
    }
    return city;
  }

  public static void main(String[] args) {
    Scanner sn = new Scanner(System.in);
    CityORM cities = new CityORM();

    while (true) {
      Util.clsScr();
      System.out.println("""
          \n\t--------------- City Option ---------------
          \t-------------------------------------------
          \t\t1. List all cities
          \t\t2. Add new city
          \t\t3. Delete city by id
          \t\t4. End the program
          \t-------------------------------------------
            """);

      System.out.print("\tYour option : ");
      int option = Util.getInt("\tX - Integer only. Input again : ");

      // Display all city in a country
      if (option == 1) {

        if (CE1.isMainMenu || (CE1.mainUser == null || CE1.mainUser.getRole().getRole().equals("Customer"))) {
          // your code here
          System.out.print("\tEnter country : ");
          String countryName = sn.nextLine();
          Country country = CountryListing.geCountry(countryName);
          if (country == null) {
            System.out.println("\n\t--> X - Country not found.");
          } else {
            String query = "SELECT * FROM `cities` WHERE `countryid` = " + country.getId();
            System.out.printf("""
                \n\tAll cities in %s country...\n
                \t ID |        City        | ucity
                \t----+--------------------+----------------\n""", country.getCountry());

            for (City city : cities.rawQueryList(query)) {
              System.out.printf("\t %2d | %-18s | %s\n", city.getId(),
                  Util.trimTo(city.getCity(), 18), city.getUcity());
            }
          }
        } else if (CE1.isMainMenu || (CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Admin"))) {
          System.out.println("\n\tX - Sorry, this option is only for Customer role.");
        }
      }

      // Add new city
      else if (option == 2) {

        // Admin
        if (CE1.isMainMenu || (CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Admin"))) {
          // your code here
          System.out.println("\n\tEnter...");
          System.out.print("\tCountry Name : ");
          String countryName = sn.nextLine();
          System.out.print("\tCity Name    : ");
          String cityName = sn.nextLine();
          Country country = CountryListing.geCountry(countryName);

          if (country == null) {
            System.out.printf("\n\tX - Added unsucessfully. '%s' Country not found.\n", countryName);
          } else {
            City cityTmp = CityListing.getCity(cityName, countryName);

            if (cityTmp == null) {
              City city = cities.add(new City(0, cityName, country));
              System.out.printf("\n\t--> '%s' city has been added to %s country.\n", city.getCity(),
                  country.getCountry());
            } else {
              System.out.printf("""
                  \n\tX - Cannot add.
                  \t    '%s' city has already exists in %s country.
                  """, cityName, countryName);
            }
          }
        }

        // Customer
        else if (CE1.isMainMenu || (CE1.mainUser == null || CE1.mainUser.getRole().getRole().equals("Customer"))) {
          System.out.println("\n\tX - Sorry, this option is only for Admin role.");
        }
      }

      // Delete city by ID
      else if (option == 3) {

        // Admin
        if (CE1.isMainMenu || (CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Admin"))) {
          // your code here
          System.out.print("\tEnter City ID : ");
          int cityID = Util.getInt("\tX - Integer only. Input again : ");
          City city = CityListing.getCity(cityID);
          if (city == null) {
            System.out.println("\n\tX - Deleted unsuccessfully. City not found.");
          } else {
            cities.delete(cityID);
            System.out.println("\n\t-> Deleted '" + city.getCity() + "' city in "
                + city.getCountry().getCountry() + " country Successfully.");
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
