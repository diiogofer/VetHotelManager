package hva.core;

import java.util.*;

public class Habitat extends Identified{
    private String _name;
    private int _area;
    private Map<String, Animal> _animals = new HashMap<>();
    private Map<String, Tree> _trees = new HashMap<>();
    private Map<Species, Integer> _adequacies = new HashMap<>();
    private int _numberKeepers;
    
    Habitat(String identifier, String name, int area) {
        super(identifier);
        _name = name;
        _area = area;
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
    void addTree(Tree tree) {
        _trees.put(tree.getId(), tree);
        tree.changeHabitat(this);
    }
    void removeTree(Tree tree) {_trees.remove(tree.getId());}
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
        Collections.sort(treeList);
        return Collections.unmodifiableList(treeList);
    }

    public String toString() {return "HABITAT|" + getId() + "|" + _name + "|" + _area + "|" + _trees.size();}
}
