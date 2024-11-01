package hva.core;

public class Animal extends Identified {
    String _name;
    Species _species;
    Habitat _habitat;

    Animal(String identifier, String animalName, Species species, Habitat habitat) {
      super(identifier);
      _name = animalName;
      _species = species;
      _habitat = habitat;
    }
    
    boolean changeHabitat(Habitat habitat) {
      if(_habitat != null) {
        if(_habitat.equals(habitat)) return false;
        _habitat.removeAnimal(this);
      }
      _habitat = habitat;
      _habitat.addAnimal(this);
      return true;
    }

    int calculateSatisfaction() {
      int same = _habitat.countSpecies(_species);
      int population = _habitat.countPopulation();
      int different = population - same;
      int area = _habitat.getArea();
      int adequacy = _habitat.getAdequacy(_species);
      return 20 + 3 * same - 2 * different + area / population + adequacy;
    }
    
    Species getSpecies() {
      return _species;
    }

    public String toString() {
      return "ANIMAL|" + getId() + "|" + _name + "|" + _species.getId() + "|" + 
        healthLogToString() + "|" + _habitat.getId();
      }
      // TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      private String healthLogToString() {
        return "VOID";
      }
}
