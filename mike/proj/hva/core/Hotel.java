package hva.core;

import hva.core.exception.*;
import java.io.*;
// FIXME import classes

public class Hotel implements Serializable {

  private Season _season;

  Season getSeason() {return _season;}

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

  //Register
  public void registerSpecies(String id, String name) throws DuplicateSpeciesKeyException, DuplicateSpeciesNameException {}
  public void registerTree(String id, String name, String age, String baseDifficulty, TreeType treeType) throws DuplicateTreeKeyException {}
  public void registerHabitat(String id, String name, String area, String[] treeIds) throws DuplicateHabitatKeyException, UnknownTreeKeyException {}
  public void registerAnimal(String animalId, String name, String speciesId, String habitatId) throws DuplicateAnimalKeyException, UnknownSpeciesKeyException, UnknownHabitatKeyException {}
  public void registerEmployee(String id, String name, String[] responsibilityID, EmployeeType employeeType) throws DuplicateEmployeeKeyException, UnknownSpeciesKeyException {}
  public void registerVaccine(String id, String name, String[] speciesIds) throws DuplicateVaccineKeyException, UnknownSpeciesKeyException {}
}
