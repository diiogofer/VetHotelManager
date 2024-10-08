package hva.core.exception;

public class UnknownFieldException extends Exception{
  public UnknownFieldException (String message, Throwable cause) {
    super(message, cause);
  }
  public UnknownFieldException (String message) {
    super(message);
  }
}
