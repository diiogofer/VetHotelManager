package hva.core;

public class Animal extends Identified {
  String _name;
  Species _species;
  Habitat _habitat;
  Animal(String id, String name, Species species, Habitat habitat) {
    super(id);
    _name = name;
    _species = species;
    _habitat = habitat;
  }
  public String toString() {
    return "ANIMAL|" + getId() + "|" + _name + "|" + _species.getId() + "|" + 
      healthLogToString() + "|" + _habitat.getId();
  }
  // TODO
  private String healthLogToString() {
    return "VOID";
  }
}
