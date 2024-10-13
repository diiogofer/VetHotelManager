package hva.core;

import java.util.*;

/**
 * Represents a habitat that contains animals, trees, and provides information 
 * about the area, population, and adequacy for different species.
 * 
 * <p>This class provides methods to calculate population, adequacy values, 
 * and workload.</p>
 * 
 * @see Animal
 * @see Tree
 * @see Species
 * @see Identified
 */
public class Habitat extends Identified{
    
    /** The name of the habitat. */    
    private String _name;

    /** The area of the habitat */
    private int _area;

    /** A map of animals currently living in the habitat, keyed by their unique identifier. */
    private Map<String, Animal> _animalMap = new HashMap<>();

    /** A map of trees currently present in the habitat, keyed by their unique identifier. */
    private Map<String, Tree> _treeMap = new HashMap<>();

    /** A map of adequacy values for species, indicating how suitable the habitat is for each species. */
    private Map<Species, Integer> _adequacyMap = new HashMap<>();

    /** The number of keepers responsible for maintaining the habitat. */    
    private int _numberKeepers;

    /**
     * Constructs a Habitat object with the specified identifier, name, and area.
     * 
     * @param identifier the unique identifier for the habitat
     * @param name the name of the habitat
     * @param area the area of the habitat in square meters
     */
    Habitat(String identifier, String name, int area) {
        super(identifier);
        _name = name;
        _area = area;
    }

    /**
     * Returns the population of animals in the habitat that belong to the same species 
     * as the given species, excluding the current animal.
     * 
     * @param species the species to check
     * @return the population count of animals of the same species, minus one
     */
    int getPopulationSameSpecies(Species species) {
        int counter = 0;
        for(Animal animal : _animalMap.values()) {
            if((animal.getSpecies()).equals(species)) { 
                counter++;
            }
        }
        return counter - 1;
    }

    /**
     * Returns the total population of animals in the habitat.
     * 
     * @param species the species to check
     * @return the total population of animals in the habitat
     */
    int getPopulation(Species species) { 
        return _animalMap.size();
    }

    /**
     * Returns the area of the habitat.
     * 
     * @return the area of the habitat
     */
    int getArea() { 
        return _area;
    }
    /**
     * Sets a new area for the habitat.
     * Returns if old area was changed.
     * 
     * @param newArea
     * @return
     */
    boolean setArea(int newArea) {
        if(newArea == _area) return false;
        _area = newArea;
        return true;
    }

    

    /**
     * Returns the adequacy value of the habitat for the given species. 
     * If no adequacy value is set, returns 0.
     * 
     * @param species the species to check
     * @return the adequacy value for the species, or 0 if not set
     */
    int getAdequacyValue(Species species) {
        Integer value = _adequacyMap.get(species);
        return value == null ?  0 : value;
    }

    /**
     * Set's the value of adequacy for a species in this habitat.
     * Returns if adequacy was changed for the species.
     * 
     * @param species
     * @param adequacy
     * @return true if adequacy was changed, false if not
     */
    boolean setAdequacy(Species species, SpeciesAdequacy adequacy) {
        int newValue = adequacy.getValue();
        Integer oldValue =_adequacyMap.put(species, newValue);
        if(oldValue == null) return true;
        return !oldValue.equals(newValue);
    }

    /**
     * Adds a tree to the habitat and updates the tree's habitat information.
     * 
     * @param tree the tree to be added
     */
    void addTree(Tree tree) {
        _treeMap.putIfAbsent(tree.getId().toLowerCase(), tree);
        tree.changeHabitat(this);
    }

    /**
     * Removes a tree from the habitat.
     * 
     * @param tree the tree to be removed
     */
    void removeTree(Tree tree) {
        _treeMap.remove(tree.getId());
    }

    /**
     * Calculates the total work required to maintain the habitat, considering 
     * the area, number of animals, and the cleaning effort of the trees.
     * 
     * @return the total amount of work required
     */
    double calculateWork() {
        double work = 0;
        work += _area + 3 * _animalMap.size();
        for(Tree tree : _treeMap.values()) {
            work += tree.calculateCleaningEffort();
        }
        return work;
    }

    /**
     * Returns the number of keepers assigned to the habitat.
     * 
     * @return the number of keepers
     */
    int getNumberKeepers() {
        return _numberKeepers;
    }

    /**
     * Returns a sorted and unmodifiable list of all trees in the habitat.
     * 
     * @return a list of all trees in the habitat, sorted by id.
     */
    public List<Tree> getAllTrees() {
        List<Tree> treeList = new ArrayList<>(_treeMap.values());
        Collections.sort(treeList);
        return Collections.unmodifiableList(treeList);
    }

    /**
     * Returns a string representation of the habitat, including its identifier, 
     * name, area, and the number of trees it contains.
     * 
     * @return a string representing the habitat
     */
    public String toString() {
        return "HABITAT|" + getId() + "|" + _name + "|" + _area + "|" + _treeMap.size();
    }

    void addAnimal(Animal animal) {
        _animalMap.putIfAbsent(animal.getId().toLowerCase(), animal);
    }

    void removeAnimal(Animal animal) {
        _animalMap.remove(animal.getId().toLowerCase());
    }
}
