package hva.core;

public class Employee extends Identified {
  private String _name;
  Employee(String id, String name) {
    super(id);
    _name = name;
  }
}
