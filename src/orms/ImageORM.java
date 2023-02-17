package orms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Image;

public class ImageORM extends ORM<Image> {

  public ImageORM() {
    tableName = "images";
  }

  // List all row from database
  @Override
  public ArrayList<Image> listAll() {
    String query = "SELECT * FROM " + tableName;
    return this.rawQueryList(query);
  }

  // add a row
  @Override
  public Image add(Image img) {
    try (var stmt = connection.prepareStatement("INSERT INTO " + tableName
        + " VALUES(NULL,?,?)", Statement.RETURN_GENERATED_KEYS)) {
      stmt.setInt(1, img.getHotel().getId());
      stmt.setString(2, img.getImagepath());
      stmt.execute();
      var rs = stmt.getGeneratedKeys();
      if (rs.next())
        img.setId(rs.getInt(1));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return img;
  }

  // remove a row by ID
  @Override
  public boolean delete(int id) {
    boolean isSuccess = false;
    try (var stmt = connection.createStatement()) {
      stmt.executeUpdate("DELETE FROM `" + tableName + "` where id = " + id);
      isSuccess = true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return isSuccess;
  }

  // update a country
  @Override
  public void update(Image img) {
    try (var stmt = connection.prepareStatement("UPDATE `" + tableName +
        "` SET `hotelid` = ?, `imagepath` = ? WHERE `id` = ?")) {
      stmt.setInt(1, img.getHotel().getId());
      stmt.setString(2, img.getImagepath());
      stmt.setInt(3, img.getId());
      stmt.execute();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  // execute query
  @Override
  public ArrayList<Image> rawQueryList(String query) {
    ArrayList<Image> ar = new ArrayList<>();
    try (var stmt = connection.createStatement()) {
      ResultSet rs = stmt.executeQuery(query);
      HotelORM hotels = new HotelORM();
      while (rs.next()) {
        String queryHotel = "SELECT * FROM `hotels` WHERE `id` = " + rs.getInt(2);
        // Hotel hotel = hotels.rawQueryList(queryHotel);
        ar.add(new Image(rs.getInt("id"),
            hotels.rawQueryList(queryHotel).get(0), rs.getString(3)));
      }
    } catch (Exception e) {
      System.out.println(e.getClass());
      System.out.println(e.getMessage());
    }
    return ar;
  }
}
