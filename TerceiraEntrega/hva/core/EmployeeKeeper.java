package hva.core;

import java.util.*;

import hva.core.exception.UnknownResponsibilityException;

public class EmployeeKeeper extends Employee {
  private Map<String, Habitat> _responsibilityMap = new HashMap<>();
  private EmployeeKeeperStrategy _strategy;
  
  EmployeeKeeper(String id, String name) {
    super(id, name);
  }
  @Override
  double calculateSatisfaction(){
    _strategy = new EmployeeKeeperSatisfactionStrategy();
    return _strategy.calculateSatisfaction(this);
  }

  List<Habitat> getAllHabitats() {
    List<Habitat> list = new ArrayList<>(_responsibilityMap.values());
    return Collections.unmodifiableList(list);
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
  protected boolean addResponsibility(String responsibilityId, Hotel hotel) 
    throws UnknownResponsibilityException {
    String key = responsibilityId.toLowerCase();
    if(_responsibilityMap.containsKey(key))
      return false;
    Habitat habitat = hotel.getHabitat(key);
    if(habitat == null) throw new UnknownResponsibilityException(responsibilityId);
    _responsibilityMap.putIfAbsent(key, habitat);
    habitat.addKeeper(this);
    return true;
  }

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

  @Override
  protected boolean hasResponsibility(String respId) {
    return _responsibilityMap.containsKey(respId.toLowerCase());
  }
}