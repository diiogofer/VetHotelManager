package hva.core;

import java.util.*;

public class Vaccine extends Identified{
  private Map<String, Species> _speciesMap;
  List<VaccineEvent> _vaccineEventList = new ArrayList<>();

  Vaccine(String id, String name, Map<String, Species> map) {
    super(id, name);
    _speciesMap = map;
  }

  private String speciesToString() {
    StringBuilder str = new StringBuilder();
    List<Species> list = new ArrayList<>(_speciesMap.values());
    Collections.sort(list);
    
    for ( Species s : list) {
        str.append(s.getId()).append(",");
    }

    // Check if the string builder is not empty, then remove the last comma
    if (str.length() > 0) {
        str.deleteCharAt(str.length() - 1);  // Remove the trailing comma
    }

    return str.toString();
  }

  public String toString() {
    String species = speciesToString();
    return species.length() == 0 ? 
      "VACINA|" + getId() + "|" + getName() + "|" + _vaccineEventList.size() :
      "VACINA|" + getId() + "|" + getName() + "|" + _vaccineEventList.size() + "|" + speciesToString();
  }

  boolean isGoodForSpecies(Species species) {
    return _speciesMap.containsKey(species.getId().toLowerCase());
  }

  List<Species> getGoodSpecies() {
    List<Species> list = new ArrayList<>(_speciesMap.values());
    return Collections.unmodifiableList(list);
  }

  void addVaccineEvent(VaccineEvent event) {
    _vaccineEventList.add(event);
  }
}
