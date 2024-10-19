package hva.core.exception;

public class DuplicateHabitatException extends Exception{
    public DuplicateHabitatException(String identifier){
        super(identifier);
    }
}
