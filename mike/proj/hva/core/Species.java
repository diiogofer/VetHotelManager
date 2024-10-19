package hva.core;

import java.util.Map;
import java.util.HashMap;

public class Species extends Identified {
  final String _name;
  private Map<String, Animal> _animalMap = new HashMap<>();

  Species(String id, String name) {
    super(id);
    _name = name;
  }
  String getName() {return _name;}
  void addAnimal(Animal newAnimal) {
    _animalMap.putIfAbsent(newAnimal.getId().toLowerCase(), newAnimal);
  }
}
