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
        _damage = VaccinationResult.getResultFromDamage(calculateDamage(), _vaccine.getVaccineSpecies().contains(animal.getSpecies()));
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

    public VaccinationResult getDamage() {
        return _damage;
    }

}
