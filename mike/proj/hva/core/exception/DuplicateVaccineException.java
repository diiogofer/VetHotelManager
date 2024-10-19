package hva.core.exception;

public class DuplicateVaccineException extends Exception {
  public DuplicateVaccineException(String id) {
    super(id);
  }
}
