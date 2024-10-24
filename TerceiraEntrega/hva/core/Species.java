package hva.core;

import java.util.Map;
import java.util.HashMap;

public class Species extends Identified {
  private int _nVet = 0;
  private Map<String, Animal> _animalMap = new HashMap<>();

  Species(String id, String name) {
    super(id, name);
  }
  void addVet() {_nVet++;}
  void removeVet() {_nVet--;}
  int getNumberVet(){return _nVet;}
  int getPopulation(){
    return _animalMap.size();
  }
  void addAnimal(Animal newAnimal) {
    _animalMap.putIfAbsent(newAnimal.getId().toLowerCase(), newAnimal);
  }
  boolean equals(Species species) {
    return ((this.getId().toLowerCase()).equals(species.getId().toLowerCase()) &&
      (this.getName().toLowerCase()).equals(species.getName().toLowerCase()));
  }
}
