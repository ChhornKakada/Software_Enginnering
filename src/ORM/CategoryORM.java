package ORM;

import java.sql.*;
import java.util.ArrayList;

import Model.Category;

public class CategoryORM extends ORM<Category> {
    public CategoryORM() {
        tableName = "categories";
    }

    @Override
    public ArrayList<Category> listAll() {
        ArrayList<Category> arr = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM `" + tableName + "`");
            while (resultSet.next()) {
                arr.add(convertFromResultSetToCategory(resultSet));
            }
            
        } catch (Exception e) {
            e.printStackTrace();            
        }
        
        return arr;
    }

    @Override
    public Category add(Category Category) {
        try (var stmt = connection.prepareStatement("INSERT INTO `" + tableName + "` VALUES(null,?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, Category.getCategoryName());
            stmt.execute();

            var resultSet = stmt.getGeneratedKeys();
            if(resultSet.next()) Category.setId(resultSet.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Category;
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
    public void update(Category Category) {
        String  query = "UPDATE " + tableName + " SET Categoryname = ? WHERE id=" + Category.getId();
        try (var stmt = connection.prepareStatement(query)) {
            stmt.setString(1, Category.getCategoryName());
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Category> rawQuery(String query) {
        ArrayList<Category> arr = new ArrayList<>();
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                arr.add(convertFromResultSetToCategory(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    private Category convertFromResultSetToCategory(ResultSet resultSet) throws SQLException {
        return new Category(resultSet.getInt("id"), resultSet.getString(2));
    }
    
    public static void main(String[] args) {
        ArrayList<Category> Categorys = new ArrayList<>();
        CategoryORM CategoryORM = new CategoryORM();

        var newCategory_1 = new Category(0, "Testing Category");
        var d = CategoryORM.add(newCategory_1);

        Categorys = CategoryORM.listAll();
        for (Category Category : Categorys) {
            System.out.println("ID: " + Category.getId() + " \t Name: " + Category.getCategoryName());
        }

        var newCategory_2 = new Category(d.getId(), "Update Category Testing");
        CategoryORM.update(newCategory_2);

        Categorys = CategoryORM.listAll();
        for (Category Category : Categorys) {
            System.out.println("ID: " + Category.getId() + " \t Name: " + Category.getCategoryName());
        }

        // CategoryORM.delete(d.getId());
        

        // Categorys = CategoryORM.rawQuery("SELECT * FROM " + CategoryORM.tableName + " WHERE id=1");
        // for (Category Category : Categorys) {
        //     System.out.println("ID: " + Category.getId() + " \t Name: " + Category.getCategoryName());
        // }
    }

}
