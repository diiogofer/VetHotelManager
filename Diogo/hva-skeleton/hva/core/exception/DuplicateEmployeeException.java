package hva.core.exception;

public class DuplicateEmployeeException extends Exception {
    public DuplicateEmployeeException(String id) {
        super(id);
    }
}