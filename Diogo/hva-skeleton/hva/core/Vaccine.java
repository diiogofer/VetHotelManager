package hva.core;

import java.util.*;

public class Vaccine extends Identified {
    private String _name;
    private Map<String, Species> _speciesMap;
    List<VaccineEvent> _vaccineEventList = new ArrayList<>();
    
    Vaccine(String vaccineId, String vaccineName, Map<String, Species> speciesMap) {
        super(vaccineId);
        _name = vaccineName;
        _speciesMap = speciesMap;
    }

    private String speciesToString() {
        StringBuilder str = new StringBuilder();
        List<Species> list = new ArrayList<>(_speciesMap.values());
        Collections.sort(list);
        
        for ( Species s : list) {
            str.append(s.getId()).append(",");
        }
        // Check if the string builder is not empty, then remove the last comma
        if (str.length() > 0) {
            str.deleteCharAt(str.length() - 1);  // Remove the trailing comma
        }
        return str.toString();
    }

    public String toString() {
        return "VACINA|" + getId() + "|" + _name + "|" +
           _vaccineEventList.size() + "|" + speciesToString();
    }

}
