package hva.core;

abstract class Employee implements Identifiable{
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
}
