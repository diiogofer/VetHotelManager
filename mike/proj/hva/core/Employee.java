package hva.core;

abstract class Employee {
    private final String _identifier;
    private String _name;
    private Hotel _hotel;

    Employee(String identifier, String name, Hotel hotel) {
        _identifier = identifier;
        _name = name;
        _hotel = hotel;
    }
    @Override
    public int hashCode() {return _identifier.hashCode();}

    abstract int calculateSatisfaction();
}
