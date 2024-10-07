package hva.core.exception;

public class DuplicateHabitatKeyException extends Exception {
  public DuplicateHabitatKeyException (String key) {
    super("Habitat with key already exists: " + key);
  }
}
