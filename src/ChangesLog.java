package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import utils.Util;

public class ChangesLog extends DateUtil {



  public ChangesLog() {}

  @Override
  public String toString() {
    String sql = "SELECT * FROM `dateutils` "
                + "WHERE Date(changedDate) <= CURDATE() "
                + "ORDER BY changedDate DESC "
                + "LIMIT 5";
    return this.strDataList(sql);
  }

  // for displaying
  private void dataList(String sql) {
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dateutil?user=root")) {
      //stmt.executeUpdate(sql);
      var stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      System.out.print(this.header()); // print header (content)
      while(rs.next()){
        Date date = new Date(rs.getTimestamp(6).getTime());  
        System.out.printf("\t  %2d   %s   %s   %6d   %-13s  %s\n", 
            rs.getInt(1),
            Util.ndfm.format(rs.getDate(2)),
            Util.ndfm.format(rs.getDate(3)),
            rs.getInt(4),
            rs.getString(5),
            Util.dfm.format(date));
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private String header() {
    String result = "\t| ID | Start_Date |  End_Date  | n_Days |  Operation   |    Changed_Date     |"
                  + "\n\t+----+------------+------------+--------+--------------+---------------------+\n";
    return result;
  }

  // for displaying and return as String
  private String strDataList(String sql) {
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dateutil?user=root")) {
      //stmt.executeUpdate(sql);
      var stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      String resutl = this.header();
      String operation = "Substraction";
      // System.out.println("""
      //   \t| ID | Start_Date |  End_Date  | n_Days |  Operation   |    Changed_Date     |
      //   \t+----+------------+------------+--------+--------------+---------------------+
      //   """);
      while(rs.next()){
        if(rs.getString(5).equals("Increment")) {
          operation = rs.getString(5) + "   ";
        }
        // Timestamp ts=new Timestamp(System.currentTimeMillis());  
        Date date = new Date(rs.getTimestamp(6).getTime());  
        resutl += "\t  " +String.format("%02d", rs.getInt(1))
              +"   "+ Util.ndfm.format(rs.getDate(2))
              +"   "+ Util.ndfm.format(rs.getDate(3))
              +"   "+ String.format("%6d", rs.getInt(4))
              +"   "+ operation
              +"   "+ Util.dfm.format(date)+ "\n";
      }
      return resutl;
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  // list all data in the database
  public void listAllChanged() {
    var sql = "SELECT * FROM dateutils";
    this.dataList(sql);
  }

  // list the current week 
  public void listCurrentWeek() {
    var sql = "select * from dateutils where week(changedDate)=week(now())";
    this.dataList(sql);
  }

  // Listing changes by date range
  public void listingChangesByDateRange(Date startDate, Date endDate) {
    String start = Util.sqlDfm.format(startDate);
    String end = Util.sqlDfm.format(endDate);
    var sql = "SELECT * FROM dateutils  WHERE changedDate >= '" +start+" 00:00:00' AND  changedDate <= '" +end+ " 23:59:59'";
    this.dataList(sql);
  }

  // Listing changes by date
  public void listingChangedByDate(Date date) {
    String temp = Util.sqlDfm.format(date);
    var sql = "SELECT * FROM dateutils WHERE changedDate >= '" +temp+" 00:00:00' AND  changedDate <= '" +temp+ " 23:59:59'";
    this.dataList(sql);
  }

}

class ex2 {
  public static void main(String[] args) {
    ChangesLog changedLog = new ChangesLog();

    while(true) {
      Util.clsScr();
      System.out.println("""
        \n\t------------ Option of the program ------------
        \t-----------------------------------------------
        \t    1. list last 5 changes in date history
        \t    2. Listing changes by date
        \t    3. Listing changes by week
        \t    4. Listing changes by date range
        \t    5. List all changes
        \t    6. Exit the program
        \t-----------------------------------------------
          """);
  
      int opt = Util.getInt("\tYour option: ", "\tX - Only integer! Input again: ");
  
      if(opt == 1) {
        System.out.println("\n\tThe last 5 changes in dateutils history are...\n");
        System.out.println(changedLog);
      } 

      else if(opt == 2) {
        System.out.println("\n\tDate format (dd-MM-yyyy)...");
        Date date = Util.inputDate("\tChanged Date : ", "dd-MM-yyyy");
        System.out.println("\n");
        changedLog.listingChangedByDate(date);
      } 
      
      else if(opt == 3) {
        System.out.println("\n\tDisplay all changed in this week...\n");
        changedLog.listCurrentWeek();
      } 
      
      else if(opt == 4) {
        System.out.println("\n\tDate format (dd-MM-yyyy)...");
        Date startDate = Util.inputDate("\tStart_Date : ", "dd-MM-yyyy");
        Date endDate = Util.inputDate("\tEnd_Date   : ", "dd-MM-yyyy");
        System.out.println("\n");
        changedLog.listingChangesByDateRange(startDate, endDate);
      } 
      
      else if(opt == 5) {
        System.out.println("\n\tAll dates histories...\n");
        changedLog.listAllChanged();
      } 

      else if(opt == 6) {
        System.out.println("\n\tThanks for using this program! *.*\n\n");
        break;
      }
      
      else System.out.println("\n\tX - Invalid option.");

      Util.pause("\n\n\t");
    }
  }

}
