import java.util.Scanner;

public class MainMenu {
  public static void main(String[] args) {
    Scanner sn = new Scanner(System.in);
    while (true) {
      Util.clsScr();
      System.out.println("""
          \n\t-------------- Main Program --------------
          \t------------------------------------------
          \t\t1. Countries
          \t\t2. Cities
          \t\t3. Hotels
          \t\t4. Images
          \t\t5. Roles
          \t\t6. Users""");
      
          if (CE1.mainUser == null) {
            System.out.println("\t\t7. End the program");
          } else System.out.println("\t\t7. Logout");
          System.out.println("\t------------------------------------------");
          
          

      System.out.print("\tYour option : ");
      int option = Util.getIntWithTryCatch("\tX - Integer only. Input again : ");

      if (option == 1) {
        CountryListing.main(null);
      }
      
      else if (option == 2) {
        CityListing.main(null);
      }

      else if (option == 3) {
        HotelListing.main(null);
      }

      else if (option == 4) {
        ImageListing.main(null);
      }

      else if (option == 5) {
        RoleListing.main(null);
      }

      else if (option == 6) {
        UserListing.main(null);
      }

      else if (option == 7) {
        
        if (CE1.mainUser != null) {
          System.out.printf("\n\t--> User '%s' has been logout.\n\n", CE1.mainUser.getUsername());
          CE1.mainUser = null;
        } else {
          System.out.printf("\n\t--> Thanks for using this program.\n\n");
        }
        break;
      }

      else {
        System.out.println("\tX - Invalid option.");
      }
      Util.pause("\n\t");
    }
  }
}
