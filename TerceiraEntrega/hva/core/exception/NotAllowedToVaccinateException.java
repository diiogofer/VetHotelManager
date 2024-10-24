package hva.core.exception;

public class NotAllowedToVaccinateException extends Exception{
  public NotAllowedToVaccinateException (String id) {
    super("Vet not allowed to vaccinate species: " + id);
  }
}
