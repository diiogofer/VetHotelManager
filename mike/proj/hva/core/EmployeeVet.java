package hva.core;

import java.util.*;

import hva.core.exception.UnknownResponsibilityException;

public class EmployeeVet extends Employee {

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
  protected String employeeTypeToString() {
    return "VET";
  }

  @Override
  protected Responsibility getResponsibility(Hotel hotel, String speciesId) {
    return hotel.getSpecies(speciesId);
  }

  @Override
  protected boolean addResponsibility(Responsibility resp) {
    String key = resp.getId().toLowerCase();
    if(!(resp instanceof Species species) || _responsibilityMap.containsKey(key))
      return false;
    _responsibilityMap.putIfAbsent(key, species);
    return true;
  }

  @Override
  protected boolean removeResponsibility(Responsibility resp) 
    throws UnknownResponsibilityException {
    String key = resp.getId().toLowerCase();
    if(!(resp instanceof Species) )
      return false;
    if(!_responsibilityMap.containsKey(key))
      throw new UnknownResponsibilityException(resp.getId());
    _responsibilityMap.remove(key);
    return true;
  }

  @Override
  protected boolean hasRespondibility(Responsibility resp) {
    return _responsibilityMap.containsKey(resp.getId().toLowerCase());
  }

  void addVaccineEvent(VaccineEvent event) {
    _vaccineEventList.add(event);
  }

  List<VaccineEvent> getAllVaccineEvents() {
    return Collections.unmodifiableList(_vaccineEventList);
  }
}
