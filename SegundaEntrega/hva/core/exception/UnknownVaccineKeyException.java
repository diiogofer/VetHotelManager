package hva.core.exception;

public class UnknownVaccineKeyException extends Exception{
  public UnknownVaccineKeyException(String key) {
    super("No vaccine with key: " + key);
  }
  
}
