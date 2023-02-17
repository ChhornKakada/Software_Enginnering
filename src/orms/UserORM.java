package orms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.User;

public class UserORM extends ORM<User> {

  public UserORM() {
    tableName = "users";
  }

  // List all row from database
  @Override
  public ArrayList<User> listAll() {
    String query = "SELECT * FROM " + tableName;
    return this.rawQueryList(query);
  }

  // add a row
  @Override
  public User add(User user) {
    try (var stmt = connection.prepareStatement("INSERT INTO " + tableName
        + " VALUES(NULL,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
      stmt.setString(1, user.getUsername());
      stmt.setString(2, user.getPass());
      stmt.setString(3, user.getEmail());
      stmt.setInt(4, user.getRole().getId());
      stmt.setShort(5, user.getDiscount());
      stmt.setString(6, user.getAvatar());
      stmt.execute();
      var rs = stmt.getGeneratedKeys();
      if (rs.next())
        user.setId(rs.getInt(1));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return user;
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
  public void update(User user) {
    try (var stmt = connection.prepareStatement("UPDATE `" + tableName +
        "` SET `username` = ?, `pass` = ?, `email` = ?, " +
        "`roleid` = ?, `discount` = ?, `avatar` = ? WHERE `id` = ?")) {
      stmt.setString(1, user.getUsername());
      stmt.setString(2, user.getPass());
      stmt.setString(3, user.getEmail());
      stmt.setInt(4, user.getRole().getId());
      stmt.setShort(5, user.getDiscount());
      stmt.setString(6, user.getAvatar());
      stmt.setInt(7, user.getId());
      stmt.execute();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  // execute query
  @Override
  public ArrayList<User> rawQueryList(String query) {
    ArrayList<User> ar = new ArrayList<>();
    try (var stmt = connection.createStatement()) {
      ResultSet rs = stmt.executeQuery(query);
      RoleORM roles = new RoleORM();
      while (rs.next()) {
        String getRole = "SELECT * FROM `roles` WHERE `id` = " + rs.getInt(5);
        ar.add(new User(rs.getInt("id"),
            rs.getString("username"),
            rs.getString("pass"),
            rs.getString("email"),
            roles.rawQueryList(getRole).get(0),
            rs.getShort("discount"),
            rs.getString("avatar")));
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return ar;
  }

}
