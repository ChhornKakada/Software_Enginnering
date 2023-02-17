package Exception;

public class RoleException extends RuntimeException{
  public RoleException() {
    super("Role must not be null ot blank.");
  }
}
