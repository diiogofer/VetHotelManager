package hva.core.exception;

public class UnknownSpeciesException extends Exception{
  public UnknownSpeciesException(String id) {
    super(id);
  }
}
