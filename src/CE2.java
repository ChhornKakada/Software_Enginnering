import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import models.User;
import utils.DBManager;

public class CE2 {

  public static boolean isCE2 = false;

  public static String queryCreateTable = """
      CREATE TABLE IF NOT EXISTS hotelcomments(
        userid INT REFERENCES users(id),
        hotelid INT REFERENCES hotels(id),
        comment VARCHAR(128)
      )
        """;
        
  public static String queryDeleteTable = "DROP TABLE IF EXISTS `hotelcomments`";
  public static DBManager getInstance = DBManager.getInstance("se.tp11.i4");

  // Insert data into hotelcomments
  public static void insertToHotelComments(int userID, int hotelID, String comment) {
    try (var stmt = getInstance.getConn().prepareStatement("INSERT INTO `hotelcomments` VALUES(?,?,?)")) {
      stmt.setInt(1, userID);
      stmt.setInt(2, hotelID);
      stmt.setString(3, comment);
      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // fetch data from hotel comment by hotelid
  public static void displayHotelComment(int hotelID) {
    try (var stmt = getInstance.getConn().createStatement()) {
      String query = "SELECT * FROM `hotelcomments` WHERE `hotelid` = " + hotelID;
      ResultSet rs = stmt.executeQuery(query);
      System.out.println("\tComment from other users...");
      while (rs.next()) {
        User user = UserListing.getUser(rs.getInt("userid"));
        System.out.printf("\t%s : %s\n", user.getUsername(), rs.getString("comment"));
          }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static void main(String[] args) throws SQLException {
    isCE2 = true;

    var stmt = getInstance.getConn().createStatement();
    stmt.execute(queryCreateTable);

    CE1.main(null);


  }
}
