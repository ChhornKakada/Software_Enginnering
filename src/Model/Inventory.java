package Model;

import java.sql.Timestamp;

public class Inventory extends Model {
  private Book book;
  private int copies;
  private String srcurl;
  private Timestamp createAt;

  public Inventory(int id, Book book, int copies, String srcurl, Timestamp createAt) {
    super(id);
    this.book = book;
    this.copies = copies;
    this.srcurl = srcurl;
    this.createAt = createAt;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public int getCopies() {
    return copies;
  }

  public void setCopies(int copies) {
    this.copies = copies;
  }

  public String getSrcurl() {
    return srcurl;
  }

  public void setSrcurl(String srcurl) {
    this.srcurl = srcurl;
  }

  public Timestamp getCreateAt() {
    return createAt;
  }

  public void setCreateAt(Timestamp createAt) {
    this.createAt = createAt;
  }

}
