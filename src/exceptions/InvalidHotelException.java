package exceptions;

public class InvalidHotelException extends RuntimeException{
  public InvalidHotelException() {
    super("Hotel must not be null.");
  }
}
