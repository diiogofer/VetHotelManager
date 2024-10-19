package hva.core.exception;

public class DuplicateTreeException extends Exception{
    public DuplicateTreeException(String id) {
        super(id);
    }
}