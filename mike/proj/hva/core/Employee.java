package hva.core;

public abstract class Employee implements Identifiable{
    private final String _identifier;
    private String _name;

    Employee(String identifier, String name) {
        _identifier = identifier;
        _name = name;
    }
    
    @Override
    public String getId() {return _identifier;}

    @Override
    public int hashCode() {return _identifier.hashCode();}

    abstract int calculateSatisfaction();
    abstract String getEmployeeTypeToString();
    abstract String getResponsibilitiesToString();

    public String toString() {
        String resposibilityString = getResponsibilitiesToString();
        if (resposibilityString.length() == 0) {
            return getEmployeeTypeToString() + "|" + _identifier + 
                "|" +_name;
        }
        return getEmployeeTypeToString() + "|" + _identifier + 
            "|" +_name + "|" + getResponsibilitiesToString();
    }
}
