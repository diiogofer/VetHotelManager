package hva.core;

import java.util.*;

import hva.core.exception.UnknownResponsibilityException;

public class EmployeeVet extends Employee {

  private EmployeeVetStrategy _strategy;
  private Map<String, Species> _responsibilityMap = new HashMap<>();
  private List<VaccineEvent> _vaccineEventList = new ArrayList<>();


  EmployeeVet(String id, String name) {
    super(id, name);
  }


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
        str.deleteCharAt(str.length() - 1);  // Remove the trailing comma
    }
    return str.toString();
  }

  @Override
  double calculateSatisfaction() {
    _strategy = new EmployeeVetSatisfactionStrategy();
    return _strategy.calculateSatisfaction(this);
  }

  List<Species> getAllSpecies() {
    List<Species> list = new ArrayList<>(_responsibilityMap.values());
    return Collections.unmodifiableList(list);  
  }

  @Override
  protected String employeeTypeToString() {
    return "VET";
  }

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

  void addVaccineEvent(VaccineEvent event) {
    _vaccineEventList.add(event);
  }

  List<VaccineEvent> getAllVaccineEvents() {
    return Collections.unmodifiableList(_vaccineEventList);
  }

  @Override
  protected boolean hasResponsibility(String respId) {
    return _responsibilityMap.containsKey(respId.toLowerCase());
  }
}