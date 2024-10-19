package hva.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeVet extends Employee{
  private Map<String, Species> _responsibilityMap = new HashMap<>();    
  
  EmployeeVet(String identifier, String vetName) {
    super(identifier, vetName);
  }
  
  @Override
  protected String responsibilitiesToString() {
    StringBuilder str = new StringBuilder();
    List<Species> list = new ArrayList<>(_responsibilityMap.values());
    Collections.sort(list);
    
    for (Species s : list) {
        str.append(s).append(",");
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
  
}