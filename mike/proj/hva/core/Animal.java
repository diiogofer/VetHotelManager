package hva.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Animal extends Identified {
  String _name;
  Species _species;
  Habitat _habitat;
  private List<VaccineEvent> _vaccineEventList = new ArrayList<>();

  Animal(String id, String name, Species species, Habitat habitat) {
    super(id);
    _name = name;
    _species = species;
    _habitat = habitat;
  }
  boolean changeHabitat(Habitat habitat) {
    if(_habitat != null) {
      if(_habitat.equals(habitat)) return false;
      _habitat.removeAnimal(this);
    }
    _habitat = habitat;
    _habitat.addAnimal(this);
    return true;
  }
  public String toString() {
    return "ANIMAL|" + getId() + "|" + _name + "|" + _species.getId() + "|" + 
      vaccineEventsToString() + "|" + _habitat.getId();
  }
  // TODO
  private String vaccineEventsToString() {
    if(_vaccineEventList.size() == 0) return "VOID";

    StringBuilder damageString = new StringBuilder();

    for (VaccineEvent event : _vaccineEventList) {
        damageString.append(event.toCategory()).append(",");
    }
    // Remove , at end
    damageString.setLength(damageString.length() - 1);
    return damageString.toString();
  }
  int calculateSatisfaction() {
    int same = _habitat.countSpecies(_species) - 1;
    int population = _habitat.countPopulation();
    int different = population - same - 1;
    int area = _habitat.getArea();
    int adequacy = _habitat.getAdequacy(_species);
    return 20 + 3 * same - 2 * different + area / population + adequacy;
  }

  Species getSpecies() {return _species;}

  void addVaccineEvent(VaccineEvent event) {
    _vaccineEventList.add(event);
  }

  List<VaccineEvent> getAllVaccineEvents() {
    return Collections.unmodifiableList(_vaccineEventList);
  }
}
