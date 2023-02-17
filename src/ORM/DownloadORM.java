package ORM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Download;

public class DownloadORM extends ORM<Download> {
  public DownloadORM() {
    tableName = "downloads";
  }

  @Override
  public ArrayList<Download> listAll() {
    ArrayList<Download> arr = new ArrayList<>();
    try (var stmt = connection.createStatement()) {
      ResultSet resultSet = stmt.executeQuery("SELECT * FROM `" + tableName + "`");
      while (resultSet.next()) {
        arr.add(convertFromResultSetToDownload(resultSet));
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return arr;
  }

  @Override
  public Download add(Download data) {
    try (var stmt = connection.prepareStatement("INSERT INTO `" + tableName + "` VALUES(null,?,?,?)",
        Statement.RETURN_GENERATED_KEYS)) {
      stmt.setInt(1, data.getUser().getId());
      stmt.setInt(2, data.getBook().getId());
      stmt.setTimestamp(3, data.getDownloadAt());

      stmt.execute();

      var resultSet = stmt.getGeneratedKeys();
      if (resultSet.next())
        data.setId(resultSet.getInt(1));
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
  public void update(Download Download) {
    String query = "UPDATE " + tableName + " SET user_id = ?, book_id = ?, downloaded_at = ? WHERE id="
        + Download.getId();
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, Download.getUser().getId());
      stmt.setInt(2, Download.getBook().getId());
      stmt.setTimestamp(3, Download.getDownloadAt());
      stmt.execute();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public ArrayList<Download> rawQuery(String query) {
    ArrayList<Download> arr = new ArrayList<>();
    try (var statement = connection.createStatement()) {
      var resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        arr.add(convertFromResultSetToDownload(resultSet));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return arr;
  }

  private Download convertFromResultSetToDownload(ResultSet resultSet) throws SQLException {
    var user = new UserORM().rawQuery("SELECT * FROM users WHERE id=" + resultSet.getInt("user_id")).get(0);
    var book = new BookORM().rawQuery("SELECT * FROM books WHERE id=" + resultSet.getInt("book_id")).get(0);

    return new Download(resultSet.getInt("id"),
        user,
        book,
        resultSet.getTimestamp(4));
  }

  // public static void main(String[] args) {
  //   ArrayList<Download> arr = new ArrayList<>();
  //   DownloadORM orm = new DownloadORM();

  //   Book b = new Book(1, null, null, null, null, null);
  //   User u = new User(2, null, null, null, null, null, null, null, null);

  //   Timestamp ts = new Timestamp(System.currentTimeMillis());

  //   var newDownload_1 = new Download(0, u, b, ts);
  //   var d = orm.add(newDownload_1);

  //   arr = orm.listAll();
  //   for (Download Download : arr) {
  //     System.out.println("ID: " + Download.getId() + " \t B ID: " + Download.getBook().getId());
  //   }

  //   User u1 = new User(3, null, null, null, null, null, null, null, null);

  //   var newDownload_2 = new Download(d.getId(), u1, b, ts);
  //   orm.update(newDownload_2);

  //   arr = orm.listAll();
  //   for (Download Download : arr) {
  //     System.out.println("ID: " + Download.getId() + " \t B ID: " + Download.getBook().getId());
  //   }

  //   // orm.delete(d.getId());

  //   arr = orm.rawQuery("SELECT * FROM " + orm.tableName + " WHERE id=1");
  //   for (Download Download : arr) {
  //     System.out.println("ID: " + Download.getId() + " \t B ID: " + Download.getBook().getId());
  //   }
  // }

}
