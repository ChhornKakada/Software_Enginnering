package solutions;
import java.io.IOException;
import java.util.Scanner;

import modelClass.SMSList;
import modelClass.SMS;
import utils.Util;

public class tp7ex3 {
    public static void main(String[] args) throws IOException {
    Util util = new Util();
    Scanner input = new Scanner(System.in);
    String errorInput = "\tX - Invalid input, try again: ";
    String fileDestination = "storages/SMSList.txt";

    SMSList SMSList = new SMSList();
    SMSList.readDataFromStorage(fileDestination);

    SMS sms = new SMS();

    while(true) {
      util.clsScr();
      // menu
      System.out.println("""
          \t----------------------------- Option of the program ------------------------------
          \t    1. List all SMSes
          \t    2. View SMS Detail (decrypt content using password)
          \t    3. View readable SMSes (all SMS that can be decrypted using given password)
          \t    4. Remove SMSes by index
          \t    5. Quit
          \t-----------------------------------------------------------------------------------""");
      // Option
      int opt = util.getInt("\n\tChoose an option: ", errorInput);

      // 1. List all SMSes
      if(opt == 1) {
        SMSList.displaySMSesList();
      }

      // 2. View SMS Detail (decrypt content using password)
      else if(opt == 2) {
        int index = util.getInt("\n\tEnter index of the SMS: ", errorInput);
        System.out.print("\tEnter password to decrypt the content: ");
        String passwd = input.nextLine();

        if(index > 0 && index <= SMSList.size()) {
          System.out.println();
          SMSList.viewSMSDetail(index-1, passwd);
        } else System.out.println("\n\tX - Error: Invalid index.");
      }

      // 3. View readable SMSes (all SMS that can be decrypted using given password)
      else if(opt == 3) {
        System.out.print("\n\tEnter password: ");
        String passwd = input.nextLine();
        SMSList.displayReadableMsg(passwd);
      }

      // 4. Remove SMSes by index
      else if(opt == 4) {
        int index = util.getInt("\n\tEnter index of the SMS: ", errorInput);
        if(index > 0 && index <= SMSList.size()) {
          SMSList.removeSMSByIndex(index-1);
          System.out.printf("\n\t\\> The SMS has been deleted successfully.", index);
        } else System.out.println("\tX - Error: Invalid index.");
      }

      // 5. Quit and update to data to the storage
      else if(opt == 5) {
        SMSList.updateStorage(fileDestination);
        System.out.println("\n\t\\> Thank you for using our service.\n\n");
        break;
      }

      else System.out.println("\tError: Invalid option.");
      util.pause("\n\n\t");

    }

  }
}
