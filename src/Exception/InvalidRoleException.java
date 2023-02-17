package Exception;

public class InvalidRoleException extends RuntimeException {
  public InvalidRoleException() {
    super("Role must not blank.");
  }
}
