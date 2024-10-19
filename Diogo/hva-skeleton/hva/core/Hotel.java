package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;
// FIXME import classes

public class Hotel implements Serializable {
  private Map<String, Habitat> _habitatMap = new HashMap<>();

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  // FIXME define attributes
  // FIXME define contructor(s)
  // FIXME define more methods
  
  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    //FIXME implement method
  }

  private <T extends Identified> void addIdentified(T identified, Map<String, T> identifiedMap) {
      identifiedMap.putIfAbsent(identified.getId().toLowerCase(), identified);
  }

  private <T extends Identified> boolean containsIdentified(String identifier, Map<String, T> identifierMap) {
    return identifierMap.containsKey(identifier.toLowerCase());
  }

  public void registerHabitat(String habitatId, String habitatName, int habitatArea) throws DuplicateHabitatException{
    if(containsIdentified(habitatId, _habitatMap)) {
      throw new DuplicateHabitatException(habitatId);
    }
    Habitat newHabitat = new Habitat(habitatId, habitatName, habitatArea);
    addIdentified(newHabitat, _habitatMap);
  }

}
