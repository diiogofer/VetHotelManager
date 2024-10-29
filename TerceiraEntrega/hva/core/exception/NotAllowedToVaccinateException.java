package hva.core.exception;

public class NotAllowedToVaccinateException extends Exception{
  public NotAllowedToVaccinateException (String id) {
    super(id);
  }
}
