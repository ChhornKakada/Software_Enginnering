package Model;

import Exception.InvalidRoleException;

public class Role extends Model {
  private String roleName;

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    if (roleName.isBlank() || roleName.equals(null)) {
      throw new InvalidRoleException();
    }
    this.roleName = roleName;
  }

  public Role(int id, String roleName) {
    super(id);
    this.roleName = roleName;
  }

}
