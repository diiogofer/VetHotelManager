package hva.core;

import java.io.Serializable;
import java.util.List;

import hva.core.exception.NotAllowedToVaccinateException;

public class VaccineEvent implements Serializable{
  
  private final EmployeeVet _vet;
  private final Animal _animal;
  private final Vaccine _vaccine;
  private final boolean _correct;
  private final int _damage;

  VaccineEvent(EmployeeVet vet, Animal animal, Vaccine vaccine) 
    throws NotAllowedToVaccinateException {
    _vet = vet;
    _animal = animal;
    _vaccine = vaccine;
    Species species = animal.getSpecies();
    if(!vet.hasRespondibility(species))
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

  private int calculateDamage() {
    List<Species> vaccineSpecies = _vaccine.getGoodSpecies();
    String speciesName = _animal.getSpecies().getName().toLowerCase();
    int maxDamage = 0;

    for (Species s : vaccineSpecies) {
        String compareName = s.getName().toLowerCase();
        int counter = 0;
        char[] name1 = speciesName.toCharArray();
        char[] name2 = compareName.toCharArray();

        for (char c : name1) {
            for (int i = 0; i < name2.length; i++) {
                if (c == name2[i]) {
                    name2[i] = 0;  // Mark letter as used
                    counter++;
                    break;  // Break after finding a match
                }
            }
        }

        // Calculate the difference as the number of unmatched letters
        int result = name2.length - counter;
        if (result > maxDamage) {
            maxDamage = result;
        }
    }

    return maxDamage;
  }

  //TODO CHANGE THIS 
  int getDamage() {return _damage;}
  boolean isCorrect() {return _correct;}
  VaccineDamageCategory toCategory() {
    if(_correct) return VaccineDamageCategory.NORMAL;
    if(_damage == 0) return VaccineDamageCategory.CONFUSION;
    if(1 <= _damage && _damage <= 4) return VaccineDamageCategory.ACCIDENT;
    return VaccineDamageCategory.ERROR;
  }
  public String toString() {
    return  "REGISTO-VACINA|" + _vaccine.getId() + "|" + 
            _vet.getId() + "|" + _animal.getSpecies().getId();
  }
}
