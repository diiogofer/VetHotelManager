package hva.core;

public abstract class Employee extends Identified{
    private String _name;

    Employee(String identifier, String name) {
        super(identifier);
        _name = name;
    }

    abstract int calculateSatisfaction();
    abstract String getEmployeeTypeToString();
    abstract String getResponsibilitiesToString();

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
