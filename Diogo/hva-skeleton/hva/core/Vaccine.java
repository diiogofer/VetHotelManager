package hva.core;

import java.util.Map;

public class Vaccine extends Identified {
    private String _name;
    private Map<String, Species> _speciesMap;
    
    Vaccine(String vaccineId, String vaccineName, Map<String, Species> speciesMap) {
        super(vaccineId);
        _name = vaccineName;
        _speciesMap = speciesMap;
    }
}
