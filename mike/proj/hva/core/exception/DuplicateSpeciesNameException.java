package hva.core.exception;

public class DuplicateSpeciesNameException extends Exception {
  public DuplicateSpeciesNameException(String name) {
    super("Species with name already exists: " + name);
  }
}
