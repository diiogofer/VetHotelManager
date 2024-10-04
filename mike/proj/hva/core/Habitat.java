package hva.core;

import java.util.*;
import hva.core.sorter.*;

public class Habitat implements Identifiable{
    private final String _identifier;
    private String _name;
    private int _area;
    private Map<String, Animal> _animals = new HashMap<>();
    private Map<String, Tree> _trees = new HashMap<>();
    private Map<Species, Integer> _adequacies = new HashMap<>();
    private int _numberKeepers;
    
    Habitat(String identifier, String name, int area) {
        _identifier = identifier;
        _name = name;
        _area = area;
    }
    @Override
    public String getId() {return _identifier;}

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
        return counter - 1;
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
        for(Tree t : _trees.values()) {
            work += t.calculateCleaningEffort();
        }
        return work;
    }
    int getNumberKeepers() {return _numberKeepers;}

    public List<Tree> getAllTrees() {
        List<Tree> treeList = new ArrayList<>(_trees.values());
        Collections.sort(treeList, new SortById<Tree>());
        return Collections.unmodifiableList(treeList);
    }

    public String toString() {return "HABITAT|" + _identifier + "|" + _name + "|" + _area + "|" + _trees.size();}
}
