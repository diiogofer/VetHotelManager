package hva.core.exception;

public class DuplicateAnimalKeyException extends Exception {
  public DuplicateAnimalKeyException (String key) {
    super("Animal with key already exists: " + key);
  }
}
