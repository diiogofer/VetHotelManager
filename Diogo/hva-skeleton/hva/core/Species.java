package hva.core;

import java.util.HashSet;
import java.util.Set;

public class Species extends HotelEntity{
    private Set<Animal> _animais;
    private Set<Veterinarian> _qualifiedVets; // set ou lista normal?

    Species(String speciesId, String speciesName) {
        super(speciesId, speciesName);
        _animais = new HashSet<>();
        _qualifiedVets = new HashSet<>();
    }

    public void addAnimal(Animal animal) {
        _animais.add(animal);
    }

    public void addQualifiedVet(Veterinarian veterinarian) {
        _qualifiedVets.add(veterinarian);
    }

    int getPopulation() {
        return _animais.size();
    }

    int getNumberQualifiedVets() { 
        return _qualifiedVets.size();
    }

    @Override
    public int hashCode() {
        return super.id().hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) // Compare the reference
            return true;

        // Check if the object is null or if the classes are different
        if (obj == null || obj.getClass() != this.getClass()) 
            return false;
    
        // downcasting to Especie and compare based on ID
        Species other = (Species) obj;
        return super.id().equals(other.id());
    }

    /* missing toString method  */

}