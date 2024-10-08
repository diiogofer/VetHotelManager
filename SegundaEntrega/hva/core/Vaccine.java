package hva.core;

import java.util.*;

public class Vaccine extends Identified{
    private String _name;
    private Map<String, Species> _speciesMap;
    private List<VaccineEvent> _vaccineEvents = new ArrayList<>();

    Vaccine(String identifier, String name, Map<String, Species> speciesMap) {
        super(identifier);
        _name = name;
        _speciesMap = speciesMap;
    }
    
    public String toString() {
        String speciesString = "";
        for(Species s : _speciesMap.values()) {
            speciesString += speciesString.length() == 0 ? s.getId() : "," + s.getId();
        }
        String ret = "VACINA|" + getId() + "|" + _name + "|" + _vaccineEvents.size();
        if(speciesString.length() == 0) return ret;
        return ret + "|" + speciesString;
    }
}
