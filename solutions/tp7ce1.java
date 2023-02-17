package solutions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import modelClass.AccountList;
import modelClass.SMSList;
import modelClass.SMS;
import utils.Util;

public class tp7ce1 {

  public static void main(String[] args) throws IOException {
    Util util = new Util();
    Scanner input = new Scanner(System.in);
    String fileDestination = "storages/Accountlist.txt";

    AccountList accounts = new AccountList();
    accounts.readDataFromStorage(fileDestination);

    util.clsScr();
    
    System.out.println("\tLogin...");
    System.out.print("\n\tUsername: ");
    String username = input.nextLine();
    System.out.print("\tPassword: ");
    String password = input.nextLine();

    if(accounts.isAccExist(username, password)) {

      String errorInput = "\tX - Invalid input, try again: ";

      SMSList SMSList = new SMSList();
      SMSList.readDataFromStorage("SMSList.txt");
  
      SMS sms = new SMS();
      while(true) {
        util.clsScr();
        System.out.println("\n\t\\> Username: " +username);
        // menu
        System.out.println("""
          \n\t---------- Welcome to private SMS app -----------
          \t-------------------------------------------------
          \t        1. List all SMS
          \t        2. View SMS Detail
          \t        3. Send SMS
          \t        4. Remove SMS by index
          \t        5. Quit
          \t-------------------------------------------------""");
        // Option
        int opt = util.getInt("\n\tChoose an option: ", errorInput);
  
        // 1. List all SMS
        if(opt == 1) {
          SMSList.displaySMSesList(username);
        }
  
        // 2. View SMS Detail 
        else if(opt == 2) {
          int index = util.getInt("\n\tEnter index of the SMS: ", errorInput);

          if(index > 0 && index <= SMSList.size(username)) {
            System.out.println();
            SMSList.viewSMSDetail(username, index);
          } else System.out.println("\n\tX - Error: Invalid index.");
        }
  
        // 3. Send SMS
        else if(opt == 3) {
          sms.from = username;
          System.out.print("\n\tTo    : ");
          sms.receiver = input.nextLine();
          // sms.setType(util.getInt("\tType (Text:0, MMS:1) : ", errorInput));
          System.out.print("\tTitle : ");
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
          
          // System.out.print("\n\tPassword\n\t(to encrypt the content): ");
          // String password = input.nextLine();
          sms.encryptContent(password);
          
          SMSList.add(sms);
          System.out.println("\n\t\\> Your massage has been sent successfully.");
        }
  
        // 4. Remove SMSes by index
        else if(opt == 4) {
          int index = util.getInt("\n\tEnter index of the SMS: ", errorInput);
          if(index > 0 && index <= SMSList.size(username)) {
            SMSList.removeSMSByIndex(username, index);
            System.out.print("\n\t\\> The SMS has been deleted successfully.");
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

    else System.out.println("\n\tX - Error: Username or Password is incorrect!\n\n");
  }
}

