package hva.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents an animal in the zoo hotel.
 */
public class Animal extends Identified {
  
  /** The species to which the animal belongs. */
  Species _species;

  /** The habitat where the animal resides. */
  Habitat _habitat;

  /** List of vaccine events associated with the animal. */
  private List<VaccineEvent> _vaccineEventList = new ArrayList<>();


  /**
   * Constructs an Animal with the given identifier, name, species, and habitat.
   * 
   * @param animalId The unique identifier of the animal.
   * @param name The name of the animal.
   * @param species The species of the animal.
   * @param habitat The habitat where the animal resides.
   */
  Animal(String animalId, String name, Species species, Habitat habitat) {
    super(animalId, name);
    _species = species;
    _habitat = habitat;
  }

  /**
   * Changes the animal's habitat.
   * 
   * @param habitat The new habitat for the animal.
   * @return true if the habitat was changed, false if the new habitat is the same as the current one.
   */
  boolean changeHabitat(Habitat habitat) {
    if(_habitat != null) {
      if(_habitat.equals(habitat)) { 
        return false; 
      }
      _habitat.removeAnimal(this);
    }
    _habitat = habitat;
    _habitat.addAnimal(this);
    return true;
  }

  /**
   * Returns a string representation of the animal in the format:
   * "ANIMAL|id|name|speciesId|vaccineEvents|habitatId".
   * 
   * @return A string representation of the animal.
   */
  public String toString() {
    return "ANIMAL|" + getId() + "|" + getName() + "|" + _species.getId() + "|" + 
      vaccineEventsToString() + "|" + _habitat.getId();
  }

  /**
   * Converts the vaccine events to a string representation.
   * 
   * @return A string of vaccine events or "VOID" if no events exist.
   */
  private String vaccineEventsToString() {
    if(_vaccineEventList.size() == 0) return "VOID";

    StringBuilder damageString = new StringBuilder();

    for (VaccineEvent event : _vaccineEventList) {
        damageString.append(event.toCategory()).append(",");
    }
    // Remove the last comma
    damageString.setLength(damageString.length() - 1);
    return damageString.toString();
  }

  /**
   * Calculates the satisfaction level of the animal.
   * The formula used is:
   * <pre>
   * satisfaction = 20 + 3 * sameSpecies - 2 * differentSpecies + area / population + adequacy
   * </pre>
   * 
   * @return The calculated satisfaction level of the animal.
   */
  double calculateSatisfaction() {
    double same = _habitat.countSpecies(_species) - 1;
    double population = _habitat.countPopulation();
    double different = population - same - 1;
    double area = _habitat.getArea();
    double adequacy = _habitat.getAdequacy(_species);
    return 20 + 3 * same - 2 * different + area / population + adequacy;
  }

  /**
   * Returns the species of the animal.
   * 
   * @return The species of the animal.
   */
  Species getSpecies() {
    return _species;
  }

  /**
   * Adds a new vaccine event to the animal's vaccine history.
   * 
   * @param event The vaccine event to be added.
   */
  void addVaccineEvent(VaccineEvent event) {
    _vaccineEventList.add(event);
  }

  /**
   * Returns an unmodifiable list of all vaccine events associated with the animal.
   * 
   * @return A list of vaccine events.
   */
  List<VaccineEvent> getAllVaccineEvents() {
    return Collections.unmodifiableList(_vaccineEventList);
  }
}
