package hva.core;
import java.util.Map;
import java.util.HashMap;

public class Habitat extends Identified {
    private String _name;
    private int _area;
    private Map<String, Animal> _animalMap = new HashMap<>();
    
    Habitat(String habitatId, String habitatName, int habitatArea) { 
        super(habitatId);
        _name = habitatName;
        _area = habitatArea;
    }

    void addAnimal(Animal newAnimal) {
        _animalMap.putIfAbsent(newAnimal.getId().toLowerCase(), newAnimal);
    }
}
