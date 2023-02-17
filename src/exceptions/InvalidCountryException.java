package exceptions;

public class InvalidCountryException extends RuntimeException {
  public InvalidCountryException() {
    super("Country must not be blank or null.");
  }
}
