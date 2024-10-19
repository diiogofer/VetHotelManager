package hva.core;

public class Species extends Identified {
    final String _name;
    Species(String identifier, String speciesName) {
        super(identifier);
        _name = speciesName;
    }
    
    String getName() {
        return _name;
    }
}