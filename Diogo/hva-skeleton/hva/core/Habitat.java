package hva.core;

import java.util.*;

public class Habitat extends HotelEntity {
    private int _area;
    private Map<String, Animal> _animals;
    private List<Tree> _trees;
    private Map<Species, Integer> _speciesAdequacy;
    private List<Zookeeper> _zookeepers;

    Habitat(String habitatId, String habitatName,int area){
        super(habitatId, habitatName);
        _area = area;
        _animals = new HashMap<>();
        _trees = new ArrayList<>();
        _speciesAdequacy = new HashMap<>();
        _zookeepers = new ArrayList<>();
    }

    int getArea(){
        return _area;
    }

    void addAnimal(Animal animal) {
        _animals.put(animal.id(), animal);
    }

    void addTree(Tree tree) {
        _trees.add(tree);
    }
    
    void addZookeeper(Zookeeper zookeeper) {
        _zookeepers.add(zookeeper);
    }

    int getPopulationSameSpecies(Species species) {
        int counter = 0;
        for(Animal animal : _animals.values()) {
            if((animal.getSpecies()).equals(species)) 
                counter++;
        }
        return counter - 1;
    }
    
    int getPopulation() { 
        return _animals.size();
    }

    int getAdequacyValue(Species species) {
        Integer value = _speciesAdequacy.get(species);
        return value == null ?  0 : value;
    }
    
    double calculateWork() {
        double work = _area + 3 * _animals.size();
        for(Tree tree : _trees) {
            work += tree.calculateCleaningEffort();
        }
        return work;
    }

    int getNumberKeepers(){
        return _zookeepers.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Habitat other = (Habitat) obj;
        return super.id().equals(other.id());
    }
}
