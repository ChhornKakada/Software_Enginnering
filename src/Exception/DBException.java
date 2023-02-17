package Exception;

public class DBException extends RuntimeException{
    public DBException(Throwable e) {
        super(e);
    }
}
