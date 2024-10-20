package hva.core;

import java.util.HashMap;
import java.util.Map;

public class Species extends Identified implements Responsibility {
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

    boolean equals(Species species) {
        return ((this.getId().toLowerCase()).equals(species.getId().toLowerCase()) &&
          (this._name.toLowerCase()).equals(species._name.toLowerCase()));
    }
}