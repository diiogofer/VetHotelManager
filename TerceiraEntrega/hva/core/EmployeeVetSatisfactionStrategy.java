package hva.core;

import java.util.List;

/**
 * Strategy for calculating the satisfaction of a veterinarian (EmployeeVet).
 */
public class EmployeeVetSatisfactionStrategy implements EmployeeVetStrategy {
    
    /**
     * Calculates the satisfaction of a veterinarian.
     * <p>
     * The satisfaction starts at 20 and for each species the veterinarian is responsible for, 
     * the satisfaction decreases by the species population divided by the number of veterinarians assigned to that species.
     * </p>
     * 
     * @param vet The veterinarian whose satisfaction is being calculated.
     * @return The calculated satisfaction value for the veterinarian.
     */
    @Override
    public double calculateSatisfaction(EmployeeVet vet) {
      double satisfaction = 20;
      List<Species> list = vet.getAllSpecies();
      for (Species species : list) {
        satisfaction -= ((double) species.getPopulation() / species.getNumberVet());
      }
      return satisfaction;
    }
}
