package hva.core;

public class Species implements Identifiable{
    private final String _identifier;                                       //Unique
    private final String _name;                                             //Unique
    private int _population;
    private int _numberQualifiedVets;


    // throws NoIdentifierException, NoNameException
    Species(String identifier, String name) {
        // if(identifier == null) throw new NoIdentifierException();
        // if(name == null) throw new NoNameException();
        _identifier = identifier;
        _name = name;
        _population = 0;
        _numberQualifiedVets = 0;
    }
    @Override
    public String getId() {return _identifier;}

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

    String getName() {return _name;}
    int getPopulation() {return _population;}
    int getNumberQualifiedVets() {return _numberQualifiedVets;}
    
}