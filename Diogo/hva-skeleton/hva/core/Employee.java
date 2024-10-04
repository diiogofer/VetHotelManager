package hva.core;

public abstract class Employee extends HotelEntity {

    Employee(String id, String name) {
        super(id, name);
    }

    abstract int calculateSatisfaction();
    
}
