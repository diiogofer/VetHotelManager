package hva.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeKeeper extends Employee {
    private Map<String, Habitat> _responsibilityMap = new HashMap<>();

    EmployeeKeeper(String identifier, String keeperName) {
        super(identifier, keeperName);
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
    protected String responsibilitiesToString() {
        StringBuilder str = new StringBuilder();
        List<Habitat> list = new ArrayList<>(_responsibilityMap.values());
        Collections.sort(list);
    
        for (Habitat h : list) {
            str.append(h).append(",");
        }
        // Check if the string builder is not empty, then remove the last comma
        if (str.length() > 0) {
            str.deleteCharAt(str.length() - 1);  // Remove the trailing comma
        }
        return str.toString();
    }
  
    @Override
    protected String employeeTypeToString() {
        return "TRT";
    }
}
