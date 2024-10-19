package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;
// FIXME import classes

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  private Map<String, Habitat> _habitatMap = new HashMap<>();
  private Map<String, Animal> _animalMap = new HashMap<>();
  private Map<String, Species> _speciesMap = new HashMap<>();
  private Map<String, Employee> _employeeMap = new HashMap<>();
  
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

  private <T extends Identified> boolean containsIdentified(String identifier, Map<String, T> identifiedMap) {
    return identifiedMap.containsKey(identifier.toLowerCase());
  }

  private <T extends Identified> T getIdentified(String identifier, Map<String, T> identifiedMap) {
    return identifiedMap.get(identifier.toLowerCase());
  }

  public void registerHabitat(String habitatId, String habitatName, int habitatArea) throws DuplicateHabitatException{
    if(containsIdentified(habitatId, _habitatMap)) {
      throw new DuplicateHabitatException(habitatId);
    }
    Habitat newHabitat = new Habitat(habitatId, habitatName, habitatArea);
    addIdentified(newHabitat, _habitatMap);
  }

  public void registerAnimal(String animalId, String animalName, String speciesId, String habitatId) 
    throws DuplicateAnimalException, UnknownHabitatException, UnknownSpeciesException{
  
    if(containsIdentified(animalId, _animalMap)) {
      throw new DuplicateAnimalException(animalId);
    }
    if(!containsIdentified(habitatId, _habitatMap)) { 
      throw new UnknownHabitatException(habitatId);
    }
    if(!containsIdentified(speciesId, _speciesMap)){ 
      throw new UnknownSpeciesException(speciesId);
    }

    Habitat habitat = getIdentified(habitatId, _habitatMap);
    Species species = getIdentified(speciesId, _speciesMap);
    Animal newAnimal = new Animal(animalId, animalName, species, habitat);
    addIdentified(newAnimal, _animalMap);
  }

  public void registerSpecies(String speciesId, String speciesName) throws InvalidInputException {
    if(containsIdentified(speciesId, _speciesMap)) {
      throw new InvalidInputException("Species with id already exists: " + speciesId);
    }
    for(Species species : _speciesMap.values()) {
      if((species.getName().toLowerCase()).equals(speciesName.toLowerCase())) {
        throw new InvalidInputException("Species with name already exists: " + speciesName);
      }
    }
    Species newSpecies = new Species(speciesId, speciesName);
    addIdentified(newSpecies, _speciesMap);
}


  public void registerEmployee(Employee newEmployee) throws DuplicateEmployeeException {
    if(containsIdentified(newEmployee.getId(), _employeeMap)) {
      throw new DuplicateEmployeeException(newEmployee.getId());
    }
    addIdentified(newEmployee, _employeeMap);
  }

  public void registerVet(String employeeId, String employeeName) throws DuplicateEmployeeException {
    Employee newEmployee = new EmployeeVet(employeeId, employeeName);
    registerEmployee(newEmployee);
  }
  
  public void registerKeeper(String employeeId, String employeeName) throws DuplicateEmployeeException {
    Employee newEmployee = new EmployeeVet(employeeId, employeeName);
    registerEmployee(newEmployee);
}

}
