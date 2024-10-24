package hva.core;

import hva.core.exception.NotAVetException;
import hva.core.exception.UnknownResponsibilityException;

public abstract class Employee extends Identified {

  Employee(String id, String name) {
    super(id, name);
  }

  public String toString() {
    String resp = responsibilitiesToString();
    return resp.length() == 0 ? employeeTypeToString() + "|" + getId() + "|" + getName() :
                                employeeTypeToString() + "|" + getId() + "|" + getName() + "|" + resp;
  }

  EmployeeVet isVet() throws NotAVetException {
    if (this instanceof EmployeeVet vet) {
      return vet;
    }
    throw new NotAVetException(getId());
  }

  protected abstract String responsibilitiesToString();
  protected abstract String employeeTypeToString();
  protected abstract boolean addResponsibility(String responsibilityId, Hotel hotel)
    throws UnknownResponsibilityException;
  protected abstract boolean removeResponsibility(String responsibilityId) 
    throws UnknownResponsibilityException;
  protected abstract boolean hasResponsibility(String respId);
  abstract double calculateSatisfaction();  
}
