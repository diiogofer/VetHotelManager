package hva.core;

import java.util.*;

public class EmployeeVet extends Employee{
  private Map<String, Species> _responsibilityMap = new HashMap<>();


  EmployeeVet(String id, String name) {
    super(id, name);
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
  protected String treeTypeToString() {
    return "VET";
  }
}
