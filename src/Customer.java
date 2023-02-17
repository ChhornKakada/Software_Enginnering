package src;
import java.util.Calendar;
import java.util.Date;

import utils.Util;

public class Customer {
  private int number;
  private Date enterDate;
  private String [] statusList = {"waiting to order", "ordering", "waiting for food"};
  private String status = statusList[0];

  // number
  public void setNumber(int number) { this.number = number; }
  public int getNumber() { return number; }

  // Entering Date
  public void setEnterDate(Date enterDate) { this.enterDate = enterDate; }
  public Date getEnterDate() { return enterDate; }

  // Status
  public void waitingToOrder() { this.status = this.statusList[0]; }
  public void ordering() { this.status = this.statusList[1]; }
  public void waitingForFood() { this.status = this.statusList[2]; }
  public String getStatus() { return status; }

  // Construtor
  public Customer(int number, Date enterDate) {
    this.number = number;
    this.enterDate = enterDate;
  }

  public Customer() {}

  // Data output
  public void dataOutput() {
    System.out.println("\tNumber        : " +this.number);
    System.out.println("\tEntering Date : " +Util.dfm.format(this.enterDate));
    System.out.println("\tStatus        : " +this.status);
  }

  // Date input
  public static Customer dataInput() {
    
    int number = Util.getInt("\tNumber        : ", "\tX - Input only number!");
    Date enterDate = Util.inputDate("\tEntering Date : ", "dd-MM-yyyy");
    Calendar temp = Calendar.getInstance();
    temp.setTime(enterDate);
    Customer cus = new Customer(number, temp.getTime());
    return cus;
  }
  
}
