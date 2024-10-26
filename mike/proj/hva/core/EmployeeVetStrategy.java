package hva.core;

/**
 * Interface for defining a strategy to calculate the satisfaction of a veterinarian (EmployeeVet).
 * Classes implementing this interface must provide their own logic for calculating the satisfaction level of a veterinarian.
 */
public interface EmployeeVetStrategy {
    /**
     * Calculates the satisfaction level of a veterinarian.
     * 
     * @param vet The veterinarian whose satisfaction is being calculated.
     * @return The calculated satisfaction value for the veterinarian.
     */
    double calculateSatisfaction(EmployeeVet vet);
}
