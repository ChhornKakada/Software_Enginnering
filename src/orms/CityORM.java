package orms;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import models.City;
import models.Country;

public class CityORM extends ORM<City> {
  public CityORM() {
    tableName = "cities";
  }

  // list all cities from database
  @Override
  public ArrayList<City> listAll() {
    String query = "SELECT * FROM " + tableName;
    return this.rawQueryList(query);
  }

  // add new city in the DB
  @Override
  public City add(City city) {
    try (var stmt = connection.prepareStatement("INSERT INTO `" + tableName
        + "` VALUES(NULL, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
      stmt.setString(1, city.getCity());
      stmt.setInt(2, city.getCountry().getId());
      stmt.setString(3, city.getUcity());
      stmt.execute();
      var rs = stmt.getGeneratedKeys();
      while (rs.next()) {
        city.setId(rs.getInt(1));
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return city;
  }

  // delete a row by ID
  public boolean delete(int id) {
    boolean isDone = false;
    try (var stmt = connection.createStatement()) {
      String query = "DELETE FROM `" + tableName + "` WHERE `id` = " + id;
      stmt.executeUpdate(query);
      isDone = true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return isDone;
  }

  // update a row by ID
  public void update(City city) {
    try (var stmt = connection.prepareStatement("UPDATE `" + tableName + "` SET `city` = ?" +
        ", `countryid` = ?, `ucity` = ? WHERE `id` = ?")) {
      stmt.setString(1, city.getCity());
      stmt.setInt(2, city.getCountry().getId());
      stmt.setString(3, city.getUcity());
      stmt.setInt(4, city.getId());
      stmt.execute();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  // / execute query
  public ArrayList<City> rawQueryList(String query) {
    ArrayList<City> ar = new ArrayList<>();
    try (var stmt = connection.createStatement()) {
      ResultSet rs = stmt.executeQuery(query);
      CountryORM countryORM = new CountryORM();
      while (rs.next()) {
        String tmp = "SELECT * FROM `countries` WHERE `id` = " + rs.getInt(3);
        ArrayList<Country> counties = countryORM.rawQueryList(tmp);
        ar.add(new City(rs.getInt(1), rs.getString(2),
            counties.get(0)));
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return ar;
  }

}
