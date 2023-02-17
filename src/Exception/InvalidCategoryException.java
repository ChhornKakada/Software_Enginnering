package Exception;

public class InvalidCategoryException extends RuntimeException {

  public InvalidCategoryException() {
    super("Category must not be null.");
  }
  
}
