package hva.core;

import java.io.Serializable;
import java.util.*;

public class Vaccine implements Identifiable, Serializable{
    private final String _identifier;
    private String _name;
    private Map<String, Species> _speciesMap;
    private List<VaccineEvent> _vaccineEvents = new ArrayList<>();

    Vaccine(String identifier, String name, Map<String, Species> speciesMap) {
        _identifier = identifier;
        _name = name;
        _speciesMap = speciesMap;
    }
    @Override
    public String getId() {return _identifier;}
    
    public String toString() {
        String speciesString = "";
        for(Species s : _speciesMap.values()) {
            speciesString += speciesString.length() == 0 ? s.getId() : "," + s.getId();
        }
        String ret = "VACINA|" + _identifier + "|" + _name + "|" + _vaccineEvents.size();
        if(speciesString.length() == 0) return ret;
        return ret + "|" + speciesString;
    }
}
