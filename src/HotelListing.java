import java.util.ArrayList;
import java.util.Scanner;

import models.City;
import models.Hotel;
import orms.HotelORM;

public class HotelListing {

  // get hotel by ID
  public static Hotel getHotel(int ID) {
    HotelORM hotels = new HotelORM();
    Hotel hotel = null;
    for (var h : hotels.listAll()) {
      if (h.getId() == ID) {
        hotel = h;
        break;
      }
    }
    return hotel;
  }

  // get hotel
  public static Hotel getHotel(String country, String city, String hotelName) {
    HotelORM hotels = new HotelORM();
    Hotel hotel = null;
    country = country.toLowerCase();
    city = city.toLowerCase();
    hotelName = hotelName.toLowerCase();
    for (var h : hotels.listAll()) {
      if (h.getHotel().toLowerCase().equals(hotelName) &&
          h.getCity().getCity().toLowerCase().equals(city) &&
          h.getCountry().getCountry().toLowerCase().equals(country)) {
        hotel = h;
        break;
      }
    }
    return hotel;
  }

  // get hotels list by Country and City name
  public static ArrayList<Hotel> getHotels(String cityName, String countryName) {
    cityName = cityName.toLowerCase();
    countryName = countryName.toLowerCase();
    HotelORM hotels = new HotelORM();
    ArrayList<Hotel> hotelsTmp = new ArrayList<>();
    for (var h : hotels.listAll()) {
      if (h.getCity().getCity().toLowerCase().equals(cityName) &&
          h.getCountry().getCountry().toLowerCase().equals(countryName)) {
        hotelsTmp.add(h);
      }
    }
    return hotelsTmp;
  }

  public static void main(String[] args) {
    Scanner sn = new Scanner(System.in);
    HotelORM hotels = new HotelORM();

    while (true) {
      Util.clsScr();

      System.out.println("""
          \n\t---------------- Hotels Menu -----------------
          \t----------------------------------------------
          \t    1. List all hotels in a city
          \t    2. Add new hotel
          \t    3. View detail information of a hotel
          \t    4. Delete hotel by id
          \t    5. End the program""");

      if (CE2.isCE2 && CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Customer"))
        System.out.println("\t    6. Comment on a hotel");
      System.out.println("\t---------------------------------------------");

      System.out.print("\tYour option : ");
      int option = Util.getInt("\tX - Integer only. Input again : ");

      // list all
      if (option == 1) {

        if (CE1.isMainMenu || (CE1.mainUser == null || CE1.mainUser.getRole().getRole().equals("Customer"))) {
          // your code here
          System.out.println("\n\tEnter...");
          System.out.print("\tCountry Name : ");
          String countryName = sn.nextLine();
          System.out.print("\tCity Name    : ");
          String cityName = sn.nextLine();

          System.out.println(
              """
                  \n\t ID |     Hotel Name     |     City     |    Country    | Stars |   Cost   | Info
                  \t----+--------------------+--------------+---------------+-------+----------+---------------------------""");
          for (var hotel : HotelListing.getHotels(cityName, countryName)) {
            System.out.printf("\t %2d | %-18s | %-12s | %-13s |   %d   | $ %s | %-25s\n",
                hotel.getId(), Util.trimTo(hotel.getHotel(), 18),
                Util.trimTo(hotel.getCity().getCity(), 12),
                Util.trimTo(hotel.getCountry().getCountry(), 13), hotel.getStars(),
                String.format("%,05.2f", hotel.getCost()), Util.trimTo(hotel.getInfo(), 25));
          }
        }

        else if (CE1.isMainMenu || (CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Admin"))) {
          System.out.println("\n\tX - Sorry, this option is only for Customer role.");
        }
      }

      // Add new hotel
      else if (option == 2) {

        if (CE1.isMainMenu || (CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Admin"))) {
          // your code here
          System.out.println("\n\tEnter...");
          System.out.print("\t    Country Name : ");
          String countryName = sn.nextLine();
          System.out.print("\t    City Name    : ");
          String cityName = sn.nextLine();
          System.out.print("\t    Hotel Name   : ");
          String hotelName = sn.nextLine();
          System.out.print("\t    Cost         : ");
          Double cost = Util.getPositiveDouble("\tX - Only positive number. Input again : ");
          System.out.print("\t    Stars        : ");
          Integer stars;
          while (true) {
            stars = Util.getInt("\tX - Only positive number. Input again : ");
            if (stars >= 0 && stars <= 5) {
              break;
            } else {
              System.out.print("\tX - Only positive number between 0-5. Input again : ");
            }
          }
          System.out.print("\t    Info         : ");
          String info = sn.nextLine();

          City city = CityListing.getCity(cityName, countryName);

          if (city == null) {
            System.out.println("\n\tX - Cannot add. City or Country not found.\n");
          } else {
            Hotel hotel = hotels.add(new Hotel(0, hotelName, city.getCountry(), city, stars.shortValue(), cost, info));
            System.out.printf("\n\t--> Hotel '%s' has been added successfully.\n", hotel.getHotel());
          }
        }

        else if (CE1.isMainMenu || (CE1.mainUser == null || CE1.mainUser.getRole().getRole().equals("Customer"))) {
          System.out.println("\n\tX - Sorry, this option is only for Admin role.");
        }

      }

      // View detail information of a hotel
      else if (option == 3) {

        if (CE1.isMainMenu || (CE1.mainUser == null || CE1.mainUser.getRole().getRole().equals("Customer"))) {
          // your code here
          System.out.print("\tEnter Hotel ID : ");
          int hotelId = Util.getInt("\tX - Integer only. Input again : ");
          Hotel hotel = HotelListing.getHotel(hotelId);
          if (hotel == null) {
            System.out.println("\n\tX - Hotel not found.");
          } else {
            System.out.printf("""
                \n\t--> Detail Information...\n
                \t    ID       :  %d
                \t    Name     :  %s
                \t    Country  :  %s
                \t    City     :  %s
                \t    Stars    :  %d
                \t    Cost     :  $%.2f
                \t    Info     :  %s
                """, hotel.getId(), hotel.getHotel(), hotel.getCountry().getCountry(),
                hotel.getCity().getCity(), hotel.getStars(), hotel.getCost(), hotel.getInfo());

            if (CE2.isCE2 && CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Customer")) {
              System.out.println();
              CE2.displayHotelComment(hotelId);
            }
          }
        } else if (CE1.isMainMenu || (CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Admin"))) {
          System.out.println("\n\tX - Sorry, this option is only for Customer role.");
        }
      }

      // Delete hotel by id
      else if (option == 4) {
        // Admin
        if (CE1.isMainMenu || (CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Admin"))) {
          // your code here
          System.out.print("\tEnter Hotel ID : ");
          int hotelId = Util.getInt("\tX - Integer only. Input again : ");
          Hotel hotel = HotelListing.getHotel(hotelId);

          if (hotel == null) {
            System.out.println("\n\tX - Deleted unsuccessfully. Hotel not found.");
          } else {
            hotels.delete(hotelId);
            System.out.println("\n\t-> Hotel '" + hotel.getHotel() + "' has been deteled successfully.");
          }
        }
        // Customers
        else if (CE1.isMainMenu || (CE1.mainUser == null || CE1.mainUser.getRole().getRole().equals("Customer"))) {
          System.out.println("\n\tX - Sorry, this option is only for Admin role.");
        }

      }

      // End the program
      else if (option == 5) {
        System.out.println("\n\tThanks for using this program.\n\n");
        break;
      }

      // add comment || Invaid option
      else if (option == 6) {
        if (CE2.isCE2 && CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Customer")) {

          System.out.print("\n\tEnter hotel ID : ");
          int hotelID = Util.getInt("\tX - Integer only. Input again : ");
          System.out.print("\tEnter comment  : ");
          String comment = sn.nextLine();

          Hotel hotel = HotelListing.getHotel(hotelID);
          if (hotel == null) {
            System.out.println("\n\tX - Hotel not found by this ID. Cannot add a comment.\n");
          } else {
            CE2.insertToHotelComments(CE1.mainUser.getId(), hotelID, comment);
            System.out.println("\n\t--> Comment has been added already.\n");
          }

        } else {
          System.out.println("\tX - Invalid option.");
        }
      }

      // Invalid option
      else
        System.out.println("\tX - Invalid option.");

      Util.pause("\n\t");
    }
  }
}
