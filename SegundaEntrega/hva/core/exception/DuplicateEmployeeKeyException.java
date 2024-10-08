package hva.core.exception;

public class DuplicateEmployeeKeyException extends Exception {
  public DuplicateEmployeeKeyException (String key) {
    super("Employee with key already exists: " + key);
  }
}
