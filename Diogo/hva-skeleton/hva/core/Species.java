package hva.core;

import java.util.HashMap;
import java.util.Map;

public class Species extends Identified {
    final String _name;
    private Map<String, Animal> _animalMap = new HashMap<>();
    Species(String identifier, String speciesName) {
        super(identifier);
        _name = speciesName;
    }
    
    String getName() {
        return _name;
    }

    void addAnimal(Animal newAnimal) {
        _animalMap.putIfAbsent(newAnimal.getId().toLowerCase(), newAnimal);
    }
}