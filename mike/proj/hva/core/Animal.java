package hva.core;

public class Animal {
    private final String _identifier;                                     //Unique
    private String _name;
    private Species _species;
    private Habitat _habitat;
    private Hotel _hotel;

    public Animal(String identifier, String name, Species species, Habitat habitat, Hotel hotel) {
        _identifier = identifier;
        _name = name;
        _species = species;
        _habitat = habitat;
        _hotel = hotel;
    }

    @Override
    public int hashCode() {return _identifier.hashCode();}

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Animal)) return false;
        Animal animal = (Animal)object;
        return _identifier.equals(animal._identifier);
    }

    double calculateSatisfaction() {
        int sameSpecies = _habitat.getPopulationSameSpecies(_species);
        int totalPopulation = _habitat.getPopulation(_species);
        int area = _habitat.getArea();
        int adequacy = _habitat.getAdequacyValue(_species);
        return 20 + 3 * sameSpecies - 2 * (totalPopulation - sameSpecies) + (area / totalPopulation) + adequacy;
    }

    Species getSpecies() {return _species;}
}
