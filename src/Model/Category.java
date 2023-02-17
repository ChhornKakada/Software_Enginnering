package Model;

import Exception.InvalidCategoryException;

public class Category extends Model {
  private String categoryName;

  public Category(int id, String categoryName) {
    super(id);
    this.categoryName = categoryName;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    if (categoryName.isBlank() || categoryName == null) {
      throw new InvalidCategoryException();
    }
    this.categoryName = categoryName;
  }
}
