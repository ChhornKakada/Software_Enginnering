package orms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Hotel;

public class HotelORM extends ORM<Hotel> {
  public HotelORM() {
    tableName = "hotels";
  }

  // List all row
  @Override
  public ArrayList<Hotel> listAll() {
    String query = "SELECT * FROM " + tableName;
    return this.rawQueryList(query);
  }

  // add a row
  @Override
  public Hotel add(Hotel hotel) {
    try (var stmt = connection.prepareStatement("INSERT INTO " + tableName
        + " VALUES(NULL,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
      stmt.setString(1, hotel.getHotel());
      stmt.setInt(2, hotel.getCountry().getId());
      stmt.setInt(3, hotel.getCity().getId());
      stmt.setShort(4, hotel.getStars());
      stmt.setDouble(5, hotel.getCost());
      stmt.setString(6, hotel.getInfo());
      stmt.execute();
      var rs = stmt.getGeneratedKeys();
      if (rs.next())
        // set ID to reutrun
        hotel.setId(rs.getInt(1));
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return hotel;
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
  public void update(Hotel hotel) {
    try (var stmt = connection.prepareStatement("UPDATE `" + tableName +
        "` SET `hotel` = ?, `countryid` = ?, `cityid` = ?, " +
        "`stars` = ?, `cost` = ?, `info` = ? WHERE `id` = ?")) {
      stmt.setString(1, hotel.getHotel());
      stmt.setInt(2, hotel.getCountry().getId());
      stmt.setInt(3, hotel.getCity().getId());
      stmt.setShort(4, hotel.getStars());
      stmt.setDouble(5, hotel.getCost());
      stmt.setString(6, hotel.getInfo());
      stmt.setInt(7, hotel.getId());
      stmt.execute();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  // execute query
  @Override
  public ArrayList<Hotel> rawQueryList(String query) {
    ArrayList<Hotel> ar = new ArrayList<>();
    try (var stmt = connection.createStatement()) {
      ResultSet rs = stmt.executeQuery(query);
      CountryORM countries = new CountryORM();
      CityORM cities = new CityORM();
      while (rs.next()) {
        String getCountry = "SELECT * FROM `countries` WHERE `id` = " + rs.getInt(3);
        String getCity = "SELECT * FROM `cities` WHERE `id` = " + rs.getInt(4);
        ar.add(new Hotel(
            rs.getInt(1),
            rs.getString(2),
            countries.rawQueryList(getCountry).get(0),
            cities.rawQueryList(getCity).get(0),
            rs.getShort(5),
            rs.getDouble(6),
            rs.getString(7)));
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return ar;
  }
}

// class ex3hotel {
//   public static void main(String[] args) {
//     HotelORM hotels = new HotelORM();
//     CountryORM countries = new CountryORM();
//     CityORM cities = new CityORM();
//     short stars = 4;

//     Hotel h = new Hotel(3, "Kimhak Baramey", 
//         countries.listAll().get(2), cities.listAll().get(1), stars,
//         180, "On the Mounatin and can feel the sky.");
//     hotels.add(h);
//     // hotels.update(h);
    
//     hotels.delete(3);

//     // add 100%, rawQuery 100%, listAll 100%, update 100%,

//     String query = "SELECT * FROM `hotels` WHERE `id` = 3";
//     for (var hotel : hotels.rawQueryList(query)) {
//       System.out.printf("""
//           ID      : %d
//           Name    : %s
//           Country : %s
//           City    : %s
//           Stars   : %d
//           Cost    : %.2f
//           Info    : %s\n
//           """, hotel.getId(), hotel.getHotel(), hotel.getCountry().getCountry(),
//           hotel.getCity().getCity(), hotel.getStars(), hotel.getCost(), hotel.getInfo());
//     }


    // Hotel h = new Hotel(2, "Lasimaa Tinjin", countries.listAll().get(13),
    // cities.listAll().get(0), stars, 10, "On the mount TAILA.");
    // // hotels.add(hotel);
    // hotels.update(h);
    // hotels.delete(2);

    // String query = "SELECT * FROM `hotels`";
    // for (var hotel : hotels.listAll()) {
    //   System.out.printf("""
    //       ID      : %d
    //       Name    : %s
    //       Country : %s
    //       City    : %s
    //       Stars   : %d
    //       Cost    : %.2f
    //       Info    : %s\n
    //       """, hotel.getId(), hotel.getHotel(), hotel.getCountry().getCountry(),
    //       hotel.getCity().getCity(), hotel.getStars(), hotel.getCost(), hotel.getInfo());
    // }

//   }
// }