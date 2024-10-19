package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;

  private Map<String, Habitat> _habitatMap = new HashMap<>();
  private Map<String, Animal> _animalMap = new HashMap<>();
  private Map<String, Species> _speciesMap = new HashMap<>();
  private Map<String, Employee> _employeeMap = new HashMap<>();

  // Identified ----------------------------------------------------------------
  private <T extends Identified> void addIdentified(T identified, Map<String, T> map) {
    map.putIfAbsent(identified.getId().toLowerCase(), identified);
  }
  private <T extends Identified> boolean containsIdentified(String id, Map<String, T> map) {
    return map.containsKey(id.toLowerCase());
  }
  private <T extends Identified> T getIdentified(String id, Map<String, T> map) {
    return map.get(id.toLowerCase());
  }
    
  // HABITAT -------------------------------------------------------------------
  public void registerHabitat(String habitatId, String habitatName, int habitatArea)
    throws DuplicateHabitatException {
    if(containsIdentified(habitatId, _habitatMap)) throw new DuplicateHabitatException(habitatId);
    Habitat newHabitat = new Habitat(habitatId, habitatName, habitatArea);
    addIdentified(newHabitat, _habitatMap);
  }

  // ANIMAL --------------------------------------------------------------------
  public void registerAnimal(String animalId, String animalName, String speciesId, String habitatId) 
    throws DuplicateAnimalException, UnknownHabitatException, UnknownSpeciesException{
    if(containsIdentified(animalId, _animalMap)) 
      throw new DuplicateAnimalException(animalId);
    if(!containsIdentified(habitatId, _habitatMap)) 
      throw new UnknownHabitatException(habitatId);
    if(!containsIdentified(speciesId, _speciesMap)) 
      throw new UnknownSpeciesException(speciesId);
    Habitat habitat = getIdentified(habitatId, _habitatMap);
    Species species = getIdentified(speciesId, _speciesMap);
    Animal newAnimal = new Animal(animalId, animalName, species, habitat);
    habitat.addAnimal(newAnimal);
    species.addAnimal(newAnimal);
    addIdentified(newAnimal, _animalMap);
    
  }

  // SPECIES -------------------------------------------------------------------
  public void registerSpecies(String speciesId, String speciesName) 
    throws InvalidInputException {
    if(containsIdentified(speciesId, _speciesMap)) 
      throw new InvalidInputException("Species with id already exists: " + speciesId);
    for(Species s : _speciesMap.values()) {
      if((s.getName().toLowerCase()).equals(speciesName.toLowerCase()))
        throw new InvalidInputException("Species with name already exists: " + speciesName);
    }
    Species newSpecies = new Species(speciesId, speciesName);
    addIdentified(newSpecies, _speciesMap);
  }

  // EMPLOYEE ------------------------------------------------------------------
  public void registerEmployee(Employee newEmployee) 
    throws DuplicateEmployeeException {
    if(containsIdentified(newEmployee.getId(), _employeeMap))
      throw new DuplicateEmployeeException(newEmployee.getId());
    addIdentified(newEmployee, _employeeMap);
  }
  public void registerVet(String employeeId, String employeeName) 
    throws DuplicateEmployeeException {
    Employee newEmployee = new EmployeeVet(employeeId, employeeName);
    registerEmployee(newEmployee);
  }
  public void registerKeeper(String employeeId, String employeeName) 
    throws DuplicateEmployeeException {
    Employee newEmployee = new EmployeeVet(employeeId, employeeName);
    registerEmployee(newEmployee);
  }

  // TODO ----------------------------------------------------------------------
  void importFile(String filename) throws IOException, UnrecognizedEntryException {}
}
