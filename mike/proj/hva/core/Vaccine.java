package hva.core;

import java.util.*;

public class Vaccine {
    private final String _identifier;
    private String _name;
    private Map<String, Species> _species = new HashMap<>();
    private List<VaccineEvent> _vaccineEvents = new ArrayList<>();

    Vaccine(String identifier, String name, List<Species> speciesList) {
        _identifier = identifier;
        _name = name;
        for (Species s : speciesList) {
            _species.put(s.getIdentifier(), s);
        }
    }

    // VaccineEvent createVaccineEvent() {}
    
}
