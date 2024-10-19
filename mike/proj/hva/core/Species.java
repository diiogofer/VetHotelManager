package hva.core;

public class Species extends Identified {
  final String _name;
  Species(String id, String name) {
    super(id);
    _name = name;
  }
  String getName() {return _name;}
}
