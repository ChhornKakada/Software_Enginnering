package ORM;

import java.sql.*;
import java.util.ArrayList;

import Model.Role;

public class RoleORM extends ORM<Role> {
  public RoleORM() {
    tableName = "roles";
  }

  @Override
  public ArrayList<Role> listAll() {
    ArrayList<Role> arr = new ArrayList<>();
    try (var stmt = connection.createStatement()) {
      ResultSet resultSet = stmt.executeQuery("SELECT * FROM `" + tableName + "`");
      while (resultSet.next()) {
        arr.add(convertFromResultSetToRole(resultSet));
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return arr;
  }

  @Override
  public Role add(Role role) {
    try (var stmt = connection.prepareStatement("INSERT INTO `" + tableName + "` VALUES(null,?)",
        Statement.RETURN_GENERATED_KEYS)) {
      stmt.setString(1, role.getRoleName());
      stmt.execute();

      var resultSet = stmt.getGeneratedKeys();
      if (resultSet.next())
        role.setId(resultSet.getInt(1));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return role;
  }

  @Override
  public Boolean delete(int id) {
    try (var statment = connection.prepareStatement("DELETE FROM " + tableName + " WHERE id=" + id)) {
      statment.execute();

      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public void update(Role role) {
    String query = "UPDATE " + tableName + " SET rolename = ? WHERE id=" + role.getId();
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setString(1, role.getRoleName());
      stmt.execute();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public ArrayList<Role> rawQuery(String query) {
    ArrayList<Role> arr = new ArrayList<>();
    try (var statement = connection.createStatement()) {
      var resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        arr.add(convertFromResultSetToRole(resultSet));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return arr;
  }

  private Role convertFromResultSetToRole(ResultSet resultSet) throws SQLException {
    return new Role(resultSet.getInt("id"), resultSet.getString(2));
  }

  public static void main(String[] args) {
    ArrayList<Role> roles = new ArrayList<>();
    RoleORM roleORM = new RoleORM();

    var newRole_1 = new Role(0, "Testing Role");
    var d = roleORM.add(newRole_1);

    roles = roleORM.listAll();
    for (Role role : roles) {
      System.out.println("ID: " + role.getId() + " \t Name: " + role.getRoleName());
    }

    var newRole_2 = new Role(d.getId(), "Update Role Testing");
    roleORM.update(newRole_2);

    roles = roleORM.listAll();
    for (Role role : roles) {
      System.out.println("ID: " + role.getId() + " \t Name: " + role.getRoleName());
    }

    roleORM.delete(d.getId());


  }

}
