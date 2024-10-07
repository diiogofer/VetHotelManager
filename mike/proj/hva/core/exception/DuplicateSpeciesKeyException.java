package hva.core.exception;

public class DuplicateSpeciesKeyException extends Exception{
  public DuplicateSpeciesKeyException (String key) {
    super("Species with key already exists: " + key);
  }
}
