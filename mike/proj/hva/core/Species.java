package hva.core;

import java.util.Map;
import java.util.HashMap;

/**
 * Represents a species in the Zoo Hotel.
 * Extends the {@link Identified} class.
 */
public class Species extends Identified {

  /** A map that holds the animals belonging to this species, keyed by their ID. */
  private Map<String, Animal> _animalMap = new HashMap<>();

  /** A map that holds the veterinarians responsible for this species, keyed by their ID. */
  private Map<String, EmployeeVet> _vetMap = new HashMap<>();

  /**
   * Constructs a new species with the given ID and name.
   * 
   * @param id the unique ID of the species
   * @param name the name of the species
   */
  Species(String id, String name) {
    super(id, name);
  }

  /**
   * Adds an identified object (animal or vet) to the specified map.
   * 
   * @param <T> the type of the identified object
   * @param identified the identified object to add
   * @param map the map to which the object should be added
   */
  private <T extends Identified> void addIdentified(T identified, Map<String, T> map) {
    map.putIfAbsent(identified.getId().toLowerCase(), identified);
  }

  /**
   * Removes an identified object from the specified map by its ID.
   * 
   * @param <T> the type of the identified object
   * @param id the ID of the identified object to remove
   * @param map the map from which the object should be removed
   */
  private <T extends Identified> void removeIdentified(String id, Map<String, T> map) {
    map.remove(id.toLowerCase());
  }

  /**
   * Adds a veterinarian to this species.
   * 
   * @param vet the veterinarian to add
   */
  void addVet(EmployeeVet vet) {
    addIdentified(vet, _vetMap);
  }

  /**
   * Removes a veterinarian from this species by their ID.
   * 
   * @param vet the veterinarian to remove
   */
  void removeVet(Employee vet) {
    removeIdentified(vet.getId(), _vetMap);
  } 

  /**
   * Returns the number of veterinarians responsible for this species.
   * 
   * @return the number of veterinarians
   */
  int getNumberVet(){
    return _vetMap.size();
  }

  /**
   * Returns the number of animals belonging to this species.
   * 
   * @return the animal population of this species
   */
  int getPopulation(){
    return _animalMap.size();
  }

  /**
   * Adds an animal to this species.
   * 
   * @param newAnimal the animal to add
   */
  void addAnimal(Animal newAnimal) {
    addIdentified(newAnimal, _animalMap);
  }
  
  /**
   * Compares this species to another species for equality, considering both ID and name.
   * 
   * @param species the species to compare
   * @return true if both species have the same ID and name, ignoring case; false otherwise
   */
  boolean equals(Species species) {
    return ((this.getId().toLowerCase()).equals(species.getId().toLowerCase()) &&
      (this.getName().toLowerCase()).equals(species.getName().toLowerCase()));
  }
}
