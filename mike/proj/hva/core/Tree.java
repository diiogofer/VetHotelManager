package hva.core;

/**
 * Represents a tree in the hotel, with attributes such as name, age, cleaning difficulty,
 * and its associated habitat. This abstract class provides common functionality for all types
 * of trees, including the ability to calculate cleaning effort.
 * 
 * <p>Concrete subclasses must provide implementations for tree type, seasonal cleaning effort,
 * and biological cycle based on the season.</p>
 * 
 * <p>This class extends {@link Identified}, which provides a unique identifier for each tree.</p>
 * 
 * @see BiologicalCycle
 * @see Season
 * @see Habitat
 */
public abstract class Tree extends Identified{

    /** The name of the tree. */
    private String _name;

    /** The age of the tree in seasons. */
    private int _ageInSeasons;

    /** The base difficulty for cleaning around the tree. */
    private int _baseCleaningDifficulty;

    /** The hotel where the tree is located. */
    private Hotel _hotel;

    /** The habitat where the tree is planted. */
    private Habitat _habitat;

    /**
     * Constructs a Tree with the specified identifier, name, age, base cleaning difficulty, and hotel.
     * The age is converted from years to seasons (1 year = 4 seasons).
     * 
     * @param identifier the unique identifier for the tree
     * @param name the name of the tree
     * @param ageInYears the age of the tree in years
     * @param baseCleaningDifficulty the base difficulty of cleaning around the tree
     * @param hotel the hotel where the tree is located
     */
    Tree(String identifier, String name, int ageInYears, int baseCleaningDifficulty, Hotel hotel) {
        super(identifier);
        _name = name;
        _ageInSeasons = 4 * ageInYears;
        _baseCleaningDifficulty = baseCleaningDifficulty;
        _hotel = hotel;
    }

    /**
     * Changes the habitat of the tree to the specified habitat. 
     * If the tree is already in another habitat, it is removed from that habitat first.
     * 
     * @param habitat the new habitat for the tree
     */
    void changeHabitat(Habitat habitat) {
        if(_habitat != null) _habitat.removeTree(this);
        _habitat = habitat;
    }

    /**
     * Returns the name of the tree.
     * 
     * @return the name of the tree
     */
    String getName() {
        return _name;
    }

    /**
     * Returns the age of the tree in years.
     * 
     * @return the age of the tree in years
     */
    int getAge() {
        return _ageInSeasons / 4;
    }

    /**
     * Abstract method that returns a string representing the type of tree (e.g., "PERENE" or "CADUCA").
     * Subclasses must implement this method to provide the specific tree type.
     * 
     * @return the string representing the tree type
     */  
    abstract String treeTypeToString();

    /**
     * Returns the base cleaning difficulty for the tree.
     * 
     * @return the base cleaning difficulty
     */
    int getBaseCleaningDifficulty() {return _baseCleaningDifficulty;}
    
    /**
     * Abstract method that returns the cleaning effort for the tree during a given season.
     * Subclasses must implement this method to provide the cleaning effort for each season.
     * 
     * @param season the current season
     * @return the cleaning effort for the given season
     */
    abstract int getSeasonalCleaningEffort(Season season);

    /**
     * Calculates the total cleaning effort required for the tree based on its base cleaning difficulty,
     * seasonal cleaning effort, and age.
     * 
     * @return the total cleaning effort for the tree
     */
    public double calculateCleaningEffort() {
        return _baseCleaningDifficulty * getSeasonalCleaningEffort(_hotel.getSeason()) * Math.log(getAge() + 1);
    }

    /**
     * Abstract method that returns the biological cycle of the tree for a given season.
     * Subclasses must implement this method to provide the biological cycle for each season.
     * 
     * @param season the current season
     * @return the biological cycle for the given season
     */
    abstract BiologicalCycle getBiologicalCycle(Season season);
    
    /**
     * Returns a string representation of the tree, including its ID, name, age, base cleaning difficulty,
     * tree type, and biological cycle for the current season.
     * 
     * @return a string representation of the tree
     */
    public String toString() {
        return "ÁRVORE|" + getId() + "|" + _name + "|" + getAge() + "|" + _baseCleaningDifficulty + 
            "|" + treeTypeToString() + "|" + getBiologicalCycle(_hotel.getSeason());
    }
}
