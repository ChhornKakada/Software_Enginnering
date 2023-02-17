package ORM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Book;
import Model.BookCategory;
import Model.Category;

public class BookCategoryORM extends ORM<BookCategory>{
    public BookCategoryORM() {
        tableName = "books_categories";
    }

    @Override
    public ArrayList<BookCategory> listAll() {
        ArrayList<BookCategory> arr = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM `" + tableName + "`");
            while (resultSet.next()) {
                arr.add(convertFromResultSetToBookCategory(resultSet));
            }
            
        } catch (Exception e) {
            e.printStackTrace();            
        }
        
        return arr;
    }

    @Override
    public BookCategory add(BookCategory BookCategory) {
        try (var stmt = connection.prepareStatement("INSERT INTO `" + tableName + "` VALUES(?,?)")) {
            stmt.setInt(1, BookCategory.getBook().getId());
            stmt.setInt(2, BookCategory.getCategory().getId());

            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return BookCategory;
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
    public void update(BookCategory BookCategory) {
        String  query = "UPDATE " + tableName + " SET  = ? WHERE category_id=" + BookCategory.getBook().getId();
        try (var stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, BookCategory.getCategory().getId());
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<BookCategory> rawQuery(String query) {
        ArrayList<BookCategory> arr = new ArrayList<>();
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                arr.add(convertFromResultSetToBookCategory(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    private BookCategory convertFromResultSetToBookCategory(ResultSet resultSet) throws SQLException {
        Book book = new BookORM().rawQuery("SELECT * FROM books WHERE id=" + resultSet.getInt("book_id")).get(0);
        Category ca = new CategoryORM().rawQuery("SELECT * FROM categories WHERE id=" + resultSet.getInt("category_id")).get(0);

        return new BookCategory(book,
                                ca
                                );
    }
    
    // public static void main(String[] args) {
    //     ArrayList<BookCategory> BookCategorys = new ArrayList<>();
    //     BookCategoryORM BookCategoryORM = new BookCategoryORM();

    //     Book b = new 

    //     var newBookCategory_1 = new BookCategory(0, "Testing BookCategory");
    //     var d = BookCategoryORM.add(newBookCategory_1);

    //     BookCategorys = BookCategoryORM.listAll();
    //     for (BookCategory BookCategory : BookCategorys) {
    //         System.out.println("ID: " + BookCategory.getId() + " \t Name: " + BookCategory.getBookCategoryName());
    //     }

    //     var newBookCategory_2 = new BookCategory(d.getId(), "Update BookCategory Testing");
    //     BookCategoryORM.update(newBookCategory_2);

    //     BookCategorys = BookCategoryORM.listAll();
    //     for (BookCategory BookCategory : BookCategorys) {
    //         System.out.println("ID: " + BookCategory.getId() + " \t Name: " + BookCategory.getBookCategoryName());
    //     }

    //     BookCategoryORM.delete(d.getId());
        

    //     BookCategorys = BookCategoryORM.rawQuery("SELECT * FROM " + BookCategoryORM.tableName + " WHERE id=1");
    //     for (BookCategory BookCategory : BookCategorys) {
    //         System.out.println("ID: " + BookCategory.getId() + " \t Name: " + BookCategory.getBookCategoryName());
    //     }
    // } 

}
