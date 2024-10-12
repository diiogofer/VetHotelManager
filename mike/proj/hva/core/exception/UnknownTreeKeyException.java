package hva.core.exception;

public class UnknownTreeKeyException extends Exception{
  public UnknownTreeKeyException(String key) {
    super("No tree with key: " + key);
  }
}
