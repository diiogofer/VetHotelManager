package hva.core;

import hva.core.exception.NotAVetException;
import hva.core.exception.UnknownResponsibilityException;

/**
 * Represents an (abstract) employee in the zoo hotel.
 */
public abstract class Employee extends Identified {

  /**
   * Constructs an employee with a given ID and name.
   * 
   * @param identifier The unique identifier of the employee.
   * @param name The name of the employee.
   */
  Employee(String identifier, String name) {
    super(identifier, name);
  }

  /**
   * Returns a string representation of the employee in the format:
   * <pre>
   * employeeType|id|name|responsibilities
   * </pre>
   * If the employee has no responsibilities, the format will be:
   * <pre>
   * employeeType|id|name
   * </pre>
   * 
   * @return A string representation of the employee, including their type, ID, name, and responsibilities (if any).
   */
  @Override
  public String toString() {
    String resp = responsibilitiesToString();
    return resp.length() == 0 ? employeeTypeToString() + "|" + getId() + "|" + getName() :
                                employeeTypeToString() + "|" + getId() + "|" + getName() + "|" + resp;
  }

  /**
   * Checks if the employee is a veterinarian.
   * 
   * @return An instance of {@link EmployeeVet} if the employee is a veterinarian.
   * @throws NotAVetException if the employee is not a veterinarian.
   */
  EmployeeVet isVet() throws NotAVetException {
    if (this instanceof EmployeeVet vet) {
      return vet;
    }
    throw new NotAVetException(getId());
  }

  /**
   * Returns a string representation of the employee's responsibilities.
   * 
   * @return A string of responsibilities or an empty string if none exist.
   */
  protected abstract String responsibilitiesToString();

  /**
   * Returns a string representation of the employee type.
   * 
   * @return A string representing the employee type.
   */
  protected abstract String employeeTypeToString();

  /**
   * Adds a responsibility to the employee, identified by its ID.
   * 
   * @param responsibilityId The ID of the responsibility.
   * @param hotel The hotel context used for checking the responsibility.
   * @return true if the responsibility was added, false if it was already assigned.
   * @throws UnknownResponsibilityException if the responsibility is unknown.
   */
  protected abstract boolean addResponsibility(String responsibilityId, Hotel hotel)
    throws UnknownResponsibilityException;

  /**
   * Removes a responsibility from the employee, identified by its ID.
   * 
   * @param responsibilityId The ID of the responsibility to remove.
   * @return true if the responsibility was removed, false if it was not assigned.
   * @throws UnknownResponsibilityException if the responsibility is unknown.
   */
  protected abstract boolean removeResponsibility(String responsibilityId) 
    throws UnknownResponsibilityException;

  /**
   * Checks if the employee has a specific responsibility.
   * 
   * @param respId The ID of the responsibility to check.
   * @return true if the employee has the responsibility, false otherwise.
   */
  protected abstract boolean hasResponsibility(String respId);
  
  /**
   * Calculates the satisfaction level of the employee.
   * 
   * @return The satisfaction level of the employee.
   */
  abstract double calculateSatisfaction();  
}
