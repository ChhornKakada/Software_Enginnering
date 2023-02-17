package ORM;

import java.sql.*;
import java.util.ArrayList;

import Model.Group;

public class GroupORM extends ORM<Group> {
    public GroupORM() {
        tableName = "groups";
    }

    @Override
    public ArrayList<Group> listAll() {
        ArrayList<Group> arr = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM `" + tableName + "`");
            while (resultSet.next()) {
                arr.add(convertFromResultSetToGroup(resultSet));
            }
            
        } catch (Exception e) {
            e.printStackTrace();            
        }
        
        return arr;
    }

    @Override
    public Group add(Group group) {
        try (var stmt = connection.prepareStatement("INSERT INTO `" + tableName + "` VALUES(null,?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, group.getgroupName());
            stmt.execute();

            var resultSet = stmt.getGeneratedKeys();
            if(resultSet.next()) group.setId(resultSet.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return group;
    }

    @Override
    public Boolean delete(int id) {
        try (var statment = connection.prepareStatement("DELETE FROM `" + tableName + "` WHERE id=" + id)) {
            statment.execute();

            return true;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(Group data) {
        String  query = "UPDATE `" + tableName + "` SET groupname = ? WHERE id=" + data.getId();
        try (var stmt = connection.prepareStatement(query)) {
            stmt.setString(1, data.getgroupName());
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Group> rawQuery(String query) {
        ArrayList<Group> arr = new ArrayList<>();
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                arr.add(convertFromResultSetToGroup(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    private Group convertFromResultSetToGroup(ResultSet resultSet) throws SQLException {
        return new Group(resultSet.getInt("id"), resultSet.getString(2));
    }
    
    public static void main(String[] args) {
        ArrayList<Group> arr = new ArrayList<>();
        GroupORM orm = new GroupORM();

        var newGroup_1 = new Group(0, "Testing Group");
        var d = orm.add(newGroup_1);

        arr = orm.listAll();
        for (Group data : arr) {
            System.out.println("ID: " + data.getId() + " \t Name: " + data.getgroupName());
        }

        var newGroup_2 = new Group(d.getId(), "Update Group Testing");
        orm.update(newGroup_2);

        arr = orm.listAll();
        for (Group Group : arr) {
            System.out.println("ID: " + Group.getId() + " \t Name: " + Group.getgroupName());
        }

        // orm.delete(d.getId());
        

        arr = orm.rawQuery("SELECT * FROM `" + orm.tableName + "` WHERE id=1");
        for (Group Group : arr) {
            System.out.println("ID: " + Group.getId() + " \t Name: " + Group.getgroupName());
        }
    }

}
