package hva.core;

import java.util.*;

/**
 * Represents a vaccine that is suitable for specific species.
 */
public class Vaccine extends Identified{

  /** Map containing species that the vaccine is suitable for, identified by their IDs. */
  private Map<String, Species> _speciesMap;

  /** List of vaccine events where this vaccine has been applied. */
  List<VaccineEvent> _vaccineEventList = new ArrayList<>();

  /**
   * Constructs a new Vaccine with the specified ID, name, and the species it is good for.
   * 
   * @param id the unique identifier of the vaccine
   * @param name the name of the vaccine
   * @param map a map of species that the vaccine is suitable for
   */
  Vaccine(String id, String name, Map<String, Species> map) {
    super(id, name);
    _speciesMap = map;
  }

  /**
   * Returns a string representation of the species this vaccine is suitable for.
   * 
   * @return a string containing the IDs of the species, separated by commas
   */
  private String speciesToString() {
    StringBuilder str = new StringBuilder();
    List<Species> list = new ArrayList<>(_speciesMap.values());
    Collections.sort(list);
    
    for ( Species s : list) {
        str.append(s.getId()).append(",");
    }

    // Check if the string builder is not empty, then remove the last comma
    if (str.length() > 0) {
        str.deleteCharAt(str.length() - 1);  // Remove the trailing comma
    }

    return str.toString();
  }

  /**
   * Returns a string representation of the vaccine, including its ID, name,
   * number of times it has been used, and the species it is suitable for.
   * 
   * @return a string representing the vaccine
   */
  public String toString() {
    String species = speciesToString();
    return species.length() == 0 ? 
      "VACINA|" + getId() + "|" + getName() + "|" + _vaccineEventList.size() :
      "VACINA|" + getId() + "|" + getName() + "|" + _vaccineEventList.size() + "|" + speciesToString();
  }

  /**
   * Checks if the vaccine is suitable for a given species.
   * 
   * @param species the species to check
   * @return true if the vaccine is suitable for the species, false otherwise
   */
  boolean isGoodForSpecies(Species species) {
    return _speciesMap.containsKey(species.getId().toLowerCase());
  }

  /**
   * Returns an unmodifiable list of species that this vaccine is suitable for.
   * 
   * @return a list of species the vaccine is suitable for
   */
  List<Species> getGoodSpecies() {
    List<Species> list = new ArrayList<>(_speciesMap.values());
    return Collections.unmodifiableList(list);
  }

  /**
   * Adds a vaccine event to the list of events where this vaccine has been used.
   * 
   * @param event the vaccine event to add
   */
  void addVaccineEvent(VaccineEvent event) {
    _vaccineEventList.add(event);
  }
}
