package orms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Country;

public class CountryORM extends ORM<Country> {
  public CountryORM() {
    tableName = "countries";
  }

  // List all row from database
  @Override
  public ArrayList<Country> listAll() {
    String query = "SELECT * FROM " + tableName;
    return this.rawQueryList(query);
  }

  // add a row
  @Override
  public Country add(Country country) {
    try (var stmt = connection.prepareStatement("INSERT INTO " + tableName
        + " VALUES(NULL,?)", Statement.RETURN_GENERATED_KEYS)) {
      // set value(country) to ?
      stmt.setString(1, country.getCountry());
      stmt.execute();
      var rs = stmt.getGeneratedKeys();
      if (rs.next())
        // set ID to reutrun
        country.setId(rs.getInt(1));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return country;
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
  public void update(Country country) {
    try (var stmt = connection.prepareStatement("UPDATE `" + tableName +
        "` SET `country` = ? WHERE `id` = ?")) {
      stmt.setString(1, country.getCountry());
      stmt.setInt(2, country.getId());
      stmt.execute();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  // execute query
  @Override
  public ArrayList<Country> rawQueryList(String query) {
    ArrayList<Country> ar = new ArrayList<>();

    try (var stmt = connection.createStatement()) {
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        ar.add(new Country(rs.getInt("id"),
            rs.getString("country")));
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return ar;
  }

}
