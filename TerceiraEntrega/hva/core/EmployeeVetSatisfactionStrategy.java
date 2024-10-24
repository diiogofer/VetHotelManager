package hva.core;

import java.util.List;

public class EmployeeVetSatisfactionStrategy implements EmployeeVetStrategy {
    @Override
    public double calculateSatisfaction(EmployeeVet vet) {
      double satisfaction = 20;
      List<Species> list = vet.getAllSpecies();
      for (Species species : list) {
        satisfaction -= (species.getPopulation() / species.getNumberVet());
      }
      return satisfaction;
    }
}
