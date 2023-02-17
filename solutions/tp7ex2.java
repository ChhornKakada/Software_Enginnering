package solutions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import modelClass.SMSList;
import modelClass.SMS;
import utils.Util;

public class tp7ex2 {
  
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
      System.out.println("""
        \t+---------------------- Option of the program ----------------------+

        \t    1. Send new SMS with Encrypted content using password method
        \t    2. View SMS detail
        \t    3. List SMSes
        \t    4. Remove SMSes by index
        \t    5. Quit
        \t+-------------------------------------------------------------------+
          """);
    
      int opt = util.getInt("\tChoose an option: ", errorInput);

      // 1. Send new SMS with Encrypted content using password method
      if(opt == 1) {
        System.out.print("\n\tFrom : ");
        sms.from = input.nextLine();
        System.out.print("\tTo : ");
        sms.receiver = input.nextLine();
        sms.setType(util.getInt("\tType (Text:0, MMS:1) : ", errorInput));
        System.out.print("\tTitle : ");
        // input.nextLine();
        sms.setSubject(input.nextLine());

        ArrayList <String> userData = new ArrayList<>();
        System.out.println("\n\tContent (Enter END to end the content)");

        int k = 0;
        while (true) {
          if(k == 0) System.out.print("\t: ");
          else System.out.print("\t  ");
          String line = input.nextLine();
          if ("END".equalsIgnoreCase(line)) {
              break;
          }
          userData.add(line);
          k++;
        }
        sms.setContent(userData);
        
        System.out.print("\n\tPassword\n\t(to encrypt the content): ");
        String password = input.nextLine();
        sms.encryptContent(password);
        
        SMSList.add(sms);
        System.out.println("\n\t\\> Your massage has been sent successfully.");
      } 
      
      // 2. View SMS detail
      else if(opt == 2) {
        int index = util.getInt("\n\tEnter index of the SMS: ", errorInput);
        if(index > 0 && index <= SMSList.size()) {
          System.out.println();
          SMSList.viewSMSDetail(index-1);
        } else System.out.println("\tX - Error: Invalid index.");
      } 
      
      // 3. List SMSes
      else if(opt == 3) {
        SMSList.displaySMSesList();
      } 
      
      // 4. Remove SMSes by index
      else if(opt == 4) {
        int index = util.getInt("\n\tEnter index of the SMS: ", errorInput);
        if(index > 0 && index <= SMSList.size()) {
          SMSList.removeSMSByIndex(index-1);
          System.out.printf("\n\t\\> The SMS has been deleted successfully.", index);
        } else System.out.println("\tX - Error: Invalid index.");
      } 
      
      // 5. Quit and update the data of the storage
      else if(opt == 5) {
        SMSList.updateStorage(fileDestination);
        System.out.println("\n\t\\> Thank you for using our service.\n\n");
        break;
      } 
      
      // any option that not contain in the list
      else System.out.println("\tError: Invalid option.");

      util.pause("\n\n\t");

    }
  }
}
