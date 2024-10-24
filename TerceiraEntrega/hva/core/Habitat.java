package hva.core;

import java.util.*;

/**
 * Represents a habitat in the veterinary hotel.
 */
public class Habitat extends Identified {

  /** The area of the habitat in square units. */
  private int _area;

  /** A map of animals currently in the habitat, keyed by their IDs. */
  private Map<String, Animal> _animalMap = new HashMap<>();

  /** A map of species adequacies for the habitat, keyed by species ID. */
  private Map<String, SpeciesAdequacy> _adequacies = new HashMap<>();

  /** A map of trees in the habitat, keyed by tree ID. */
  private Map<String, Tree> _treeMap = new HashMap<>();

  /** A map of keepers responsible for the habitat, keyed by keeper ID. */
  private Map<String, EmployeeKeeper> _keeperMap = new HashMap<>();

  /**
   * Constructs a habitat with a specified ID, name, and area.
   * 
   * @param id The unique identifier of the habitat.
   * @param name The name of the habitat.
   * @param area The area of the habitat.
   */
  Habitat(String identifier, String name, int area) {
    super(identifier, name);
    _area = area;
  }

  /**
   * Adds an identified entity (like an animal, tree, or keeper) to the provided map.
   * 
   * @param <T> The type of identified entity.
   * @param identified The entity to add.
   * @param map The map where the entity will be added.
   */
  private <T extends Identified> void addIdentified(T identified, Map<String, T> map) {
    map.putIfAbsent(identified.getId().toLowerCase(), identified);
  }

  /**
   * Removes an identified entity (like an animal, tree, or keeper) from the provided map.
   * 
   * @param <T> The type of identified entity.
   * @param identified The entity to remove.
   * @param map The map from which the entity will be removed.
   */
  private <T extends Identified> void removeIdentified(T identified, Map<String, T> map) {
    map.remove(identified.getId().toLowerCase());
  }

  /**
   * Adds an animal to the habitat.
   * 
   * @param newAnimal The animal to be added.
   */
  void addAnimal(Animal newAnimal) {
    addIdentified(newAnimal, _animalMap);
  }

  /**
   * Removes an animal from the habitat.
   * 
   * @param animal The animal to be removed.
   */
  void removeAnimal(Animal animal) {
    removeIdentified(animal, _animalMap);
  }

  /**
   * Adds a zookeeper responsible for the habitat.
   * 
   * @param keeper The keeper to be added.
   */
  void addKeeper(EmployeeKeeper keeper) {
    addIdentified(keeper, _keeperMap);
  }

  /**
   * Removes a zookeeper from the habitat.
   * 
   * @param keeper The keeper to be removed.
   */
  void removeKeeper(EmployeeKeeper keeper) {
    removeIdentified(keeper, _keeperMap);
  }

  /**
   * Gets the number of zookeepers assigned to the habitat.
   * 
   * @return The number of zookeepers.
   */
  int getNumberKeepers() {
    return _keeperMap.size();
  }

  /**
   * Adds a tree to the habitat.
   * 
   * @param tree The tree to be added.
   */
  void addTree(Tree tree) {
    addIdentified(tree, _treeMap);
  }

  /**
   * Removes a tree from the habitat.
   * 
   * @param tree The tree to be removed.
   */
  void removeTree(Tree tree) {
    removeIdentified(tree, _treeMap);
  }

  /**
   * Returns a list of all trees in the habitat, sorted by their identifiers.
   * 
   * @return An unmodifiable list of all trees in the habitat.
   */
  public List<Tree> getAllTrees() {
    List<Tree> list = new ArrayList<>(_treeMap.values());
    Collections.sort(list);
    return Collections.unmodifiableList(list);
  }

  /**
   * Returns a list of all animals in the habitat, sorted by their identifiers.
   * 
   * @return An unmodifiable list of all animals in the habitat.
   */
  public List<Animal> getAllAnimals() {
    List<Animal> list = new ArrayList<>(_animalMap.values());
    Collections.sort(list);
    return Collections.unmodifiableList(list);
  }

  /**
   * Returns a string representation of the habitat in the format:
   * <pre>
   * "HABITAT|id|name|area|numberOfTrees"
   * </pre>
   * 
   * @return A string representation of the habitat.
   */
  @Override
  public String toString() {
    return "HABITAT|" + getId() + "|" + getName() + "|" + _area + "|" + _treeMap.size();
  }

  /**
   * Counts the number of animals in the habitat that belong to a specific species.
   * 
   * @param species The species to count.
   * @return The number of animals of the specified species.
   */
  int countSpecies(Species species) {
    int counter = 0;
    for(Animal animal : _animalMap.values()) {
      if(animal.getSpecies().equals(species)) counter++;
    }
    return counter;
  }

  /**
   * Counts the total number of animals in the habitat.
   * 
   * @return The population of the habitat.
   */
  int countPopulation() {
    return _animalMap.size();
  }

  /**
   * Calculates the total effort required to maintain the habitat.
   * The effort is based on the area, the population of the habitat, and the trees within it.
   * 
   * @return The calculated effort.
   */
  double calculateEffort() {
    double effort = _area + 3 * countPopulation();
    for(Tree tree : _treeMap.values()) {
      effort += tree.calculateEffort();
    }
    return effort;
  }

  /**
   * Gets the area of the habitat.
   * 
   * @return The area of the habitat.
   */
  int getArea() {
    return _area;
  }

  /**
   * Sets the area of the habitat.
   * 
   * @param area The new area of the habitat.
   * @return true if the area was changed, false if it remained the same.
   */
  boolean setArea(int area) {
    if(area == _area) {
      return false;
    }
    _area = area;
    return true;
  }

  /**
   * Gets the adequacy of the habitat for a specific species.
   * 
   * @param species The species to check adequacy for.
   * @return The adequacy value for the species.
   */
  int getAdequacy(Species species) {
    SpeciesAdequacy adequacy =_adequacies.get(species.getId().toLowerCase());
    return adequacy == null ? SpeciesAdequacy.NEUTRAL.getValue() : adequacy.getValue();
  }

  /**
   * Sets the adequacy of the habitat for a specific species.
   * 
   * @param speciesId The ID of the species.
   * @param adequacy The adequacy value to set.
   * @return true if the adequacy was changed, false if it remained the same.
   */
  boolean setSpeciesAdequacy(String speciesId, SpeciesAdequacy adequacy) {
    SpeciesAdequacy oldAdequacy = _adequacies.get(speciesId.toLowerCase());
    if(oldAdequacy != null && oldAdequacy == adequacy) return false;
    _adequacies.put(speciesId.toLowerCase(), adequacy);
    return true;
  }
}
