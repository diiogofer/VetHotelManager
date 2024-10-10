package hva.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Animal with an identifier, name, species, habitat, and vaccine events.
 * The Animal can also calculate its satisfaction based on its habitat conditions.
 * 
 * @see Species
 * @see Habitat
 * @see VaccineEvent
 * @see Identified
 */
public class Animal extends Identified{
    
    /** The name of the animal. */
    private String _name;

    /** The species of the animal. */
    private Species _species;

    /** The habitat where the animal lives. */
    private Habitat _habitat;

    /** List of vaccine events that the animal has received. */
    private List<VaccineEvent> _vaccineEvents;

    /**
     * Constructs an Animal object with the specified identifier, name, species, habitat and a list of vaccine events.
     * 
     * @param identifier the unique identifier for the animal
     * @param name the name of the animal
     * @param species the species to which the animal belongs
     * @param habitat the habitat where the animal resides
     */
    Animal(String identifier, String name, Species species, Habitat habitat) {
        super(identifier);
        _name = name;
        _species = species;
        _habitat = habitat;
        _vaccineEvents = new ArrayList<>();
    }

    /**
     * Calculates the satisfaction of the animal.
     * 
     * @return a double value representing the satisfaction of the animal
     */
    double calculateSatisfaction() {
        int sameSpecies = _habitat.getPopulationSameSpecies(_species);
        int totalPopulation = _habitat.getPopulation(_species);
        int area = _habitat.getArea();
        int adequacy = _habitat.getAdequacyValue(_species);
        return 20 + 3 * sameSpecies - 2 * (totalPopulation - sameSpecies) + (area / totalPopulation) + adequacy;
    }

    /**
     * Returns the species of the animal.
     * 
     * @return the species of the animal
     */    
    Species getSpecies() {
        return _species;
    }

    /**
     * Returns a string representation of the animal, including its identifier, name, species, 
     * vaccine events, and habitat identifier.
     * 
     * @return a string representation of the animal
     */
    @Override
    public String toString() {
        String healthRecords = "";
        for(VaccineEvent event : _vaccineEvents) {
            String eventDamage = event.getDamage().toString();
            healthRecords += healthRecords.length() == 0 ? eventDamage : "," + eventDamage; 
        }
        if(healthRecords.length() == 0) { 
            healthRecords = "VOID";
        }
        return "ANIMAL|" + getId() + "|" + _name + "|" + _species.getId() +
            "|" + healthRecords + "|" + _habitat.getId();
    }
}
