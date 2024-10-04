package hva.core;

import java.util.List;

public class VaccineApplication {
    private Vaccine _vaccine;
    private Veterinarian _veterinarian;
    private Animal _animal;
    private VaccinationResult _damage;

    public VaccineApplication(Veterinarian veterinarian, Animal animal, Vaccine vaccine) {
        _veterinarian = veterinarian;
        _animal = animal;
        _vaccine = vaccine;
        _damage = determineVaccinationResult(calculateDamage());
    }

    public int calculateDamage() {
        int maxDamage = 0;
        List<Species> speciesList = _vaccine.getVaccineSpecies();
        // Percorrer todas as espécies para as quais a vacina é designada
        for (Species species : speciesList) {
            int damage = calculateSpeciesDamage(_animal.getSpecies().name(), species.name());
            if (damage > maxDamage) {
                maxDamage = damage;
            }
        }
        return maxDamage;
    }


    private int calculateSpeciesDamage(String speciesAnimal, String speciesVaccine) {
        int maxNameLength = Math.max(speciesAnimal.length(), speciesVaccine.length());
        int commonCharacters = getCommonCharacters(speciesAnimal, speciesVaccine);
        return maxNameLength - commonCharacters;
    }

    private int getCommonCharacters(String species1, String species2) {
        int commonCount = 0;
        for (char c : species1.toCharArray()) {
            if (species2.indexOf(c) != -1) {
                commonCount++;
            }
        }
        return commonCount;
    }
    
    private VaccinationResult determineVaccinationResult(int damage) {
        if (damage == 0 && _vaccine.getVaccineSpecies().contains(_animal.getSpecies())) {
            return VaccinationResult.NORMAL;
        } else if (damage == 0) {
            return VaccinationResult.CONFUSION;
        } else if (damage >= 1 && damage <= 4) {
            return VaccinationResult.ACCIDENT;
        } else {
            return VaccinationResult.ERROR;
        }
    }
    public VaccinationResult getDamage() {
        return _damage;
    }

}
