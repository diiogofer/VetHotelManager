package hva.core;

/**
 * Represents an abstract Employee class, which defines the basic attributes and behaviors 
 * of an employee, including an identifier, name, and methods to calculate satisfaction, 
 * get responsibilities, and represent the type of employee.
 *
 * @see Identified
 */
public abstract class Employee extends Identified{
    /** The name of the employee. */
    private String _name;

    /**
     * Constructs an Employee object with the specified identifier and name.
     * 
     * @param identifier the unique identifier for the employee
     * @param name the name of the employee
     */
    Employee(String identifier, String name) {
        super(identifier);
        _name = name;
    }

    /**
     * Calculates the satisfaction level of the employee. 
     * Concrete subclasses must implement this method.
     * 
     * @return an integer representing the satisfaction level
     */
    abstract int calculateSatisfaction();

    /**
     * Returns the type of employee as a string.
     * Concrete subclasses must implement this method to describe the specific type of employee.
     * 
     * @return a string representing the type of employee
     */
    abstract String getEmployeeTypeToString();

    /**
     * Returns the responsibilities of the employee as a string.
     * Concrete subclasses must implement this method to describe the specific responsibilities of the employee.
     * 
     * @return a string representing the employee's responsibilities
     */
    abstract String getResponsibilitiesToString();

    /**
     * Returns a string representation of the employee, including the employee type, 
     * identifier, name, and responsibilities.
     * 
     * @return a string representing the employee
     */
    public String toString() {
        String resposibilityString = getResponsibilitiesToString();
        if (resposibilityString.length() == 0) {
            return getEmployeeTypeToString() + "|" + getId() + 
                "|" +_name;
        }
        return getEmployeeTypeToString() + "|" + getId() + 
            "|" +_name + "|" + getResponsibilitiesToString();
    }
}
