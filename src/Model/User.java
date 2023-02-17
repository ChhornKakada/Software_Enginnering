package Model;

import Exception.InvalidUserException;

public class User extends Model {
  private String username;
  private String pwd;
  private Role role;
  private String token;
  private Group group;
  private String remoteAddr;
  private String forwardAddr;
  private String image;

  public User(int id, String username, String pwd, Role role, String token, Group group, 
    String remoteAddr, String forwardAddr, String image) {
    super(id);
    this.username = username;
    this.pwd = pwd;
    this.role = role;
    this.token = token;
    this.group = group;
    this.remoteAddr = remoteAddr;
    this.forwardAddr = forwardAddr;
    this.image = image;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    if (username.isBlank() || username == null) {
      throw new InvalidUserException();
    }
    this.username = username;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  public String getRemoteAddr() {
    return remoteAddr;
  }

  public void setRemoteAddr(String remoteAddr) {
    this.remoteAddr = remoteAddr;
  }

  public String getForwardAddr() {
    return forwardAddr;
  }

  public void setForwardAddr(String forwardAddr) {
    this.forwardAddr = forwardAddr;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

}
