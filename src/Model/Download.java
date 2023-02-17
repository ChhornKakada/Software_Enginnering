package Model;

import java.sql.Timestamp;

public class Download extends Model {
  private User user;
  private Book book;
  private Timestamp downloadAt;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Download(int id, User user, Book book, Timestamp downloadAt) {
    super(id);
    this.user = user;
    this.book = book;
    this.downloadAt = downloadAt;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public Timestamp getDownloadAt() {
    return downloadAt;
  }

  public void setDownloadAt(Timestamp downloadAt) {
    this.downloadAt = downloadAt;
  }

}
