package hva.core.exception;

public class UnknownAnimalKeyException extends Exception{
  public UnknownAnimalKeyException(String key) {
    super("No animal with key: " + key);
  }
}
