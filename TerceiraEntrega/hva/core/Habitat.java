package hva.core;

import java.util.*;

public class Habitat extends Identified{
  private int _area;
  private Map<String, Animal> _animalMap = new HashMap<>();
  private Map<String, SpeciesAdequacy> _adequacies = new HashMap<>();
  private Map<String, Tree> _treeMap = new HashMap<>();
  private Map<String, EmployeeKeeper> _keeperMap = new HashMap<>();

  Habitat(String id, String name, int area) {
    super(id, name);
    _area = area;
  }
  private <T extends Identified> void addIdentified(T identified, Map<String, T> map) {
    map.putIfAbsent(identified.getId().toLowerCase(), identified);
  }
  private <T extends Identified> void removeIdentified(T identified, Map<String, T> map) {
    map.remove(identified.getId().toLowerCase());
  }

  void addAnimal(Animal newAnimal) {
    addIdentified(newAnimal, _animalMap);
  }
  void removeAnimal(Animal animal) {
    removeIdentified(animal, _animalMap);
  }

  void addKeeper(EmployeeKeeper keeper) {
    addIdentified(keeper, _keeperMap);
  }

  void removeKeeper(EmployeeKeeper keeper) {
    removeIdentified(keeper, _keeperMap);
  }

  int getNumberKeepers() {
    return _keeperMap.size();
  }

  void addTree(Tree tree) {
    addIdentified(tree, _treeMap);
  }
  void removeTree(Tree tree) {
    removeIdentified(tree, _treeMap);
  }

  public List<Tree> getAllTrees() {
    List<Tree> list = new ArrayList<>(_treeMap.values());
    Collections.sort(list);
    return Collections.unmodifiableList(list);
  }

  public List<Animal> getAllAnimals() {
    List<Animal> list = new ArrayList<>(_animalMap.values());
    Collections.sort(list);
    return Collections.unmodifiableList(list);
  }

  public String toString() {
    return "HABITAT|" + getId() + "|" + getName() + "|" + _area + "|" + _treeMap.size();
  }

  int countSpecies(Species species) {
    int counter = 0;
    for(Animal a : _animalMap.values()) {
      if(a.getSpecies().equals(species)) counter++;
    }
    return counter;
  }
  int countPopulation() {
    return _animalMap.size();
  }

  double calculateEffort() {
    double effort = _area + 3 * countPopulation();
    for(Tree tree : _treeMap.values()) {
      effort += tree.calculateEffort();
    }
    return effort;
  }

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
