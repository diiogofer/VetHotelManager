package hva.core.exception;

public class UnknownEmployeeException extends Exception {
  public UnknownEmployeeException(String id) {
    super(id);
  }
}
