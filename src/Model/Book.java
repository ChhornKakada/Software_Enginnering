package Model;

import Exception.InvalidBookException;

public class Book extends Model {
  private String title;
  private String path;
  private User user;
  private Group group;
  private Publishers publisher;

  public Book(int id, String title, String path, User user, Group group, Publishers publisher) {
    super(id);
    this.title = title;
    this.path = path;
    this.user = user;
    this.group = group;
    this.publisher = publisher;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    if (title.isBlank() || title == null) {
      throw new InvalidBookException();
    }
    this.title = title;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  public Publishers getPublisher() {
    return publisher;
  }

  public void setPublisher(Publishers publisher) {
    this.publisher = publisher;
  }

  public String getBookName() {
    return null;
  }
}
