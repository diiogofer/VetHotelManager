package hva.core;

/**
 * Interface for defining a strategy to calculate the satisfaction of a zookeeper (EmployeeKeeper).
 * Classes implementing this interface must provide their own logic for calculating the satisfaction level of a zookeeper.
 */
public interface EmployeeKeeperStrategy {

    /**
     * Calculates the satisfaction level of a zookeeper.
     * 
     * @param keeper The zookeeper whose satisfaction is being calculated.
     * @return The calculated satisfaction value for the zookeeper.
     */
    double calculateSatisfaction(EmployeeKeeper keeper);
}
