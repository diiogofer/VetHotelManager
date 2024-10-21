package hva.core;

import java.util.*;

import hva.core.exception.UnknownResponsibilityException;

public class EmployeeKeeper extends Employee {
  private Map<String, Habitat> _responsibilityMap = new HashMap<>();
  
  EmployeeKeeper(String id, String name) {
    super(id, name);
  }
  @Override
  double calculateSatisfaction(){
    double satisfaction = 300;
    for(Habitat habitat : _responsibilityMap.values()) {
      satisfaction -= (habitat.calculateEffort() / habitat.getNumberKeepers());
    }
    return satisfaction;
  }

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


  @Override
  protected String employeeTypeToString() {
    return "TRT";
  }

  @Override
  protected Responsibility getResponsibility(Hotel hotel, String habitatId) {
    return hotel.getHabitat(habitatId);
  }

  @Override
  protected boolean addResponsibility(String responsibilityId, Hotel hotel) 
    throws UnknownResponsibilityException {
    String key = responsibilityId.toLowerCase();
    if(_responsibilityMap.containsKey(key))
      return false;
    Habitat habitat = hotel.getHabitat(key);
    if(habitat == null) throw new UnknownResponsibilityException(responsibilityId);
    _responsibilityMap.putIfAbsent(key, habitat);
    habitat.addKeeper();
    return true;
  }

  @Override
  protected boolean removeResponsibility(String responsibilityId) 
    throws UnknownResponsibilityException {
    String key = responsibilityId.toLowerCase();
    if(!_responsibilityMap.containsKey(key))
      throw new UnknownResponsibilityException(responsibilityId);
    Habitat habitat = _responsibilityMap.get(key);
    habitat.removeKeeper();
    _responsibilityMap.remove(key);
    return true;
  }

  @Override
  protected boolean hasRespondibility(Responsibility resp) {
    return _responsibilityMap.containsKey(resp.getId().toLowerCase());
  }
}
