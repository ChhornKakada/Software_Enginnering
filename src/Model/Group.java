package Model;

import Exception.InvalidGroupException;

public class Group extends Model {
  private String groupName;

  public Group(int id, String groupName) {
    super(id);
    this.groupName = groupName;
  }

  public String getgroupName() {
    return groupName;
  }

  public void setgroupName(String groupName) {
        if (groupName.isBlank() || groupName == null) {
          throw new InvalidGroupException();
        }
        this.groupName = groupName;
    }
}
