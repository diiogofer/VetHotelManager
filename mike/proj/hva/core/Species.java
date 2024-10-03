package hva.core;

import java.util.*;
import hva.core.exception.*;

public class Species {
    private final String _identifier;                                       //Unique
    private final String _name;                                             //Unique
    private Hotel _hotel;
    private int _population;
    private int _numberQualifiedVets;


    
    Species(String identifier, String name, Hotel hotel) throws NoIdentifierException, NoNameException{
        if(identifier == null) throw new NoIdentifierException();
        if(name == null) throw new NoNameException();
        _identifier = identifier;
        _name = name;
        _hotel = hotel;
        _population = 0;
        _numberQualifiedVets = 0;
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

    int getPopulation() {return _population;}
    int getNumberQualifiedVets() {return _numberQualifiedVets;}
}