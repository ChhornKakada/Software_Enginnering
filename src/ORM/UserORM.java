package ORM;

import java.sql.*;
import java.util.ArrayList;

import Model.Group;
import Model.Role;
import Model.User;

public class UserORM extends ORM<User> {
    public UserORM() {
        tableName = "users";
    }

    @Override
    public ArrayList<User> listAll() {
        ArrayList<User> arr = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM `" + tableName + "`");
            while (resultSet.next()) {
                arr.add(convertFromResultSetToUser(resultSet));
            }
            
        } catch (Exception e) {
            e.printStackTrace();            
        }
        
        return arr;
    }

    @Override
    public User add(User data) {
        try (var stmt = connection.prepareStatement("INSERT INTO `" + tableName + "` VALUES(null,?,MD5(?),?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, data.getUsername());
            stmt.setString(2, data.getPwd());
            stmt.setInt(3, data.getRole().getId());
            stmt.setString(4, data.getToken());
            stmt.setInt(5, data.getGroup().getId());
            stmt.setString(6, data.getRemoteAddr());
            stmt.setString(7, data.getForwardAddr());
            stmt.setString(8, data.getImage());
            
            stmt.execute();

            var resultSet = stmt.getGeneratedKeys();
            if(resultSet.next()) data.setId(resultSet.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
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
    public void update(User data) {
        String  query = "UPDATE " + tableName + " SET username = ?, pwd = MD5(?), role_id = ?, token = ?, group_id = ?, remote_addr = ?, forward_addr = ?, image = ? WHERE id=" + data.getId();
        try (var stmt = connection.prepareStatement(query)) {
            stmt.setString(1, data.getUsername());
            stmt.setString(2, data.getPwd());
            stmt.setInt(3, data.getRole().getId());
            stmt.setString(4, data.getToken());
            stmt.setInt(5, data.getGroup().getId());
            stmt.setString(6, data.getRemoteAddr());
            stmt.setString(7, data.getForwardAddr());
            stmt.setString(8, data.getImage());
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> rawQuery(String query) {
        ArrayList<User> arr = new ArrayList<>();
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                arr.add(convertFromResultSetToUser(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    private User convertFromResultSetToUser(ResultSet resultSet) throws SQLException {
        var group = new GroupORM().rawQuery("SELECT * FROM `groups` WHERE id=" + resultSet.getInt("group_id")).get(0);
        var role = new RoleORM().rawQuery("SELECT * FROM roles WHERE id=" + resultSet.getInt("role_id")).get(0);

        return new User(resultSet.getInt("id"),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        role,
                        resultSet.getString(5),
                        group,
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)
        );
    }
    
    public static void main(String[] args) {
        ArrayList<User> arr = new ArrayList<>();
        UserORM orm = new UserORM();

        Role role = new Role(1, null);
        Group group = new Group(1, null);

        var newUser_1 = new User(0, "Testing User", "234", role, "1234565432", group, "1233123", "098789", "Image/123.png");
        var d = orm.add(newUser_1);

        arr = orm.listAll();
        for (User data : arr) {
            System.out.println("\t" + 
                            data.getId() + " \t" + 
                            data.getUsername() + "\t" +
                            data.getPwd() + "\t" +
                            data.getRole() + "\t" +
                            data.getGroup() + "\t" +
                            data.getRemoteAddr() + "\t" +
                            data.getForwardAddr() + "\t" +
                            data.getImage()
                            );
        }

        var newUser_2 = new User(d.getId(), "Update User", "234", role, "1234565432", group, "1233123", "098789", "Image/123.png");
        orm.update(newUser_2);

        arr = orm.listAll();
        for (User data : arr) {
            System.out.println("\t" + 
                            data.getId() + " \t" + 
                            data.getUsername() + "\t" +
                            data.getPwd() + "\t" +
                            data.getRole() + "\t" +
                            data.getGroup() + "\t" +
                            data.getRemoteAddr() + "\t" +
                            data.getForwardAddr() + "\t" +
                            data.getImage()
                            );        }

        orm.delete(d.getId());
        

        arr = orm.rawQuery("SELECT * FROM " + orm.tableName + " WHERE id=1");
        for (User data : arr) {
            System.out.println("\t" + 
                            data.getId() + " \t" + 
                            data.getUsername() + "\t" +
                            data.getPwd() + "\t" +
                            data.getRole() + "\t" +
                            data.getGroup() + "\t" +
                            data.getRemoteAddr() + "\t" +
                            data.getForwardAddr() + "\t" +
                            data.getImage()
            );
        }
    }

}
