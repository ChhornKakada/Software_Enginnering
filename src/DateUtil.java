package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import utils.Util;

public class DateUtil {

  private Calendar startDate, endDate;
  private String OperationType;
  private long nDays;

  public DateUtil() {}

  // set start date
  public void setStartDate(int date, int month, int year) {
    Calendar tmp = Calendar.getInstance();
    tmp.set(year, month-1, date);
    this.startDate = tmp;
  }

  public void setStartDate(Date _startDate) {
    Calendar tmp = Calendar.getInstance();
    tmp.setTime(_startDate);
    this.startDate = tmp;
  }

  // get start date
  public Date getStartDate() {
    return this.startDate.getTime();
  }

  // set end date
  public void setEndDate(int date, int month, int year) {
    Calendar tmp = Calendar.getInstance();
    tmp.set(year, month-1, date);
    this.endDate = tmp;
  }

  public void setEndDate(Date endDate) {
    Calendar tmp = Calendar.getInstance();
    tmp.setTime(endDate);
    this.endDate = tmp;
  }

  // get end date
  public Date getEndDate() {
    return this.endDate.getTime();
  }

  // set operation type
  public void setOperationType(String oprt) {
    this.OperationType = oprt;
  }

  public String getOperation() { return this.OperationType; }

  // get number of different dates
  public long getNDays() { return this.nDays; }
  public void setNDays(long days) { this.nDays = days; }

  // substraction between startDate and endDate and return as days
  public void substraction() {
    // Calendar.getTime() returns "Date"
    // Date.getTime() returns "long"
    long timeDiff = this.endDate.getTime().getTime() - this.startDate.getTime().getTime();
    nDays = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
  }

  // Increment the start date by specifix days
  public void increment(int days) {
    this.nDays = days;
    this.setEndDate(this.getStartDate());
    this.endDate.add(Calendar.DATE, days);
  }

  // create database for this class and store datas in it
  public void createDB() {
    // check the library whether it add or not
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      // System.out.println("\n\tConnecting to Database...");
    } catch(ClassNotFoundException e) {
      System.out.println("\tDatabase - No Driver found!");
    }

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "")) {
      var stmt = conn.createStatement();

      // Create database
      var sql = "CREATE DATABASE IF NOT EXISTS `dateutil`";
      stmt.executeUpdate(sql);
      
      // use database
      sql = "USE `dateutil`";
      stmt.executeUpdate(sql);

      // Create table in database
      sql = "CREATE TABLE IF NOT EXISTS `dateutils`("
          + "ID int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
          + "startDate DATE,"
          + "endDate DATE,"
          + "nDays INT,"
          + "operationType VARCHAR(20) NOT NULL,"
          + "changedDate TIMESTAMP NOT NULL)";
      stmt.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void insertToDB() {
    // test if can connect to DB or not
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      // System.out.println("\n\tConnecting to Database...");
    } catch(ClassNotFoundException e) {
      System.out.println("\tDatabase - No Driver found!");
    }

    // insert to DB
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dateutil?user=root")) {
      // var stmt = conn.createStatement();
      // var sql = "USE `dateutil`";
      // stmt.executeUpdate(sql);

      // java.sql.Date = new java.sql.Date(long milliseconds)
      // this.startDate.getTime() returns "Date"
      // Date.getTime() returns "long milliseconds"
      java.sql.Date startDate = new java.sql.Date((this.startDate.getTime()).getTime());
      java.sql.Date endDate = new java.sql.Date((this.endDate.getTime()).getTime());

      // current date (changed date)
      Calendar now = Calendar.getInstance();
      java.sql.Timestamp currentDate = new java.sql.Timestamp((now.getTime()).getTime());

      String sql = "INSERT INTO `dateutils` ("
          + "startDate, endDate,"
          + "nDays, operationType,"
          + "changedDate) VALUES ("
          + "?,?,?,?,?)";

      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setDate(1, startDate);
      pstmt.setDate(2, endDate);
      pstmt.setInt(3, (int)this.nDays);
      pstmt.setString(4, this.OperationType);
      pstmt.setTimestamp(5, currentDate);

      pstmt.executeUpdate();
      System.out.println("\tDatabase - Insert sucessfully!");
    } 
    catch (SQLException e) {
      System.out.println("\tDatabase - Insert unsucessfully!");
      // e.printStackTrace();
    }
  }

}

class ex1 {

  public static void main(String[] args) throws ParseException {
    Util.clsScr();
    // Scanner sc = new Scanner(System.in);

    DateUtil dates = new DateUtil();
    dates.createDB();

    while(true) {
      Util.clsScr();
      System.out.println("""
        \n\t------ Date Calculating ------
        \t-------------------------------
        \t     1. Substraction
        \t     2. Increment
        \t     3. Stop the program
        \t------------------------------""");
        
      int opt = Util.getInt("\tYour option: ", "\tX - Integer only, input again: ");
  
      if(opt == 1) {
        System.out.println("\n\tDate format (dd-MM-yyyy)...");
        Date startDate = Util.inputDate("\t    Start Date : ", "dd-MM-yyyy");
        dates.setStartDate(startDate);
        dates.setOperationType("Substraction");
  
        Date endDate = Util.inputDate("\t    End Date   : ", "dd-MM-yyyy");
        dates.setEndDate(endDate);
        dates.substraction();
        System.out.println("\n\t|> The different of these 2 dates are " +dates.getNDays()+ " days.\n");
        // Insert data into Database
        dates.insertToDB();
      } 
      else if(opt == 2) {
        System.out.println("\n\tDate format (dd-MM-yyyy)...");
        Date startDate = Util.inputDate("\t    Start Date : ", "dd-MM-yyyy");
        dates.setStartDate(startDate);
        dates.setOperationType("Increment");
  
        int increas = Util.getInt("\t    Number(days) of increment: ", "\n\tX - Input integer only!\n");
        dates.increment(increas);
        System.out.println("\n\t|> Date after increment: " +Util.ndfm.format(dates.getEndDate())+ "\n");
        // Insert data into Database
        dates.insertToDB();
      } else if(opt == 3) {
        System.out.println("\n\tThank for using this program. *.*\n\n");
        break;
      }
      else System.out.println("\n\tX - Invalid Option, Cannot operate!\n");
      
      Util.pause("\n\n\t");
      
    }
  }
}