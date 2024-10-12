package hva.core;

/**
 * Represents a species with a unique identifier, name, population, and the number of qualified veterinarians.
 * Each species has an associated population and a count of qualified veterinarians capable of treating it.
 * 
 * <p>The species are compared based on their unique identifier and name. This class extends {@link Identified},
 * which provides unique identification functionality.</p>
 * 
 */
public class Species extends Identified{
    
    /** The name of the species. */
    private final String _name;
    
    /** The current population of the species. */
    private int _population;

    /** The number of qualified veterinarians for the species. */
    private int _numberQualifiedVets;


    /**
     * Constructs a Species with the given identifier and name. The initial population and 
     * number of qualified veterinarians are set to 0.
     * 
     * @param identifier the unique identifier for the species
     * @param name the name of the species
     */
    Species(String identifier, String name) {
        super(identifier);
        _name = name;
        _population = 0;
        _numberQualifiedVets = 0;
    }

    /**
     * Compares this species to another object for equality. 
     * Two species are considered equal if they have the same identifier and name.
     * 
     * @param object the object to compare to
     * @return {@code true} if the object is a Species with the same identifier and name, {@code false} otherwise
     */

    /**
     * Returns the name of the species.
     * 
     * @return the name of the species
     */
    String getName() {
        return _name;
    }

    /**
     * Returns the current population of the species.
     * 
     * @return the population of the species
     */ 
    int getPopulation() {
        return _population;
    }

    /**
     * Returns the number of qualified veterinarians capable of treating the species.
     * 
     * @return the number of qualified veterinarians
     */
    int getNumberQualifiedVets() {
        return _numberQualifiedVets;
    }
}