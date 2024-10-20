package hva.core;

import java.util.*;

public class Habitat extends Identified implements Responsibility {
  private String _name;
  private int _area;
  private Map<String, Animal> _animalMap = new HashMap<>();
  private Map<String, SpeciesAdequacy> _adequacies = new HashMap<>();
  private Set<Tree> _treeSet = new HashSet<>();

  Habitat(String id, String name, int area) {
    super(id);
    _name = name;
    _area = area;
  }
  void addAnimal(Animal newAnimal) {
    _animalMap.putIfAbsent(newAnimal.getId().toLowerCase(), newAnimal);
  }
  void removeAnimal(Animal animal) {
    _animalMap.remove(animal.getId().toLowerCase());
  }

  void addTree(Tree tree) {_treeSet.add(tree);}
  void removeTree(Tree tree) {_treeSet.remove(tree);}

  public List<Tree> getAllTrees() {
    List<Tree> list = new ArrayList<>(_treeSet);
    Collections.sort(list);
    return Collections.unmodifiableList(list);
  } 

  public String toString() {
    return "HABITAT|" + getId() + "|" + _name + "|" + _area + "|" + _treeSet.size();
  }

  // int same = _habitat.countSpecies(_species);
  // int population = _habitat.countPopulation();
  // int area = _habitat.getArea();
  // int adequacy = _habitat.getAdequacy(_species);
  int countSpecies(Species species) {
    int counter = 0;
    for(Animal a : _animalMap.values()) {
      if(a.getSpecies().equals(species)) counter++;
    }
    return counter;
  }
  int countPopulation() {return _animalMap.size();}
  int getArea() {return _area;}
  boolean setArea(int area) {
    if(area == _area) return false;
    _area = area;
    return true;
  }
  int getAdequacy(Species species) {
    SpeciesAdequacy adequacy =_adequacies.get(species.getId().toLowerCase());
    return adequacy == null ? SpeciesAdequacy.NEUTRAL.getValue() : adequacy.getValue();
  }
  boolean setSpeciesAdequacy(String speciesId, SpeciesAdequacy adequacy) {
    SpeciesAdequacy oldAdequacy = _adequacies.get(speciesId.toLowerCase());
    if(oldAdequacy != null && oldAdequacy == adequacy) return false;
    _adequacies.put(speciesId.toLowerCase(), adequacy);
    return true;
  }
}
