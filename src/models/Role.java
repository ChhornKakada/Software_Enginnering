package models;

import exceptions.InvalidRoleException;

public class Role extends Model {
  private String role;

  public Role(int id, String role) {
    super(id);
    this.setRole(role);
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    if (role == null || role.isBlank())
      throw new InvalidRoleException();
    this.role = role;
  }
}
