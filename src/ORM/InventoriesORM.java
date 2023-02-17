package ORM;

import java.sql.*;
import java.util.ArrayList;

import Model.Book;
import Model.Inventory;

public class InventoriesORM extends ORM<Inventory> {
    public InventoriesORM() {
        tableName = "inventories";
    }

    @Override
    public ArrayList<Inventory> listAll() {
        ArrayList<Inventory> arr = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM `" + tableName + "`");
            while (resultSet.next()) {
                arr.add(convertFromResultSetToInventory(resultSet));
            }
            
        } catch (Exception e) {
            e.printStackTrace();            
        }
        
        return arr;
    }

    @Override
    public Inventory add(Inventory Inventory) {
        try (var stmt = connection.prepareStatement("INSERT INTO `" + tableName + "` VALUES(null,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, Inventory.getBook().getId());
            stmt.setInt(2, Inventory.getCopies());
            stmt.setString(3, Inventory.getSrcurl());
            stmt.setTimestamp(4, Inventory.getCreateAt());


            stmt.execute();

            var resultSet = stmt.getGeneratedKeys();
            if(resultSet.next()) Inventory.setId(resultSet.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Inventory;
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
    public void update(Inventory Inventory) {
        String  query = "UPDATE " + tableName + " SET book_id = ?, copies = ?, srcurl = ?, created_at = ? WHERE id=" + Inventory.getId();
        try (var stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, Inventory.getBook().getId());
            stmt.setInt(2, Inventory.getCopies());
            stmt.setString(3, Inventory.getSrcurl());
            stmt.setTimestamp(4, Inventory.getCreateAt());
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Inventory> rawQuery(String query) {
        ArrayList<Inventory> arr = new ArrayList<>();
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                arr.add(convertFromResultSetToInventory(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    private Inventory convertFromResultSetToInventory(ResultSet resultSet) throws SQLException {
        var b = new  BookORM().rawQuery("SELECT * FROM books WHERE id=" + resultSet.getInt(2)).get(0);

        return new Inventory(resultSet.getInt("id"), 
                            b,
                            resultSet.getInt(3),
                            resultSet.getString(4),
                            resultSet.getTimestamp(5)
                            );
    }
    
    public static void main(String[] args) {
        ArrayList<Inventory> arr = new ArrayList<>();
        InventoriesORM orm = new InventoriesORM();

        Book b = new Book(1, null, null, null, null, null);

        Timestamp ts = new Timestamp(System.currentTimeMillis());


        var newInventory_1 = new Inventory(0, b, 20, "null", ts);
        var d = orm.add(newInventory_1);

        arr = orm.listAll();
        for (Inventory Inventory : arr) {
            System.out.println("ID: " + Inventory.getId() + " \t B ID: " + Inventory.getBook().getId());
        }

        var newInventory_2 = new Inventory(d.getId(), b, 200, "null", ts);
        orm.update(newInventory_2);

        arr = orm.listAll();
        for (Inventory Inventory : arr) {
            System.out.println("ID: " + Inventory.getId() + " \t B ID: " + Inventory.getBook().getId());
        }

        // orm.delete(d.getId());
        

        arr = orm.rawQuery("SELECT * FROM " + orm.tableName + " WHERE id=1");
        for (Inventory Inventory : arr) {
            System.out.println("ID: " + Inventory.getId() + " \t B ID: " + Inventory.getBook().getId());
        }
    }

}
