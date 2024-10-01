package hva.core;

import hva.core.exception.NoIdentifierException;
import hva.core.exception.NoNameException;

public class Species {
    private final String _identifier;                                       //Unique
    private final String _name;                                             //Unique
    private Hotel _hotel;

    
    Species(String identifier, String name, Hotel hotel) throws NoIdentifierException, NoNameException{
        if(identifier == null) throw new NoIdentifierException();
        if(name == null) throw new NoNameException();
        _identifier = identifier;
        _name = name;
        _hotel = hotel;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + _identifier.hashCode();
        result = 31 * result + _name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Species)) return false;
        Species species = (Species)object;
        return _identifier.equals(species._identifier) && _name.equals(species._name);
    }
}