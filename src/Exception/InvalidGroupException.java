package Exception;

public class InvalidGroupException extends RuntimeException {

  public InvalidGroupException() {
    super("Group must not be null.");
  }
  
}
