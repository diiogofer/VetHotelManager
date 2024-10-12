package hva.core;

import java.util.*;

/**
 * Represents a vaccine, which can be administered to a set of species.
 * Each vaccine is identified by a unique ID and has a name, a map of species it is effective for, 
 * and a list of vaccine events that track its application.
 * 
 * <p>The species that the vaccine is applicable for are stored in a map, and the number of vaccine
 * events are tracked to provide a summary of the vaccine's usage.</p>
 * 
 * <p>This class extends {@link Identified}, which provides a unique identifier for the vaccine.</p>
 * 
 * @see Species
 * @see VaccineEvent
 */
public class Vaccine extends Identified{
    
    /** The name of the vaccine. */
    private String _name;

    /** A map of species that this vaccine can be applied to, keyed by species ID. */
    private Map<String, Species> _speciesMap;
    
    /** A list of vaccine events that track the administration of this vaccine. */
    private List<VaccineEvent> _vaccineEvents = new ArrayList<>();

    /**
     * Constructs a Vaccine with the specified identifier, name, and map of species.
     * 
     * @param identifier the unique identifier for the vaccine
     * @param name the name of the vaccine
     * @param speciesMap a map of species that this vaccine is effective for
     */
    Vaccine(String identifier, String name, Map<String, Species> speciesMap) {
        super(identifier);
        _name = name;
        _speciesMap = speciesMap;
    }
    
    /**
     * Returns a string representation of the vaccine, including its ID, name, the number of vaccine events,
     * and the species it is effective for. The species are listed by their IDs.
     * 
     * @return a string representation of the vaccine
     */
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
