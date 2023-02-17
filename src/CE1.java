import java.util.Scanner;

import models.User;

public class CE1 {

  public static User mainUser = null;
  public static boolean isMainMenu = true; // true when not login

  public static void main(String[] args) {

    Scanner sn = new Scanner(System.in);

    while (true) {
      Util.clsScr();
      System.out.println("""
          \n\t------- TP12.CE.1. Login/Logout --------
          \t----------------------------------------
          \t\t1. Login
          \t\t2. Use as guest
          \t\t3. End the program
          \t----------------------------------------
          """);

      System.out.print("\tYour option : ");
      int option = Util.getInt("\tX - Integer only. Input again : ");

      if (option == 1) {
        System.out.print("\n\tUsername : ");
        String username = sn.nextLine();
        System.out.print("\tPassword : ");
        String passwd = sn.nextLine();

        mainUser = UserListing.getUser(username, passwd);
        if (mainUser == null) {
          System.out.println("\n\tX - Username or Password is incorrect.\n");
        } else {
          // System.out.println(mainUser.getUsername()+ ", " +mainUser.getPass() + ", " +mainUser.getRole().getRole());
          System.out.println("\n\t--> Successfully logged in!");
          Util.pause("\n\t");
          isMainMenu = false;
          MainMenu.main(null);
        }
      } 
      
      // use as guest
      else if (option == 2) {
        isMainMenu = false;
        MainMenu.main(null);
      } 
      
      // end the program
      else if (option == 3) {
        System.out.println("\n\t--> Thanks for using this program.\n\n");
        break;
      }
      Util.pause("\n\t");
    }

  }
}
