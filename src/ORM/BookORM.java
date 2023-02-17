package ORM;

import java.sql.*;
import java.util.ArrayList;

import Model.Book;
import Model.Group;
import Model.Publishers;
import Model.User;

public class BookORM extends ORM<Book>{
    public BookORM() {
        tableName = "books";
    }

    @Override
    public ArrayList<Book> listAll() {
        ArrayList<Book> arr = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM `" + tableName + "`");
            while (resultSet.next()) {
                arr.add(convertFromResultSetToBook(resultSet));
            }
            
        } catch (Exception e) {
            e.printStackTrace();            
        }
        
        return arr;
    }

    @Override
    public Book add(Book Book) {
        try (var stmt = connection.prepareStatement("INSERT INTO `" + tableName + "` VALUES (null,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, Book.getTitle());
            stmt.setString(2, Book.getPath());
            stmt.setInt(3, Book.getUser().getId());
            stmt.setInt(4, Book.getGroup().getId());
            stmt.setInt(5, Book.getPublisher().getId());

            stmt.execute();

            var resultSet = stmt.getGeneratedKeys();
            if(resultSet.next()) Book.setId(resultSet.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Book;
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
    public void update(Book Book) {
        String  query = "UPDATE " + tableName + " SET title = ?, path = ?, user_id = ?, group_id = ?, publisher_id = ? WHERE id=" + Book.getId();
        try (var stmt = connection.prepareStatement(query)) {
            stmt.setString(1, Book.getTitle());
            stmt.setString(2, Book.getPath());
            stmt.setInt(3, Book.getUser().getId());
            stmt.setInt(4, Book.getGroup().getId());
            stmt.setInt(5, Book.getPublisher().getId());
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Book> rawQuery(String query) {
        ArrayList<Book> arr = new ArrayList<>();
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                arr.add(convertFromResultSetToBook(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    private Book convertFromResultSetToBook(ResultSet resultSet) throws SQLException {
        User user = new UserORM().rawQuery("SELECT * FROM users WHERE id=" + resultSet.getInt("user_id")).get(0);
        Group g = new GroupORM().rawQuery("SELECT * FROM `groups` WHERE id=" + resultSet.getInt("group_id")).get(0);
        Publishers p = new PublishersORM().rawQuery("SELECT * FROM  publishers WHERE id=" + resultSet.getInt("publisher_id")).get(0);

        return new Book(resultSet.getInt("id"), 
                        resultSet.getString(2),
                        resultSet.getString(3),
                        user,
                        g,
                        p
                        );
    }
    
    public static void main(String[] args) {
        ArrayList<Book> Books = new ArrayList<>();
        BookORM BookORM = new BookORM();

        User u = new User(2, null, null, null, null, null, null, null, null);
        Group g = new Group(1, null);
        Publishers p = new Publishers(1, null, null);

        var newBook_1 = new Book(0, "title", "path", u, g, p);
        var d = BookORM.add(newBook_1);

        Books = BookORM.listAll();
        for (Book Book : Books) {
            System.out.println("ID: " + Book.getId() + " \t Name: " + Book.getTitle());
        }

        var newBook_2 = new Book(d.getId(), "Book Update Testing", "ldflksdjfkl", u, g, p);
        BookORM.update(newBook_2);

        Books = BookORM.listAll();
        for (Book Book : Books) {
            System.out.println("ID: " + Book.getId() + " \t Name: " + Book.getTitle());
        }

        // BookORM.delete(d.getId());
        

        // Books = BookORM.rawQuery("SELECT * FROM " + BookORM.tableName + " WHERE id=1");
        // for (Book Book : Books) {
        //     System.out.println("ID: " + Book.getId() + " \t Name: " + Book.getBookName());
        // }
    }

}
