import java.text.SimpleDateFormat;
import java.util.Date;

public class Computer {
  public String ID;
  private String serialnumber;
  public Date purchasedDate;
  protected boolean isDamaged = false;
  String model; // accessible only in the same folder

  public Computer(String ID, String serial, String model, Date date){
    this.ID = ID;
    this.serialnumber = serial;
    this.purchasedDate = date;
    this.model = model;
  }

  public String getSerialNumber() { return this.serialnumber; }
  public void updatedStatus(boolean b) { this.isDamaged = b; }
  public boolean checkCondition() { return this.isDamaged; }

  public void show() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy");
    System.out.printf("\t|  %3s  |   %-14s|  %-29s |  %s  |   %-5s   |\n",this.ID, this.serialnumber, this.model, dateFormat.format(this.purchasedDate), this.isDamaged);
  }
  
}

