package hva.core;


public class Species extends Identified{
    private final String _name;                                             //Unique
    private int _population;
    private int _numberQualifiedVets;


    // throws NoIdentifierException, NoNameException
    Species(String identifier, String name) {
        super(identifier);
        _name = name;
        _population = 0;
        _numberQualifiedVets = 0;
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Species)) return false;
        Species species = (Species)object;
        return getId().equals(species.getId()) && _name.equals(species._name);
    }

    String getName() {return _name;}
    int getPopulation() {return _population;}
    int getNumberQualifiedVets() {return _numberQualifiedVets;}
    
}