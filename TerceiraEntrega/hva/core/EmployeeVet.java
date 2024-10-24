package hva.core;

import java.util.*;

import hva.core.exception.UnknownResponsibilityException;

/**
 * Represents a veterinarian (EmployeeVet) in the zoo hotel.
 */
public class EmployeeVet extends Employee {
  
  /** Strategy used to calculate satisfaction for the veterinarian. */
  private EmployeeVetStrategy _strategy;

  /** Map of species that the veterinarian is responsible for, keyed by species ID. */
  private Map<String, Species> _responsibilityMap = new HashMap<>();

  /** List of vaccine events performed by the veterinarian. */
  private List<VaccineEvent> _vaccineEventList = new ArrayList<>();

  /**
   * Constructs a veterinarian with a given ID and name.
   * 
   * @param identifier The unique identifier of the veterinarian.
   * @param name The name of the veterinarian.
   */
  EmployeeVet(String identifier, String name) {
    super(identifier, name);
  }

  /**
   * Returns a string representation of the veterinarian's responsibilities (species).
   * The species are listed in the format:
   * <pre>
   * speciesId1,speciesId2,...,speciesIdN
   * </pre>
   * If there are no responsibilities, an empty string is returned.
   * 
   * @return A string of the species the veterinarian is responsible for, or an empty string if none exist.
   */
  @Override
  protected String responsibilitiesToString() {
    StringBuilder str = new StringBuilder();
    List<Species> list = new ArrayList<>(_responsibilityMap.values());
    Collections.sort(list);
    
    for (Species s : list) {
        str.append(s.getId()).append(",");
    }
    // Check if the string builder is not empty, then remove the last comma
    if (str.length() > 0) {
        str.deleteCharAt(str.length() - 1);  // Remove the last comma
    }
    return str.toString();
  }

  /**
   * Calculates the satisfaction of the veterinarian.
   * 
   * @return The calculated satisfaction level of the veterinarian.
   */
  @Override
  double calculateSatisfaction() {
    _strategy = new EmployeeVetSatisfactionStrategy();
    return _strategy.calculateSatisfaction(this);
  }

  /**
   * Returns an unmodifiable list of all species the veterinarian is responsible for.
   * 
   * @return A list of species managed by the veterinarian.
   */
  List<Species> getAllSpecies() {
    List<Species> list = new ArrayList<>(_responsibilityMap.values());
    return Collections.unmodifiableList(list);  
  }

  /**
   * Returns the employee type as a string.
   * 
   * @return The string "VET".
   */
  @Override
  protected String employeeTypeToString() {
    return "VET";
  }

  /**
   * Adds a responsibility (a species) to the veterinarian.
   * 
   * @param responsibilityId The ID of the species to be added.
   * @param hotel The hotel context used to look up the species.
   * @return true if the responsibility was added, false if it was already assigned.
   * @throws UnknownResponsibilityException if the species is unknown.
   */
  @Override
  protected boolean addResponsibility(String responsibilityId, Hotel hotel) 
    throws UnknownResponsibilityException {
    String key = responsibilityId.toLowerCase();
    if(_responsibilityMap.containsKey(key))
      return false;
    Species species = hotel.getSpecies(key);
    if(species == null) throw new UnknownResponsibilityException(responsibilityId);
    _responsibilityMap.putIfAbsent(key, species);
    species.addVet(this);
    return true;
  }

  /**
   * Removes a responsibility (a species) from the veterinarian.
   * 
   * @param responsibilityId The ID of the species to be removed.
   * @return true if the responsibility was removed, false if it was not assigned.
   * @throws UnknownResponsibilityException if the species is unknown.
   */
  @Override
  protected boolean removeResponsibility(String responsibilityId) 
    throws UnknownResponsibilityException {
    String key = responsibilityId.toLowerCase();
    Species species = _responsibilityMap.get(key);
    if(species == null)
      throw new UnknownResponsibilityException(responsibilityId);
    species.removeVet(this);
    _responsibilityMap.remove(key);
    return true;
  }

  /**
   * Adds a vaccine event to the veterinarian's vaccine history.
   * 
   * @param event The vaccine event to be added.
   */
  void addVaccineEvent(VaccineEvent event) {
    _vaccineEventList.add(event);
  }

  /**
   * Returns an unmodifiable list of all vaccine events performed by the veterinarian.
   * 
   * @return A list of vaccine events.
   */
  List<VaccineEvent> getAllVaccineEvents() {
    return Collections.unmodifiableList(_vaccineEventList);
  }

  /**
   * Checks if the veterinarian has a specific responsibility (a species).
   * 
   * @param respId The ID of the species.
   * @return true if the veterinarian has the responsibility, false otherwise.
   */
  @Override
  protected boolean hasResponsibility(String respId) {
    return _responsibilityMap.containsKey(respId.toLowerCase());
  }
}
