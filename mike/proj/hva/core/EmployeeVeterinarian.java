package hva.core;

import java.util.*;

/**
 * Represents a veterinarian, an employee responsible for treating specific species in the hotel.
 * Each veterinarian is assigned a list of species as their responsibilities, and can calculate their satisfaction.
 * 
 * <p>This class extends the abstract {@link Employee} class and provides implementations for
 * calculating satisfaction, listing responsibilities, and returning the type of employee.</p>
 * 
 * <p>Veterinarians are identified by the string "VET".</p>
 * 
 * @see Species
 */
public class EmployeeVeterinarian extends Employee{
    
    /** A map of species that the veterinarian is responsible for, keyed by species ID. */
    private Map<String, Species> _responsibilities = new HashMap<>();

    /**
     * Constructs a Veterinarian with the specified identifier, name, and list of species.
     * The veterinarian's responsibilities are initialized with the provided species.
     * 
     * @param identifier the unique identifier for the veterinarian
     * @param name the name of the veterinarian
     * @param responsibilities the list of species that the veterinarian is responsible for
     */
    EmployeeVeterinarian(String identifier, String name, List<Species> responsibilities) {
        super(identifier, name);

        for (Species s : responsibilities) {
            _responsibilities.put(s.getId(), s);
        }
    }

    /**
     * Calculates the satisfaction level of the veterinarian based on the population of the species
     * they are responsible for and the number of qualified veterinarians available.
     * 
     * @return an integer representing the veterinarian's satisfaction level
     */
    @Override
    int calculateSatisfaction() {
        double sat = 0;
        for(Species s : _responsibilities.values()) {
            sat -= ((s.getPopulation() / s.getNumberQualifiedVets()));
        }
        sat += 20;
        return (int)Math.round(sat);
    }

    /**
     * Returns a string representing the type of employee, which is "VET" for veterinarians.
     * 
     * @return the string "VET" representing the veterinarian's employee type
     */
    @Override
    String getEmployeeTypeToString() {
        return "VET";
    }

    /**
     * Returns a string representing the IDs of the species the veterinarian is responsible for,
     * sorted and separated by commas.
     * 
     * @return a string of species IDs, separated by commas
     */
    @Override
    String getResponsibilitiesToString() {
        String responsibilityString = "";
        List<Species> responsibilityList = new ArrayList<>(_responsibilities.values());
        Collections.sort(responsibilityList);
        for(Species s : responsibilityList) {
            String id = s.getId();
            responsibilityString += responsibilityString.length() == 0 ? id : "," + id;
        }
        return responsibilityString;
    }
}
