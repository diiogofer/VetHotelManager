package hva.core;

import java.util.Map;
import java.util.HashMap;

public class Habitat extends Identified{
  private String _name;
  private int _area;
  private Map<String, Animal> _animalMap = new HashMap<>();
  
  Habitat(String id, String name, int area) {
    super(id);
    _name = name;
    _area = area;
  }
  void addAnimal(Animal newAnimal) {
    _animalMap.putIfAbsent(newAnimal.getId().toLowerCase(), newAnimal);
  }
}
