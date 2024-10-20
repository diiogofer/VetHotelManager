package hva.core;

import java.io.Serializable;
import java.util.List;

import hva.core.exception.NotAllowedToVaccinateException;

public class VaccineEvent implements Serializable{
  
  private EmployeeVet _vet;
  private Animal _animal;
  private Vaccine _vaccine;
  private boolean _correct;
  private int _damage;

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
}
