package hva.core;

import hva.core.exception.UnknownResponsibilityException;

public abstract class Employee extends Identified {
  private String _name;

  Employee(String id, String name) {
    super(id);
    _name = name;
  }

  public String toString() {
    String resp = responsibilitiesToString();
    return resp.length() == 0 ? employeeTypeToString() + "|" + getId() + "|" + _name :
                                employeeTypeToString() + "|" + getId() + "|" + _name + "|" + resp;
  }

  protected abstract String responsibilitiesToString();
  protected abstract String employeeTypeToString();
  protected abstract boolean addResponsibility(String responsibilityId, Hotel hotel)
    throws UnknownResponsibilityException;
  protected abstract boolean removeResponsibility(String responsibilityId) 
    throws UnknownResponsibilityException;
  protected abstract boolean hasRespondibility(String respId);
  abstract double calculateSatisfaction();  
}
