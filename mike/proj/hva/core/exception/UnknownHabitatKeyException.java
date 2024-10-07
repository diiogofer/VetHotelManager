package hva.core.exception;

public class UnknownHabitatKeyException extends Exception{
  public UnknownHabitatKeyException(String key) {
    super("No habitat with key: " + key);
  }
  
}
