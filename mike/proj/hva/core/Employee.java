package hva.core;

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
  protected abstract Responsibility getResponsibility(Hotel hotel, String responsibilityId);
  protected abstract boolean addResponsibility(Responsibility resp);
}
