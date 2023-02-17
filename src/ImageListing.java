import java.util.ArrayList;
import java.util.Scanner;

import models.Hotel;
import models.Image;
import orms.ImageORM;

public class ImageListing {

  // get image by ID
  public static Image getImage(int ID) {
    ImageORM images = new ImageORM();
    Image image = null;
    for (var img : images.listAll()) {
      if (img.getId() == ID) {
        image = img;
      }
    }
    return image;
  }

  // get image
  public static ArrayList<Image> getImages(String country, String city, String hotelName) {
    ImageORM imgs = new ImageORM();
    Hotel hotel = HotelListing.getHotel(country, city, hotelName);
    ArrayList<Image> images = new ArrayList<>();
    if (hotel != null) {
      for (var i : imgs.listAll()) {
        if (i.getHotel().getId() == hotel.getId()) {
          images.add(i);
        }
      }
    }
    return images;
  }

  public static void main(String[] args) {
    Scanner sn = new Scanner(System.in);
    ImageORM imgs = new ImageORM();

    while (true) {
      Util.clsScr();
      System.out.println("""
          \n\t------------- Images Option -------------
          \t-----------------------------------------
          \t\t1. List all images
          \t\t2. Add new image
          \t\t3. Delete image by ID
          \t\t4. End the program
          \t-----------------------------------------
            """);

      System.out.print("\tYour option : ");
      int option = Util.getInt("\tX - Integer only. Input again : ");

      // List all images of a hotel
      if (option == 1) {

        // Customer
        if (CE1.isMainMenu || (CE1.mainUser == null || CE1.mainUser.getRole().getRole().equals("Customer"))) {
          // your code here
          System.out.println("\n\tEnter...");
          System.out.print("\tCountry    : ");
          String countryName = sn.nextLine();
          System.out.print("\tCity       : ");
          String cityName = sn.nextLine();
          System.out.print("\tHotel      : ");
          String hotelName = sn.nextLine();

          Hotel hotel = HotelListing.getHotel(countryName, cityName, hotelName);

          if (hotel == null) {
            System.out.printf("\n\tX - Hotel '%s' not found in this location.\n", hotelName);
          } else {
            System.out.printf("""
                \n\tAll images of '%s' hotel...
                \n\t ID | Image path
                \t----+--------------------------------\n""", hotel.getHotel());
            for (var img : imgs.listAll()) {
              if (img.getHotel().getId() == hotel.getId()) {
                System.out.printf("\t %2d | %-27s\n", img.getId(),
                    Util.trimTo(img.getImagepath(), 30));
              }
            }
          }
        }

        // Admin
        else if (CE1.isMainMenu || (CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Admin")) ) {
          System.out.println("\n\tX - Sorry, this option is only for Customer role.");
        }
      }

      // option 2 : Add new image
      else if (option == 2) {

        // Admin
        if (CE1.isMainMenu || (CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Admin")) ) {
          // your code here
          System.out.println("\n\tEnter...");
          System.out.print("\t    Country    : ");
          String countryName = sn.nextLine();
          System.out.print("\t    City       : ");
          String cityName = sn.nextLine();
          System.out.print("\t    Hotel      : ");
          String hotelName = sn.nextLine();
          System.out.print("\t    Image path : ");
          String imgPath = sn.nextLine();

          Hotel hotel = HotelListing.getHotel(countryName, cityName, hotelName);
          if (hotel == null) {
            System.out.println("\n\tX - Cannot add image. Hotel not found.\n");
          } else {
            imgs.add(new Image(0, hotel, imgPath));
            System.out.printf("\n\t--> Image has been added to '%s' hotel successfully.\n", hotel.getHotel());
          }
        }

        // Customer
        else if (CE1.isMainMenu || (CE1.mainUser == null || CE1.mainUser.getRole().getRole().equals("Customer"))) {
          System.out.println("\n\tX - Sorry, this option is only for Admin role.");
        }
      }

      // Delete image by ID
      else if (option == 3) {

        // Admin
        if (CE1.isMainMenu || (CE1.mainUser != null && CE1.mainUser.getRole().getRole().equals("Admin")) ) {
          // your code here
          System.out.print("\tEnter Image ID : ");
          int imgID = Util.getInt("\tX - Integer only. Input again : ");
          Image img = ImageListing.getImage(imgID);
          if (img == null) {
            System.out.println("\n\tX - Deleted unsuccessfully. Image not found.");
          } else {
            imgs.delete(imgID);
            System.out.printf("\n\t--> Image ID = %d of '%s' hotel has been deleted successfully.\n",
                imgID, img.getHotel().getHotel());
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

      else {
        System.out.println("\tX - Invalid option.");
      }

      Util.pause("\n\t");
    }
  }
}
