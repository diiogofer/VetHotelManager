package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;
// FIXME import classes

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  private boolean _hotelChanged;
  private Map<String, Habitat> _habitatMap = new HashMap<>();
  private Map<String, Animal> _animalMap = new HashMap<>();
  private Map<String, Species> _speciesMap = new HashMap<>();
  private Map<String, Employee> _employeeMap = new HashMap<>();
  private Map<String, Tree> _treeMap = new HashMap<>();
  private Map<String, Vaccine> _vaccineMap = new HashMap<>();

  Hotel() {
    // _season = Season.PRIMAVERA; 
    _hotelChanged = false;
  }
  // FIXME define more methods
  
  void setChanged(Boolean state){
    _hotelChanged = state;
  }

  public final Boolean getHotelState(){
    return _hotelChanged;
  }

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
      setChanged(true);
  }

  private <T extends Identified> boolean containsIdentified(String identifier, Map<String, T> identifiedMap) {
    return identifiedMap.containsKey(identifier.toLowerCase());
  }

  private <T extends Identified> T getIdentified(String identifier, Map<String, T> identifiedMap) {
    return identifiedMap.get(identifier.toLowerCase());
  }

  private <T extends Identified> List<T> getAllIdentified(Map<String, T> map) {
    List<T> list = new ArrayList<>(map.values());
    Collections.sort(list);
    return Collections.unmodifiableList(list);
  }  

  public void registerHabitat(String habitatId, String habitatName, int habitatArea) throws DuplicateHabitatException{
    if(containsIdentified(habitatId, _habitatMap)) {
      throw new DuplicateHabitatException(habitatId);
    }
    Habitat newHabitat = new Habitat(habitatId, habitatName, habitatArea);
    addIdentified(newHabitat, _habitatMap);
  }

  public List<Habitat> getAllHabitats() {
    return getAllIdentified(_habitatMap);
  }
  public List<Tree> getAllTreesOfHabitat(Habitat habitat) {
    return habitat.getAllTrees();
  }

  public void registerAnimal(String animalId, String animalName, String speciesId, String habitatId) 
    throws DuplicateAnimalException, UnknownHabitatException, UnknownSpeciesException {
  
    if(containsIdentified(animalId, _animalMap)) {
      throw new DuplicateAnimalException(animalId);
    }
    Habitat habitat = getIdentified(habitatId, _habitatMap);
    if(habitat == null) {
      throw new UnknownHabitatException(habitatId);
    }
    Species species = getIdentified(speciesId, _speciesMap);
    if(species == null) {
      throw new UnknownSpeciesException(speciesId);
    }
    Animal newAnimal = new Animal(animalId, animalName, species, habitat);
    habitat.addAnimal(newAnimal);
    species.addAnimal(newAnimal);
    addIdentified(newAnimal, _animalMap);
  }

  public List<Animal> getAllAnimals() {
    return getAllIdentified(_animalMap);
  }

  public void changeAnimalHabitat(String animalId, String habitatId) throws UnknownAnimalException, UnknownHabitatException {
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

  public List<Employee> getAllEmployees() {
    return getAllIdentified(_employeeMap);
  }

  public void registerVet(String employeeId, String employeeName) throws DuplicateEmployeeException {
    Employee newEmployee = new EmployeeVet(employeeId, employeeName);
    registerEmployee(newEmployee);
  }
  
  public void registerKeeper(String employeeId, String employeeName) throws DuplicateEmployeeException {
    Employee newEmployee = new EmployeeVet(employeeId, employeeName);
    registerEmployee(newEmployee);
}

  public void registerTree(String habitatKey, Tree tree) throws UnknownHabitatException {
    Habitat habitat = getIdentified(habitatKey, _habitatMap);
    if(habitat == null) {
      throw new UnknownHabitatException(habitatKey);
    }
    tree.setHabitat(habitat);
    addIdentified(tree, _treeMap);
  }

  public String registerPerene(String habitatId, String treeId, String treeName, int treeAge, int treeDifficulty) 
    throws DuplicateTreeException, UnknownHabitatException {
      
    if (containsIdentified(treeId, _treeMap)){ 
      throw new DuplicateTreeException(treeId);
    }
  
    Tree newTree = new TreePerene(treeId, treeName, treeAge, treeDifficulty);
    registerTree(habitatId, newTree);
    return newTree.toString();
  }

  public String registerCaduca(String habitatId, String treeId, String treeName, int treeAge, int treeDifficulty) 
    throws DuplicateTreeException, UnknownHabitatException {
  
    if (containsIdentified(treeId, _treeMap)) { 
      throw new DuplicateTreeException(treeId);
    }
    Tree newTree = new TreeCaduca(treeId, treeName, treeAge, treeDifficulty);
    registerTree(habitatId, newTree);
    return newTree.toString();
  }

  public void registerVaccine(String vaccineId, String vaccineName, String[] speciesIdArray) 
    throws UnknownSpeciesException, DuplicateVaccineException {
    
    Map<String, Species> _vaccineSpeciesMap = new HashMap<>();
    
    if(containsIdentified(vaccineId, _vaccineMap)) {
      throw new DuplicateVaccineException(vaccineId);
    }
    for (String identifier : speciesIdArray) {
      Species species = getIdentified(identifier, _speciesMap);
      if (species == null) {
        throw new UnknownSpeciesException(identifier);
      }
      addIdentified(species, _vaccineSpeciesMap);
    }
    Vaccine newVaccine = new Vaccine(vaccineId, vaccineName, _vaccineSpeciesMap);
    addIdentified(newVaccine, _vaccineMap);
  }

  public List<Vaccine> getAllVaccines() {
    return getAllIdentified(_vaccineMap);
  }

}
