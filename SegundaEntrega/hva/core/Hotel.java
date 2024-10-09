package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;

public class Hotel implements Serializable {

  private Season _season;
  private boolean _hotelChanged = false;
  private Map<String, Species> _speciesMap = new HashMap<>();
  private Map<String, Tree> _treeMap = new HashMap<>();
  private Map<String, Habitat> _habitatMap = new HashMap<>();
  private Map<String, Animal> _animals = new HashMap<>();
  private Map<String, Employee> _employees = new HashMap<>();
  private Map<String, Vaccine> _vaccines = new HashMap<>();
  
  public final Boolean getHotelState(){
    return _hotelChanged;
  }

  Season getSeason() {
    return _season;
  }

  Hotel() {
    _season = Season.PRIMAVERA;
    _hotelChanged = false;
  }

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  void importFile(String filename) 
    throws IOException, UnrecognizedEntryException, DuplicateFieldException, UnknownFieldException {
    Parser _parser = new Parser(this);
    _parser.parseFile(filename);
  }

  //Register
  public void registerSpecies(String id, String name) 
    throws DuplicateSpeciesKeyException, DuplicateSpeciesNameException {
    if(_speciesMap.containsKey(id)) throw new DuplicateSpeciesKeyException(id);
    for(Species s : _speciesMap.values()) {
      if(s.getName().equals(name)) throw new DuplicateSpeciesNameException(name);
    }
    Species species = new Species(id, name);
    addSpecies(species);
  }

  public void registerTree(String id, String name, String age, String baseDifficulty, TreeType treeType)
    throws DuplicateTreeKeyException, UnknownTreeTypeException {
    if(_treeMap.containsKey(id)) throw new DuplicateTreeKeyException(id);
    Tree tree;
    switch(treeType) {
      case TreeType.CADUCA -> tree = new Caduca(id, name, Integer.parseInt(age), Integer.parseInt(baseDifficulty), this);
      case TreeType.PERENE -> tree = new Perene(id, name, Integer.parseInt(age), Integer.parseInt(baseDifficulty), this);
      default -> throw new UnknownTreeTypeException();   
    }
    addTree(tree);
  }

  public void registerHabitat(String id, String name, String area, String[] treeIds) 
    throws DuplicateHabitatKeyException {
    Habitat habitat = registerHabitat(id, name, Integer.parseInt(area));
    for(String treeId : treeIds) {
      Tree tree = _treeMap.get(treeId);
      if(tree != null) habitat.addTree(tree);
    }
  }

  public Habitat registerHabitat(String id, String name, int area) 
    throws DuplicateHabitatKeyException{
    if(_habitatMap.containsKey(id)) throw new DuplicateHabitatKeyException(id);
    Habitat habitat = new Habitat(id, name, area);
    addHabitat(habitat);
    return habitat;
  }

  public void registerAnimal(String animalId, String name, String speciesId, String habitatId) 
    throws DuplicateAnimalKeyException, UnknownSpeciesKeyException, UnknownHabitatKeyException {
    if(_animals.containsKey(animalId)) throw new DuplicateAnimalKeyException(animalId);
    Species species = _speciesMap.get(speciesId);
    if (species == null) throw new UnknownSpeciesKeyException(speciesId);
    Habitat habitat = _habitatMap.get(habitatId);
    if (habitat == null) throw new UnknownHabitatKeyException(habitatId);
    Animal animal = new Animal(animalId, name, species, habitat);
    addAnimal(animal);
  }

  public void registerEmployee(String id, String name, String[] responsibilityIds, EmployeeType employeeType) 
    throws DuplicateEmployeeKeyException, UnknownHabitatKeyException, UnknownSpeciesKeyException {
    if(_employees.containsKey(id)) throw new DuplicateEmployeeKeyException(id);
    if(employeeType == EmployeeType.KEEPER) {
      registerKeeper(id, name, responsibilityIds);
    }
    if(employeeType == EmployeeType.VETERINARIAN) {
      registerVet(id, name, responsibilityIds);
    }
  }

  public void registerKeeper(String id, String name, String[] responsibilityIds) 
    throws UnknownHabitatKeyException {
    List<Habitat> responsibilities = new ArrayList<>();
    Habitat habitat;
    for(String responsibilityId : responsibilityIds) {
      habitat = _habitatMap.get(responsibilityId);
      if (habitat == null) throw new UnknownHabitatKeyException(responsibilityId);
      responsibilities.add(habitat);
    }
    Keeper keeper = new Keeper(id, name, responsibilities);
    addEmployee(keeper);
  }

  public void registerVet(String id, String name, String[] responsibilityIds) 
    throws UnknownSpeciesKeyException {
    List<Species> responsibilities = new ArrayList<>();
    Species species;
    for(String responsibilityId : responsibilityIds) {
      species = _speciesMap.get(responsibilityId);
      if (species == null) throw new UnknownSpeciesKeyException(responsibilityId);
      responsibilities.add(species);
    }
    Veterinarian vet = new Veterinarian(id, name, responsibilities);
    addEmployee(vet);
  }
  
  public void registerVaccine(String id, String name, String[] speciesIds) 
    throws DuplicateVaccineKeyException, UnknownVaccineKeyException {
    if(_vaccines.containsKey(id)) throw new DuplicateVaccineKeyException(id);
    Map<String, Species> speciesMap = new HashMap<>();
    Species species;
    for(String speciesId : speciesIds) {
      species = _speciesMap.get(speciesId);
      if (species == null) throw new UnknownVaccineKeyException(speciesId);
      speciesMap.put(species.getId(), species);
    }
    Vaccine vaccine = new Vaccine(id, name, speciesMap);
    addVaccine(vaccine);
  }

  //Add
  private void addSpecies(Species species) {
    _speciesMap.put(species.getId(), species);
    _hotelChanged = true;
  }
  private void addTree(Tree tree) {
    _treeMap.put(tree.getId(), tree);
    _hotelChanged = true;
  }
  private void addHabitat(Habitat habitat) {
    _habitatMap.put(habitat.getId(), habitat);
    _hotelChanged = true;
  }
  private void addAnimal(Animal animal) {
    _animals.put(animal.getId(), animal);
    _hotelChanged = true;
  }
  private void addEmployee(Employee employee) {
    _employees.put(employee.getId(), employee);
    _hotelChanged = true;
  }
  private void addVaccine(Vaccine vaccine) {
    _vaccines.put(vaccine.getId(), vaccine);
    _hotelChanged = true;
  }

  //GetAll
  public <T extends Identified> List<T> getAllEntities(Map<String, T> entities) {
    List<T> list = new ArrayList<>(entities.values());
    Collections.sort(list);
    return Collections.unmodifiableList(list);
  }
  public List<Habitat> getAllHabitats() {
    return getAllEntities(_habitatMap);  
  }
  public List<Animal> getAllAnimals() {
    return getAllEntities(_animals);   
  }
  public List<Employee> getAllEmployees() {
    return getAllEntities(_employees); 
  }
  public List<Vaccine> getAllVaccines() {
    return getAllEntities(_vaccines);  
  }

  void setChanged(Boolean state){
    _hotelChanged = state;
  }

}

