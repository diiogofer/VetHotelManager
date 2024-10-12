package hva.core;

import java.util.*;

/**
 * Represents a Keeper, an employee responsible for maintaining and working in specific habitats.
 * Each Keeper is assigned a list of habitats as their responsibilities.
 * 
 * <p>The satisfaction of a Keeper is influenced by the amount of work required in the habitats
 * they are responsible for and the number of keepers available to share that work.</p>
 * 
 * <p>Keepers are part of the "TRT" employee type.</p>
 * 
 * @see Habitat
 */
public class EmployeeKeeper extends Employee {
    
    /** A map of habitats that the keeper is responsible for, keyed by habitat ID. */
    private Map<String, Habitat> _responsibilities = new HashMap<>();


    /**
     * Constructs a Keeper with the specified identifier, name, and list of habitats.
     * The keeper's responsibilities are initialized with the provided habitats.
     * 
     * @param identifier the unique identifier for the keeper
     * @param name the name of the keeper
     * @param habitats the list of habitats that the keeper is responsible for
     */
    EmployeeKeeper(String identifier, String name, List<Habitat> habitats) {
        super(identifier, name);
        for(Habitat h : habitats) {
            _responsibilities.put(h.getId(), h);
        }
    }

    /**
     * Calculates the satisfaction level of the keeper based on the workload of the habitats
     * they are responsible for and the number of keepers s that habitats.
     * 
     * @return an integer representing the keeper's satisfaction level
     */
    @Override
    int calculateSatisfaction() {
        double sat = 0;
        for (Habitat h : _responsibilities.values()) {
            sat -= ( h.calculateWork() / h.getNumberKeepers() );
        }
        sat += 300;
        return (int)Math.round(sat);
    }

    /**
     * Returns a string representing the type of employee, which is "TRT" for keepers.
     * 
     * @return the string "TRT" representing the keeper's employee type
     */
    @Override
    String getEmployeeTypeToString() {
        return "TRT";
    }

    /**
     * Returns a string representing the IDs of the habitats the keeper is responsible for,
     * sorted and separated by commas.
     * 
     * @return a string of habitat IDs, separated by commas
     */
    @Override
    String getResponsibilitiesToString() {
        String responsibilityString = "";
        List<Habitat> responsibilityList = new ArrayList<>(_responsibilities.values());
        Collections.sort(responsibilityList);
        for(Habitat h : responsibilityList) {
            String id = h.getId();
            responsibilityString += responsibilityString.length() == 0 ? id : "," + id;
        }
        return responsibilityString;
    }
}
