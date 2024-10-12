package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;

/**
 * Represents a Hotel that manages various core entities such as animals, habitats, species, 
 * trees, employees, and vaccines. The hotel tracks changes in state, handles registration of 
 * new entities, and maintains references to all entities in maps.
 * 
 * <p>The Hotel class also provides methods for importing data from files and retrieving lists 
 * of all entities it manages.</p>
 * 
 * <p>This class implements Serializable to allow its instances to be serialized </p>
 * 
 * @see Habitat
 * @see Species
 * @see Tree
 * @see Animal
 * @see Employee
 * @see Vaccine
 */
public class Hotel implements Serializable {

  /** The current season in the hotel. */
  private Season _season;

  /** Indicates whether the state of the hotel has changed. */
  private boolean _hotelChanged = false;

  /** Map of species registered in the hotel, keyed by their unique identifiers. */
  private Map<String, Species> _speciesMap = new HashMap<>();

  /** Map of trees registered in the hotel, keyed by their unique identifiers. */
  private Map<String, Tree> _treeMap = new HashMap<>();

  /** Map of habitats registered in the hotel, keyed by their unique identifiers. */
  private Map<String, Habitat> _habitatMap = new HashMap<>();
  
  /** Map of animals registered in the hotel, keyed by their unique identifiers. */
  private Map<String, Animal> _animalMap = new HashMap<>();
  
  /** Map of employees registered in the hotel, keyed by their unique identifiers. */
  private Map<String, Employee> _employeeMap = new HashMap<>();

  /** Map of vaccines registered in the hotel, keyed by their unique identifiers. */
  private Map<String, Vaccine> _vaccineMap = new HashMap<>();
  

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * Constructs a Hotel with the default season set to "PRIMAVERA".
   */
  Hotel() {
    _season = Season.PRIMAVERA;
    _hotelChanged = false;
  }


  /**
   * Returns the current state of the hotel, indicating if changes have been made.
   * 
   * @return a Boolean indicating whether the hotel's state has changed
   */
  public final Boolean getHotelState(){
    return _hotelChanged;
  }

  /**
   * Returns the current season in the hotel.
   * 
   * @return the current season
   */
  Season getSeason() {
    return _season;
  }

  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   * @throws DuplicateFieldException if duplicate fields are found in the file
   * @throws UnknownFieldException if unknown fields are encountered in the file
   */
  void importFile(String filename) 
    throws IOException, UnrecognizedEntryException, DuplicateFieldException, UnknownFieldException {
    Parser _parser = new Parser(this);
    _parser.parseFile(filename);
  }

  /**
   * Registers a new species with the given ID and name.
   * 
   * @param id the unique identifier of the species
   * @param name the name of the species
   * @throws DuplicateSpeciesKeyException if the species ID is already registered
   * @throws DuplicateSpeciesNameException if the species name is already registered
   */
  public void registerSpecies(String id, String name) 
    throws DuplicateSpeciesKeyException, DuplicateSpeciesNameException {
    if(_speciesMap.containsKey(id.toLowerCase())) throw new DuplicateSpeciesKeyException(id);
    for(Species s : _speciesMap.values()) {
      if(s.getName().equals(name)) throw new DuplicateSpeciesNameException(name);
    }
    Species species = new Species(id, name);
    addIdentified(_speciesMap, species);
  }

  /**
   * Registers a new tree with the given ID, name, age, base difficulty, and type.
   * 
   * @param id the unique identifier of the tree
   * @param name the name of the tree
   * @param age the age of the tree
   * @param baseDifficulty the base cleaning difficulty of the tree
   * @param treeType the type of the tree
   * @throws DuplicateTreeKeyException if the tree ID is already registered
   * @throws UnknownTreeTypeException if the tree type is not recognized
   */  
  public void registerTree(String id, String name, String age, String baseDifficulty, TreeType treeType)
    throws DuplicateTreeKeyException, UnknownTreeTypeException {
    if(_treeMap.containsKey(id.toLowerCase())) throw new DuplicateTreeKeyException(id);
    Tree tree;
    switch(treeType) {
      case TreeType.CADUCA -> tree = new Caduca(id, name, Integer.parseInt(age), Integer.parseInt(baseDifficulty), this);
      case TreeType.PERENE -> tree = new Perene(id, name, Integer.parseInt(age), Integer.parseInt(baseDifficulty), this);
      default -> throw new UnknownTreeTypeException();   
    }
    addIdentified(_treeMap, tree);
  }

  /**
   * Registers a new habitat with the given ID, name, area, and tree IDs.
   * Used for import structure that has pre-defined tree ID array.
   * 
   * @param id the unique identifier of the habitat
   * @param name the name of the habitat
   * @param area the area of the habitat
   * @param treeIds the IDs of the trees to be added to the habitat
   * @throws DuplicateHabitatKeyException if the habitat ID is already registered
   */  
  public void registerHabitat(String id, String name, String area, String[] treeIds) 
    throws DuplicateHabitatKeyException {
    Habitat habitat = registerHabitat(id, name, Integer.parseInt(area));
    for(String treeId : treeIds) {
      Tree tree = _treeMap.get(treeId.toLowerCase());
      if(tree != null) habitat.addTree(tree);
    }
  }

  /**
   * Registers a new habitat with the given ID, name, and area.
   * 
   * @param id the unique identifier of the habitat
   * @param name the name of the habitat
   * @param area the area of the habitat
   * @return the newly registered habitat
   * @throws DuplicateHabitatKeyException if a habitat with the same ID already exists
   */
  public Habitat registerHabitat(String id, String name, int area) 
    throws DuplicateHabitatKeyException{
    if(_habitatMap.containsKey(id.toLowerCase())) throw new DuplicateHabitatKeyException(id);
    Habitat habitat = new Habitat(id, name, area);
    addIdentified(_habitatMap, habitat);
    return habitat;
  }

  /**
   * Registers a new animal with the given ID, name, species ID, and habitat ID.
   * 
   * @param animalId the unique identifier of the animal
   * @param name the name of the animal
   * @param speciesId the unique identifier of the species
   * @param habitatId the unique identifier of the habitat
   * @throws DuplicateAnimalKeyException if an animal with the same ID already exists
   * @throws UnknownSpeciesKeyException if the species ID is not recognized
   * @throws UnknownHabitatKeyException if the habitat ID is not recognized
   */  
  public void registerAnimal(String animalId, String name, String speciesId, String habitatId) 
    throws DuplicateAnimalKeyException, UnknownSpeciesKeyException, UnknownHabitatKeyException {
    if(_animalMap.containsKey(animalId.toLowerCase())) 
      throw new DuplicateAnimalKeyException(animalId);
    Habitat habitat = _habitatMap.get(habitatId.toLowerCase());
    if (habitat == null) 
      throw new UnknownHabitatKeyException(habitatId);
    Species species = _speciesMap.get(speciesId.toLowerCase());
    if (species == null) 
      throw new UnknownSpeciesKeyException(speciesId);
    Animal animal = new Animal(animalId, name, species, habitat);
    addIdentified(_animalMap, animal);
  }

  /**
   * Registers a new employee with the given ID, name, responsibilities, and employee type.
   * 
   * @param id the unique identifier of the employee
   * @param name the name of the employee
   * @param responsibilityIds an array of IDs representing the employee's responsibilities
   * @param employeeType the type of the employee
   * @throws DuplicateEmployeeKeyException if an employee with the same ID already exists
   * @throws UnknownHabitatKeyException if a habitat ID in responsibilities is not recognized (for keepers)
   * @throws UnknownSpeciesKeyException if a species ID in responsibilities is not recognized (for veterinarians)
   */ 
  public void registerEmployee(String id, String name, String[] responsibilityIds, EmployeeType employeeType) 
    throws DuplicateEmployeeKeyException, UnknownHabitatKeyException, UnknownSpeciesKeyException {
    if(_employeeMap.containsKey(id.toLowerCase())) 
      throw new DuplicateEmployeeKeyException(id);
    if(employeeType == EmployeeType.KEEPER) {
      registerKeeper(id, name, responsibilityIds);
    }
    if(employeeType == EmployeeType.VETERINARIAN) {
      registerVet(id, name, responsibilityIds);
    }
  }

  /**
   * Registers a new keeper employee.
   * 
   * @param id the unique identifier of the keeper
   * @param name the name of the keeper
   * @param responsibilityIds an array of habitat IDs representing the keeper's responsibilities
   * @throws UnknownHabitatKeyException if a habitat ID is not recognized
   */  
  public void registerKeeper(String id, String name, String[] responsibilityIds) 
    throws UnknownHabitatKeyException {
    List<Habitat> responsibilities = new ArrayList<>();
    Habitat habitat;
    for(String responsibilityId : responsibilityIds) {
      habitat = _habitatMap.get(responsibilityId.toLowerCase());
      if (habitat == null) throw new UnknownHabitatKeyException(responsibilityId);
      responsibilities.add(habitat);
    }
    Keeper keeper = new Keeper(id, name, responsibilities);
    addIdentified(_employeeMap, keeper);
  }

  /**
   * Registers a new veterinarian employee.
   * 
   * @param id the unique identifier of the veterinarian
   * @param name the name of the veterinarian
   * @param responsibilityIds an array of species IDs representing the veterinarian's responsibilities
   * @throws UnknownSpeciesKeyException if a species ID is not recognized
   */
  public void registerVet(String id, String name, String[] responsibilityIds) 
    throws UnknownSpeciesKeyException {
    List<Species> responsibilities = new ArrayList<>();
    Species species;
    for(String responsibilityId : responsibilityIds) {
      species = _speciesMap.get(responsibilityId.toLowerCase());
      if (species == null) throw new UnknownSpeciesKeyException(responsibilityId);
      responsibilities.add(species);
    }
    Veterinarian vet = new Veterinarian(id, name, responsibilities);
    addIdentified(_employeeMap, vet);
  }
  
  /**
   * Registers a new vaccine with the given ID, name, and applicable species.
   * 
   * @param id the unique identifier of the vaccine
   * @param name the name of the vaccine
   * @param speciesIds an array of species IDs for which the vaccine is applicable
   * @throws DuplicateVaccineKeyException if a vaccine with the same ID already exists
   * @throws UnknownVaccineKeyException if a vaccine is not recognized
   */
  public void registerVaccine(String id, String name, String[] speciesIds) 
    throws DuplicateVaccineKeyException, UnknownVaccineKeyException {
    if(_vaccineMap.containsKey(id.toLowerCase())) throw new DuplicateVaccineKeyException(id);
    Map<String, Species> speciesMap = new HashMap<>();
    Species species;
    for(String speciesId : speciesIds) {
      species = _speciesMap.get(speciesId.toLowerCase());
      if (species == null) throw new UnknownVaccineKeyException(speciesId);
      speciesMap.put(species.getId(), species);
    }
    Vaccine vaccine = new Vaccine(id, name, speciesMap);
    addIdentified(_vaccineMap, vaccine);
  }

  /**
   * Adds an identified entity to the corresponding map and marks the hotel state as changed.
   * 
   * @param <T> the type of the identified entity
   * @param map the map to add the entity to
   * @param identified the identified entity to be added
   */
  private <T extends Identified> void addIdentified(Map<String, T> map, T identified) {
    map.putIfAbsent(identified.getId().toLowerCase(), identified);
    _hotelChanged = true;
  }
  

  /**
   * Returns a sorted list of all identified entities in the given map.
   * 
   * @param <T> the type of the identified entity
   * @param map the map containing the identified entities
   * @return a sorted unmodifiable list of all T identified entities 
   */
  public <T extends Identified> List<T> getAllIdentified(Map<String, T> map) {
    List<T> list = new ArrayList<>(map.values());
    Collections.sort(list);
    return Collections.unmodifiableList(list);
  }

  /**
   * Returns a list of all habitats registered in the hotel.
   * 
   * @return a list of all habitats
   */
  public List<Habitat> getAllHabitats() {
    return getAllIdentified(_habitatMap);  
  }

  /**
   * Returns a list of all animals registered in the hotel.
   * 
   * @return a list of all animals
   */
  public List<Animal> getAllAnimals() {
    return getAllIdentified(_animalMap);   
  }

  /**
   * Returns a list of all employees registered in the hotel.
   * 
   * @return a list of all employees
   */  
  public List<Employee> getAllEmployees() {
    return getAllIdentified(_employeeMap); 
  }

  /**
   * Returns a list of all vaccines registered in the hotel.
   * 
   * @return a list of all vaccines
   */
  public List<Vaccine> getAllVaccines() {
    return getAllIdentified(_vaccineMap);  
  }

  /**
   * Sets the state of the hotel as changed or unchanged.
   * 
   * @param state the new state of the hotel (true if changed, false otherwise)
   */
  void setChanged(Boolean state){
    _hotelChanged = state;
  }

}

