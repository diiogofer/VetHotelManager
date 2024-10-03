package hva.core;

import java.util.*;

public class Vaccine {
    private final String _identifier;
    private String _name;
    private Map<String, Species> _speciesMap;
    private List<VaccineEvent> _vaccineEvents = new ArrayList<>();

    Vaccine(String identifier, String name, Map<String, Species> speciesMap) {
        _identifier = identifier;
        _name = name;
        _speciesMap = speciesMap;
    }

    String getId() {return _identifier;}    
}
