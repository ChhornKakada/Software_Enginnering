package models;

import exceptions.InvalidRoleException;
import exceptions.InvalidUserException;
import utils.MD5;

public class User extends Model {
  private String username, pass, email;
  private Role role;
  private short discount;
  private String avatar;

  public User(int id, String username, String pass, String email, Role role, short discount, String avatar) {
    super(id);
    this.setUsername(username);
    // this.pass = pass;
    this.pass = MD5.getMd5(pass);
    this.email = email;
    this.setRole(role);
    this.discount = discount;
    this.avatar = avatar;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    if (username.isBlank() || username == null)
      throw new InvalidUserException();
    this.username = username;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    if (role == null)
      throw new InvalidRoleException();
    this.role = role;
  }

  public short getDiscount() {
    return discount;
  }

  public void setDiscount(short discount) {
    if (discount >= 0 && discount <= 100)
      this.discount = discount;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

}
