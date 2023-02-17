package src;

import utils.Util;

public class PopularStore {

  public static void main(String[] args) {
    CustomerQueue.createDB();
    CustomerQueue cusQueue = new CustomerQueue();
    try {
      while(true) {
        Util.clsScr();
        System.out.println("""
          \n\t----------------------- Program's option ----------------------
          \t---------------------------------------------------------------
          \t     1. Add customer to waiting list (queue) for serving
          \t     2. Display list of customers in queue
          \t     3. List all served customers (orders history)
          \t     4. Serve customers
          \t     5. End the program
          \t---------------------------------------------------------------
            """);
        int option = Util.getInt("\tYour option: ", "\tX - input only integer number.\n");
  
        if(option == 1) {
          cusQueue.addNewCus();
        } else if(option == 2 ){
          cusQueue.listCusWaitToOrder();
        } else if(option == 3) {
          cusQueue.listCusWaitForFood();
        } else if(option == 4) {
          while(true) {
            System.out.println("\n");
            cusQueue.serveCustomer();
            System.out.print("\n\t(1:BreakTime ;  2:ContinueToServe) : ");
            int temp = Util.getOnly1or2("\tX - Enter only number 1 or 2! Input again: ");
            if(temp == 1) break;
          }
          
        } else if(option == 5) {
          System.out.println("\n\tThanks for using this program.\n\n");
          break;
        } else System.out.println("\tX - Invalid option. please input between 1-5.\n");
        Util.pause("\n\n\t");
      }
    } catch(Exception e) {
      System.out.println("\n\tThanks for using this program.\n\n");
    }
    
  }
}
