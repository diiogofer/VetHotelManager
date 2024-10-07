package hva.core.exception;

public class UnknownSpeciesKeyException extends Exception{
  public UnknownSpeciesKeyException(String key) {
    super("No species with key: " + key);
  }
}
