package hva.core;

import java.util.*;

import hva.core.exception.UnknownResponsibilityException;

/**
 * Represents a zookeeper (EmployeeKeeper) in the veterinary hotel.
 */
public class EmployeeKeeper extends Employee {

  /** Map of habitats that the zookeeper is responsible for, keyed by habitat ID. */
  private Map<String, Habitat> _responsibilityMap = new HashMap<>();

  /** Strategy used to calculate satisfaction for the zookeeper. */
  private EmployeeKeeperStrategy _strategy;
  
  /**
   * Constructs a zookeeper with a given ID and name.
   * 
   * @param identifier The unique identifier of the zookeeper.
   * @param name The name of the zookeeper.
   */
  EmployeeKeeper(String identifier, String name) {
    super(identifier, name);
  }

  /**
   * Calculates the satisfaction of the zookeeper.
   * 
   * @return The calculated satisfaction level of the zookeeper.
   */
  @Override
  double calculateSatisfaction(){
    _strategy = new EmployeeKeeperSatisfactionStrategy();
    return _strategy.calculateSatisfaction(this);
  }

  /**
   * Returns an unmodifiable list of all habitats the zookeeper is responsible for.
   * 
   * @return A list of habitats managed by the zookeeper.
   */
  List<Habitat> getAllHabitats() {
    List<Habitat> list = new ArrayList<>(_responsibilityMap.values());
    return Collections.unmodifiableList(list);
  }

  /**
   * Returns a string representation of the zookeeper's responsibilities.
   * The habitats are listed in the format:
   * <pre>
   * habitatId1,habitatId2,...,habitatIdN
   * </pre>
   * If there are no responsibilities, an empty string is returned.
   * 
   * @return A string of the habitats the zookeeper is responsible for, or an empty string if none exist.
   */
  @Override
  protected String responsibilitiesToString() {
    StringBuilder str = new StringBuilder();
    List<Habitat> list = new ArrayList<>(_responsibilityMap.values());
    Collections.sort(list);
    
    for (Habitat h : list) {
        str.append(h.getId()).append(",");
    }
    if (str.length() > 0) {
        str.deleteCharAt(str.length() - 1);  // Remove comma
    }
    return str.toString();
  }


  /**
   * Returns the employee type as a string.
   * 
   * @return The string "TRT".
   */
  @Override
  protected String employeeTypeToString() {
    return "TRT";
  }

  /**
   * Adds a responsibility (a habitat) to the zookeeper.
   * 
   * @param responsibilityId The ID of the habitat to be added.
   * @param hotel The hotel context used to look up the habitat.
   * @return true if the responsibility was added, false if it was already assigned.
   * @throws UnknownResponsibilityException if the habitat is unknown.
   */
  @Override
  protected boolean addResponsibility(String responsibilityId, Hotel hotel) 
    throws UnknownResponsibilityException {
    String key = responsibilityId.toLowerCase();
    if(_responsibilityMap.containsKey(key)){
      return false;
    }
    Habitat habitat = hotel.getHabitat(key);
    if(habitat == null) {
      throw new UnknownResponsibilityException(responsibilityId);
    }
    _responsibilityMap.putIfAbsent(key, habitat);
    habitat.addKeeper(this);
    return true;
  }

  /**
   * Removes a responsibility (a habitat) from the zookeeper.
   * 
   * @param responsibilityId The ID of the habitat to be removed.
   * @return true if the responsibility was removed, false if it was not assigned.
   * @throws UnknownResponsibilityException if the habitat is unknown.
   */
  @Override
  protected boolean removeResponsibility(String responsibilityId) 
    throws UnknownResponsibilityException {
    String key = responsibilityId.toLowerCase();
    if(!_responsibilityMap.containsKey(key))
      throw new UnknownResponsibilityException(responsibilityId);
    Habitat habitat = _responsibilityMap.get(key);
    habitat.removeKeeper(this);
    _responsibilityMap.remove(key);
    return true;
  }

  /**
   * Checks if the zookeeper has a specific responsibility (a habitat).
   * 
   * @param respId The ID of the habitat.
   * @return true if the zookeeper has the responsibility, false otherwise.
   */
  @Override
  protected boolean hasResponsibility(String respId) {
    return _responsibilityMap.containsKey(respId.toLowerCase());
  }
}
