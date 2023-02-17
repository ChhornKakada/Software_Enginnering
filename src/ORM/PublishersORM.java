package ORM;

import java.sql.*;
import java.util.ArrayList;

import Model.Address;
import Model.Publishers;

public class PublishersORM extends ORM<Publishers> {
    public PublishersORM() {
        tableName = "publishers";
    }

    @Override
    public ArrayList<Publishers> listAll() {
        ArrayList<Publishers> arr = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM `" + tableName + "`");
            while (resultSet.next()) {
                arr.add(convertFromResultSetToPublishers(resultSet));
            }
            
        } catch (Exception e) {
            e.printStackTrace();            
        }
        
        return arr;
    }

    @Override
    public Publishers add(Publishers data) {
        try (var stmt = connection.prepareStatement("INSERT INTO `" + tableName + "` VALUES(null,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, data.getPublisherName());
            stmt.setInt(2, data.getAddress().getId());
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
    public void update(Publishers data) {
        String  query = "UPDATE " + tableName + " SET publisername = ?, address_id = ? WHERE id=" + data.getId();
        try (var stmt = connection.prepareStatement(query)) {
            stmt.setString(1, data.getPublisherName());
            stmt.setInt(2, data.getAddress().getId());
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Publishers> rawQuery(String query) {
        ArrayList<Publishers> arr = new ArrayList<>();
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                arr.add(convertFromResultSetToPublishers(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    private Publishers convertFromResultSetToPublishers(ResultSet resultSet) throws SQLException {
        Address add = new AddressORM().rawQuery("SELECT * FROM addresses WHERE id=" + resultSet.getInt("address_id")).get(0);
        return new Publishers(resultSet.getInt("id"), 
                                resultSet.getString(2),
                                add
                                );
    }
    
    public static void main(String[] args) {
        ArrayList<Publishers> Publisherss = new ArrayList<>();
        PublishersORM PublishersORM = new PublishersORM();

        Address add = new Address(1, null, null, null, null, null, null, null, null, null, null);

        var newPublishers_1 = new Publishers(0, "Testing Publishers", add);
        var d = PublishersORM.add(newPublishers_1);

        Publisherss = PublishersORM.listAll();
        for (Publishers Publishers : Publisherss) {
            System.out.println("ID: " + Publishers.getId() + " \t Name: " + Publishers.getPublisherName());
        }

        var newPublishers_2 = new Publishers(d.getId(), "Update Testing Publishers", add);
        PublishersORM.update(newPublishers_2);

        Publisherss = PublishersORM.listAll();
        for (Publishers Publishers : Publisherss) {
            System.out.println("ID: " + Publishers.getId() + " \t Name: " + Publishers.getPublisherName());
        }

        PublishersORM.delete(d.getId());
        

        Publisherss = PublishersORM.rawQuery("SELECT * FROM " + PublishersORM.tableName + " WHERE id=1");
        for (Publishers Publishers : Publisherss) {
            System.out.println("ID: " + Publishers.getId() + " \t Name: " + Publishers.getPublisherName());
        }
    }


}
