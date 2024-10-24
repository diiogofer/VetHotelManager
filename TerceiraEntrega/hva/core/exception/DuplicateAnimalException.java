package hva.core.exception;

public class DuplicateAnimalException extends Exception{
  public DuplicateAnimalException(String id) {
    super(id);
  }
}
