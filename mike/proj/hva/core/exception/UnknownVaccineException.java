package hva.core.exception;

public class UnknownVaccineException extends Exception {
  public UnknownVaccineException(String id) {
    super(id);
  }
}
