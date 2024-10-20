package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;

  private Map<String, Habitat> _habitatMap = new HashMap<>();
  private Map<String, Tree> _treeMap = new HashMap<>();
  private Map<String, Animal> _animalMap = new HashMap<>();
  private Map<String, Species> _speciesMap = new HashMap<>();
  private Map<String, Employee> _employeeMap = new HashMap<>();
  private Map<String, Vaccine> _vaccineMap = new HashMap<>();

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
  private <T extends Identified> List<T> getAllIdentified(Map<String, T> map) {
    List<T> list = new ArrayList<>(map.values());
    Collections.sort(list);
    return Collections.unmodifiableList(list);
  }  

  // HABITAT -------------------------------------------------------------------
  public void registerHabitat(String habitatId, String habitatName, int habitatArea)
    throws DuplicateHabitatException {
    if(containsIdentified(habitatId, _habitatMap)) throw new DuplicateHabitatException(habitatId);
    Habitat newHabitat = new Habitat(habitatId, habitatName, habitatArea);
    addIdentified(newHabitat, _habitatMap);
  }
  public List<Habitat> getAllHabitats() {
    return getAllIdentified(_habitatMap);
  }
  public List<Tree> getAllTreesOfHabitat(Habitat habitat) {
    return habitat.getAllTrees();
  }
  Responsibility getHabitat(String habitatId) {
    return getIdentified(habitatId, _habitatMap);
  }
  
  // TREE ----------------------------------------------------------------------
  public void registerTree(String habitatKey, Tree tree) 
    throws UnknownHabitatException {
    Habitat habitat = getIdentified(habitatKey, _habitatMap);
    if(habitat == null) throw new UnknownHabitatException(habitatKey);
    tree.setHabitat(habitat);
    addIdentified(tree, _treeMap);
  }
  public String registerPerene(String habitatId, String treeId, String treeName, int treeAge, int treeDifficulty) 
    throws DuplicateTreeException, UnknownHabitatException {
    if (containsIdentified(treeId, _treeMap)) throw new DuplicateTreeException(treeId);
    Tree newTree = new TreePerene(treeId, treeName, treeAge, treeDifficulty);
    registerTree(habitatId, newTree);
    return newTree.toString();
  }
  public String registerCaduca(String habitatid, String treeid, String treeName, int treeAge, int treeDifficulty) 
    throws DuplicateTreeException, UnknownHabitatException {
    if (containsIdentified(treeid, _treeMap)) throw new DuplicateTreeException(treeid);
    Tree newTree = new TreeCaduca(treeid, treeName, treeAge, treeDifficulty);
    registerTree(habitatid, newTree);
    return newTree.toString();
  }

  // ANIMAL --------------------------------------------------------------------
  public void registerAnimal(String animalId, String animalName, String speciesId, String habitatId) 
    throws DuplicateAnimalException, UnknownHabitatException, UnknownSpeciesException {
    if(containsIdentified(animalId, _animalMap)) 
      throw new DuplicateAnimalException(animalId);
    Habitat habitat = getIdentified(habitatId, _habitatMap);
    if(habitat == null) throw new UnknownHabitatException(habitatId);
    Species species = getIdentified(speciesId, _speciesMap);
    if(species == null) throw new UnknownSpeciesException(speciesId);
    Animal newAnimal = new Animal(animalId, animalName, species, habitat);
    habitat.addAnimal(newAnimal);
    species.addAnimal(newAnimal);
    addIdentified(newAnimal, _animalMap);
    
  }
  public List<Animal> getAllAnimals() {
    return getAllIdentified(_animalMap);
  }
  public void changeAnimalHabitat(String animalId, String habitatId) 
    throws UnknownAnimalException, UnknownHabitatException {
    Animal animal = getIdentified(animalId, _animalMap);
    if(animal == null) throw new UnknownAnimalException(animalId);
    Habitat newHabitat = getIdentified(habitatId, _habitatMap);
    if(newHabitat == null) throw new UnknownHabitatException(habitatId);
    animal.changeHabitat(newHabitat);
  }
  public int getAnimalSatisfaction(String animalId) throws UnknownAnimalException {
    Animal animal = getIdentified(animalId, _animalMap);
    if(animal == null) throw new UnknownAnimalException(animalId);
    return animal.calculateSatisfaction();
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
  Species getSpecies(String speciesId) {
    return getIdentified(speciesId, _speciesMap);
  }

  // EMPLOYEE ------------------------------------------------------------------
  public void registerEmployee(Employee newEmployee) 
    throws DuplicateEmployeeException {
    if(containsIdentified(newEmployee.getId(), _employeeMap))
      throw new DuplicateEmployeeException(newEmployee.getId());
    addIdentified(newEmployee, _employeeMap);
  }
  public List<Employee> getAllEmployees() {
    return getAllIdentified(_employeeMap);
  }
  public void addEmployeeResponsibility(String employeeId, String responsibilityId) 
    throws UnknownEmployeeException, UnknownResponsibilityException {
    Employee employee = getIdentified(employeeId, _employeeMap);
    if(employee == null) throw new UnknownEmployeeException(employeeId);
    Responsibility resp = employee.getResponsibility(this, responsibilityId);
    if(resp == null) throw new UnknownResponsibilityException(responsibilityId);
    boolean changed = employee.addResponsibility(resp);
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

  // VACCINE -------------------------------------------------------------------
  public void registerVaccine(String vaccineId, String vaccineName, String[] speciesIdArray) 
    throws UnknownSpeciesException, DuplicateVaccineException {
    Map<String, Species> _vaccineSpeciesMap = new HashMap<>();
    if(containsIdentified(vaccineId, _vaccineMap)) throw new DuplicateVaccineException(vaccineId);
    for (String id : speciesIdArray) {
      Species species = getIdentified(id, _speciesMap);
      if (species == null) throw new UnknownSpeciesException(id);
      addIdentified(species, _vaccineSpeciesMap);
    }
    Vaccine newVaccine = new Vaccine(vaccineId, vaccineName, _vaccineSpeciesMap);
    addIdentified(newVaccine, _vaccineMap);
  }
  public List<Vaccine> getAllVaccines() {
    return getAllIdentified(_vaccineMap);
  }

  // TODO ----------------------------------------------------------------------
  void importFile(String filename) throws IOException, UnrecognizedEntryException {}
}
