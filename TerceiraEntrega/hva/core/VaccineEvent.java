package hva.core;

import java.io.Serializable;
import java.util.List;

import hva.core.exception.NotAllowedToVaccinateException;

/**
 * The {@code VaccineEvent} class represents a vaccination event performed by a veterinarian on an animal
 * using a specific vaccine.
 */
public class VaccineEvent implements Serializable {
  
  /** The veterinarian responsible for the vaccination. */
  private final EmployeeVet _vet;

  /** The animal that was vaccinated. */
  private final Animal _animal;

  /** The vaccine used in the vaccination. */
  private final Vaccine _vaccine;

  /** Indicates whether the vaccine was correct for the animal's species. */
  private final boolean _correct;

  /** The calculated damage caused by using an incorrect vaccine. */
  private final int _damage;

  /**
   * Constructs a {@code VaccineEvent} representing a vaccination event.
   * 
   * @param vet the veterinarian who administered the vaccine
   * @param animal the animal receiving the vaccine
   * @param vaccine the vaccine used
   * @throws NotAllowedToVaccinateException if the veterinarian is not authorized to vaccinate the species
   */
  VaccineEvent(EmployeeVet vet, Animal animal, Vaccine vaccine) 
    throws NotAllowedToVaccinateException {
    _vet = vet;
    _animal = animal;
    _vaccine = vaccine;
    Species species = animal.getSpecies();
    if(!vet.hasResponsibility(species.getId()))
      throw new NotAllowedToVaccinateException(species.getId());
    if(vaccine.isGoodForSpecies(species)) {
      _correct = true;
      _damage = 0;
    }
    else {
      _correct = false;
      _damage = calculateDamage();
    }
  }

  /**
   * Calculates the damage caused by using an incorrect vaccine.
   * The damage is determined based on the similarity between the species of the animal
   * and the species for which the vaccine is appropriate.
   * 
   * @return the damage value based on unmatched letters in species names
   */
  private int calculateDamage() {
    List<Species> vaccineSpecies = _vaccine.getGoodSpecies();
    String speciesName = _animal.getSpecies().getName().toLowerCase();
    int maxDamage = 0;

    for (Species s : vaccineSpecies) {
        String compareName = s.getName().toLowerCase();
        
        // Contar caracteres comuns
        int commonCharacters = 0;
        boolean[] usedInSpeciesName = new boolean[compareName.length()]; // Para marcar caracteres usados
        
        for (char c : speciesName.toCharArray()) {
            for (int i = 0; i < compareName.length(); i++) {
                if (c == compareName.charAt(i) && !usedInSpeciesName[i]) {
                    commonCharacters++;
                    usedInSpeciesName[i] = true; // Marcar como usado
                    break; // Sair do loop após encontrar um caractere correspondente
                }
            }
        }

        // Calcular dano
        int result = Math.max(speciesName.length(), compareName.length()) - commonCharacters;
        if (result > maxDamage) {
            maxDamage = result;
        }
    }
    
    return maxDamage;
}



  /**
   * Gets the damage value caused by the vaccination event.
   * 
   * @return the damage value
   */
  int getDamage() {
    return _damage;
  }

  /**
   * Determines whether the vaccine used was appropriate for the animal's species.
   * 
   * @return {@code true} if the vaccine was correct, {@code false} otherwise
   */
  boolean isGood() {
    return _correct;
  }

  /**
   * Converts the vaccination result to a damage category.
   * 
   * @return the vaccine damage category based on the result
   */
  VaccineDamageCategory toCategory() {
    if(_correct) return VaccineDamageCategory.NORMAL;
    if(_damage == 0) return VaccineDamageCategory.CONFUSION;
    if(1 <= _damage && _damage <= 4) return VaccineDamageCategory.ACCIDENT;
    return VaccineDamageCategory.ERROR;
  }

  /**
   * Returns a string representation of the vaccine event.
   * 
   * @return the string representation of the vaccine event
   */
  @Override
  public String toString() {
    return  "REGISTO-VACINA|" + _vaccine.getId() + "|" + 
            _vet.getId() + "|" + _animal.getSpecies().getId();
  }
}
