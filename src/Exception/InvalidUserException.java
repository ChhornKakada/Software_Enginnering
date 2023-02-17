package Exception;

public class InvalidUserException extends RuntimeException {
  public InvalidUserException() {
    super("Username must not be null.");
  }
}
