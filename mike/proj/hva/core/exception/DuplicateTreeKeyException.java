package hva.core.exception;

public class DuplicateTreeKeyException extends Exception {
  public DuplicateTreeKeyException (String key) {
    super("Tree with key already exists: " + key);
  }
}
