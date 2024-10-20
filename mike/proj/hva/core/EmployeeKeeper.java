package hva.core;

import java.util.*;

import hva.core.exception.UnknownResponsibilityException;

public class EmployeeKeeper extends Employee {
  private Map<String, Habitat> _responsibilityMap = new HashMap<>();
  
  EmployeeKeeper(String id, String name) {
    super(id, name);
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
  protected boolean addResponsibility(Responsibility resp) {
    String key = resp.getId().toLowerCase();
    if(!(resp instanceof Habitat habitat) || _responsibilityMap.containsKey(key))
      return false;
    _responsibilityMap.putIfAbsent(key, habitat);
    return true;
  }

  @Override
  protected boolean removeResponsibility(Responsibility resp) 
    throws UnknownResponsibilityException {
    String key = resp.getId().toLowerCase();
    if(!(resp instanceof Habitat))
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
}
