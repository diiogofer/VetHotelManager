package hva.core;

import java.util.*;

public class Habitat {
    private final String _identifier;
    private String _name;
    private int _area;
    private Hotel _hotel;
    private Map<String, Animal> _animals = new HashMap<>();
    private List<Tree> _trees = new ArrayList<>();
    private Map<Species, Integer> _adequacies = new HashMap<>();
    private int _numberKeepers;
    
    Habitat(String identifier, String name, int area, Hotel hotel) {
        _identifier = identifier;
        _name = name;
        _area = area;
        _hotel = hotel;
    }

    @Override
    public int hashCode() {return _identifier.hashCode();}

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Habitat)) return false;
        Habitat habitat = (Habitat)object;
        return _identifier.equals(habitat._identifier);
    }

    int getPopulationSameSpecies(Species species) {
        int counter = 0;
        for(Animal animal : _animals.values()) {if((animal.getSpecies()).equals(species)) counter++;}
        return counter;
    }
    int getPopulation(Species species) { return _animals.size();}
    int getArea() {return _area;}
    int getAdequacyValue(Species species) {
        Integer value = _adequacies.get(species);
        return value == null ?  0 : value;
    }
    double calculateWork() {
        double work = 0;
        work += _area + 3 * _animals.size();
        for(Tree t : _trees) {
            work += t.calculateCleaningEffort();
        }
        return work;
    }
    int getNumberKeepers() {return _numberKeepers;}
}
