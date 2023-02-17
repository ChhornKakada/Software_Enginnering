package Exception;

public class InvalidPublisherException extends RuntimeException {
  public InvalidPublisherException() {
    super("Publishher must not blank.");
  }
}
