package hva.core;

import java.util.ArrayList;
import java.util.List;

public class Animal implements Identifiable{
    private final String _identifier;                                     //Unique
    private String _name;
    private Species _species;
    private Habitat _habitat;
    private List<VaccineEvent> _vaccineEvents;

    Animal(String identifier, String name, Species species, Habitat habitat) {
        _identifier = identifier;
        _name = name;
        _species = species;
        _habitat = habitat;
        _vaccineEvents = new ArrayList<>();
    }
    
    @Override
    public String getId() {return _identifier;}

    @Override
    public int hashCode() {return _identifier.hashCode();}

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Animal)) return false;
        Animal animal = (Animal)object;
        return _identifier.equals(animal._identifier);
    }

    double calculateSatisfaction() {
        int sameSpecies = _habitat.getPopulationSameSpecies(_species);
        int totalPopulation = _habitat.getPopulation(_species);
        int area = _habitat.getArea();
        int adequacy = _habitat.getAdequacyValue(_species);
        return 20 + 3 * sameSpecies - 2 * (totalPopulation - sameSpecies) + (area / totalPopulation) + adequacy;
    }

    Species getSpecies() {return _species;}

    public String toString() {
        String healthRecords = "";
        for(VaccineEvent event : _vaccineEvents) {
            String eventDamage = event.getDamage().toString();
            healthRecords += healthRecords.length() == 0 ? eventDamage : "," + eventDamage; 
        }
        if(healthRecords.length() == 0) healthRecords = "VOID";
        return "ANIMAL|" + _identifier + "|" + _name + "|" + _species.getId() +
             "|" + healthRecords + "|" + _habitat.getId();
    }
}
