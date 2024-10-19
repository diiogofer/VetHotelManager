package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;

public class Hotel implements Serializable {
  private Map<String, Habitat> _habitatMap = new HashMap<>();

  @Serial
  private static final long serialVersionUID = 202407081733L;

  // Identified ----------------------------------------------------------------
  private <T extends Identified> void addIdentified(T identified, Map<String, T> map) {
    map.putIfAbsent(identified.getId().toLowerCase(), identified);
  }
  private <T extends Identified> boolean containsIdentified(String id, Map<String, T> map) {
    return map.containsKey(id.toLowerCase());
  }
    
  // HABITAT -------------------------------------------------------------------
  public void registerHabitat(String habitatId, String habitatName, int habitatArea)
    throws DuplicateHabitatException {
    if(containsIdentified(habitatId, _habitatMap)) throw new DuplicateHabitatException(habitatId);
    Habitat newHabitat = new Habitat(habitatId, habitatName, habitatArea);
    addIdentified(newHabitat, _habitatMap);
  }

  // TODO ----------------------------------------------------------------------
  void importFile(String filename) throws IOException, UnrecognizedEntryException {}
}
