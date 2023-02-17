package exceptions;

public class InvalidUserException extends RuntimeException {
  public InvalidUserException() {
    super("Username cannot be null or empty.");
  }
}
