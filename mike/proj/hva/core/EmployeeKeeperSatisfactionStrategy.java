package hva.core;

public class EmployeeKeeperSatisfactionStrategy implements EmployeeKeeperStrategy {

    @Override
    public double calculateSatisfaction(EmployeeKeeper keeper) {
    	double satisfaction = 300;
    	for(Habitat habitat : keeper.getAllHabitats()) {
      	satisfaction -= (habitat.calculateEffort() / habitat.getNumberKeepers());
    	}
    	return satisfaction;
    }
}
