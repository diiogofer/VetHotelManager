package hva.core;

public class Animal extends Identified {
    String _name;
    Species _species;
    Habitat _habitat;

    Animal(String identifier, String animalName, Species species, Habitat habitat) {
        super(identifier);
        _name = animalName;
        _species = species;
        _habitat = habitat;
    }
}
