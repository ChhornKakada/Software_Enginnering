package Exception;

public class InvalidBookException extends RuntimeException {

  public InvalidBookException() {
    super("Book must not be null.");
  }
  
}
