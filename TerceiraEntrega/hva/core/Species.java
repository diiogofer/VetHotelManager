package hva.core;

import java.util.Map;
import java.util.HashMap;

public class Species extends Identified {

  private Map<String, Animal> _animalMap = new HashMap<>();
  private Map<String, EmployeeVet> _vetMap = new HashMap<>();

  Species(String id, String name) {
    super(id, name);
  }

  private <T extends Identified> void addIdentified(T identified, Map<String, T> map) {
    map.putIfAbsent(identified.getId().toLowerCase(), identified);
  }
  private <T extends Identified> void removeIdentified(String id, Map<String, T> map) {
    map.remove(id.toLowerCase());
  }

  void addVet(EmployeeVet vet) {
    addIdentified(vet, _vetMap);
  }
  void removeVet(Employee vet) {
    removeIdentified(vet.getId(), _vetMap);
  } 
  int getNumberVet(){return _vetMap.size();}
  int getPopulation(){
    return _animalMap.size();
  }
  void addAnimal(Animal newAnimal) {
    addIdentified(newAnimal, _animalMap);
  }
  
  boolean equals(Species species) {
    return ((this.getId().toLowerCase()).equals(species.getId().toLowerCase()) &&
      (this.getName().toLowerCase()).equals(species.getName().toLowerCase()));
  }
}
