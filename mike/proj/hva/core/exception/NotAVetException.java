package hva.core.exception;

public class NotAVetException extends Exception {
  public NotAVetException (String id) {
    super("Employee is not a vet: "+ id);
  }
}
