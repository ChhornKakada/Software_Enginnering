package orms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Role;

public class RoleORM extends ORM<Role> {

  public RoleORM() {
    tableName = "roles";
  }

  // List all row from database
  @Override
  public ArrayList<Role> listAll() {
    String query = "SELECT * FROM " + tableName;
    return this.rawQueryList(query);
  }

  // add a row
  @Override
  public Role add(Role role) {
    try (var stmt = connection.prepareStatement("INSERT INTO " + tableName
        + " VALUES(NULL,?)", Statement.RETURN_GENERATED_KEYS)) {
      stmt.setString(1, role.getRole());
      stmt.execute();
      var rs = stmt.getGeneratedKeys();
      if (rs.next())
        role.setId(rs.getInt(1));
    } catch (SQLException e) {
      // e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return role;
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
  public void update(Role role) {
    try (var stmt = connection.prepareStatement("UPDATE `" + tableName +
        "` SET `role` = ? WHERE `id` = ?")) {
      stmt.setString(1, role.getRole());
      stmt.setInt(2, role.getId());
      stmt.execute();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  // execute query
  @Override
  public ArrayList<Role> rawQueryList(String query) {
    ArrayList<Role> ar = new ArrayList<>();
    try (var stmt = connection.createStatement()) {
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        ar.add(new Role(rs.getInt("id"),
            rs.getString("role")));
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return ar;
  }

}
