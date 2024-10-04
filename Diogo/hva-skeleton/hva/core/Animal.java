package hva.core;

import java.util.ArrayList;
import java.util.List;

public class Animal extends HotelEntity {
    private Species _species;
    private Habitat _habitat;
    private List<VaccineApplication> _vaccines; /* not used yet */

    Animal(String animalId, String animalName, Species species, Habitat habitat){
        super(animalId, animalName);
        _species = species;
        _habitat = habitat;
        _vaccines = new ArrayList<>();
    }
    
    Species getSpecies(){
        return _species;
    }

    void addVaccineApplication(VaccineApplication vaccineApplication){
        _vaccines.add(vaccineApplication);
    }

    double calculateSatisfaction() {
        int sameSpecies = _habitat.getPopulationSameSpecies(_species);
        int totalPopulation = _habitat.getPopulation();
        int area = _habitat.getArea();
        int adequacy = _habitat.getAdequacyValue(_species);
        return 20 + 3 * sameSpecies - 2 * (totalPopulation - sameSpecies) + (area / totalPopulation) + adequacy;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Animal other = (Animal) obj;
        return super.id().equals(other.id());
    }
    
}
