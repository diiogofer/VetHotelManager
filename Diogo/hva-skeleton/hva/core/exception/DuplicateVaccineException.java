package hva.core.exception;

public class DuplicateVaccineException extends Exception {
  
    public DuplicateVaccineException(String identifier) {
        super(identifier);
    }
}