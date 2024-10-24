package hva.core;

/**
 * Strategy for calculating the satisfaction of a zookeeper (EmployeeKeeper).
 */
public class EmployeeKeeperSatisfactionStrategy implements EmployeeKeeperStrategy {

    /**
     * Calculates the satisfaction of a zookeeper.
     * <p>
     * The satisfaction starts at 300 and decreases for each habitat the zookeeper is responsible for,
     * based on the effort required to maintain the habitat divided by the number of keepers responsible for it.
     * </p>
     * 
     * @param keeper The zookeeper whose satisfaction is being calculated.
     * @return The calculated satisfaction value for the zookeeper.
     */
    @Override
    public double calculateSatisfaction(EmployeeKeeper keeper) {
    	double satisfaction = 300;
    	for(Habitat habitat : keeper.getAllHabitats()) {
      	satisfaction -= (habitat.calculateEffort() / habitat.getNumberKeepers());
    	}
    	return satisfaction;
    }
}
