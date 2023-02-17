package exceptions;

public class InvalidCityException extends RuntimeException {
  public InvalidCityException() {
    super("City must not be null ot blank.");
  }
}

