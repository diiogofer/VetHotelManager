package hva.core.exception;

public class UnknownAnimalException extends Exception {
    public UnknownAnimalException(String id) {
        super(id);
    }
}
