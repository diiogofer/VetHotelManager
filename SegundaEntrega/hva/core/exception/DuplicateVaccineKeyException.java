package hva.core.exception;

public class DuplicateVaccineKeyException extends Exception {
  public DuplicateVaccineKeyException (String key) {
    super("Vaccine with key already exists: " + key);
  }
}
