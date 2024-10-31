package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;
/**
 * Represents the Hotel entity.
 */
public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;

  /** Indicates the state of the hotel has changed. */
  private boolean _hotelChanged = false;

  /**
   * The current season in the hotel.
   * The initial season is set to spring.
   */
  private Season _season = Season.SPRING;

  /** A map that stores all habitats in the hotel, keyed by their unique identifiers. */
  private Map<String, Habitat> _habitatMap = new HashMap<>();

  /** A map that stores all trees in the hotel, keyed by their unique identifiers. */
  private Map<String, Tree> _treeMap = new HashMap<>();

  /** A map that stores all animals in the hotel, keyed by their unique identifiers. */
  private Map<String, Animal> _animalMap = new HashMap<>();

  /** A map that stores all species known to the hotel, keyed by their unique identifiers. */
  private Map<String, Species> _speciesMap = new HashMap<>();

  /** A map that stores all employees in the hotel, keyed by their unique identifiers. */
  private Map<String, Employee> _employeeMap = new HashMap<>();

  /** A map that stores all vaccines available in the hotel, keyed by their unique identifiers. */
  private Map<String, Vaccine> _vaccineMap = new HashMap<>();

  /** A list that stores all vaccine eventsthat have been performed in the hotel. */
  private List<VaccineEvent> _vaccineEventList = new ArrayList<>();

  // Hotel ---------------------------------------------------------------------
  /**
   * Sets the state of the hotel as changed or unchanged.
   * 
   * @param state the new state of the hotel (true if changed, false otherwise)
   */
  void setChanged(Boolean state){
    _hotelChanged = state;
  }

  /**
   * Returns the current state of the hotel, indicating if changes have been made.
   * 
   * @return a Boolean indicating whether the hotel's state has changed
   */
  public Boolean getHotelState(){
    return _hotelChanged;
  }

  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO error while processing the text file
   * @throws DuplicateFieldException if duplicate fields are found in the file
   * @throws UnknownFieldException if unknown fields are encountered in the file
   */
  void importFile(String filename) 
    throws IOException, UnrecognizedEntryException, InvalidInputException {
    Parser _parser = new Parser(this);
    _parser.parseFile(filename);
    setChanged(true);
  }

  /**
   * Advances the season in the hotel and updates the trees accordingly.
   * 
   * @return The ordinal value of the new season.
   */
  int advanceSeason() {
    _season = _season.next();
    for(Tree tree : _treeMap.values())
      tree.advanceSeason();
    setChanged(true);
    return _season.ordinal();
  }

  /**
   * Calculates the global satisfaction (all animals and employees) in the hotel.
   * 
   * @return The calculated global satisfaction value.
   */
  double calculateGlobalSatisfaction() {
    double satisfaction = 0;
    for(Animal animal : _animalMap.values()) {
      satisfaction += animal.calculateSatisfaction();
    }
    for(Employee employee : _employeeMap.values()) {
      satisfaction += employee.calculateSatisfaction();
    }
    return satisfaction;
  }

  // Identified ----------------------------------------------------------------
  
  /**
   * Adds an identified entity (like an animal, tree, or employee) to the provided map.
   * 
   * @param <T> The type of the identified entity.
   * @param identified The entity to be added.
   * @param map The map where the entity will be added.
   */
  private <T extends Identified> void addIdentified(T identified, Map<String, T> map) {
    map.putIfAbsent(identified.getId().toLowerCase(), identified);
    setChanged(true);
  }

  /**
   * Checks if an identified entity exists in the provided map.
   * 
   * @param <T> The type of the identified entity.
   * @param identifier The ID of the entity.
   * @param map The map where the entity is stored.
   * @return true if the entity exists, false otherwise.
   */
  private <T extends Identified> boolean containsIdentified(String identifier, Map<String, T> map) {
    return map.containsKey(identifier.toLowerCase());
  }

  /**
   * Retrieves an identified entity from the provided map.
   * 
   * @param <T> The type of the identified entity.
   * @param identifier The ID of the entity.
   * @param map The map where the entity is stored.
   * @return The entity associated with the ID, or null if not found.
   */
  private <T extends Identified> T getIdentified(String identifier, Map<String, T> map) {
    return map.get(identifier.toLowerCase());
  }

  /**
   * Retrieves all identified entities from the provided map.
   * 
   * @param <T> The type of the identified entities.
   * @param map The map containing the entities.
   * @return An unmodifiable list of all identified entities.
   */
  private <T extends Identified> List<T> getAllIdentified(Map<String, T> map) {
    List<T> list = new ArrayList<>(map.values());
    Collections.sort(list);
    return Collections.unmodifiableList(list);
  }  

  // HABITAT -------------------------------------------------------------------
  
  /**
   * Registers a new habitat in the hotel.
   * 
   * @param habitatId The ID of the habitat.
   * @param habitatName The name of the habitat.
   * @param habitatArea The area of the habitat.
   * @throws DuplicateHabitatException If a habitat with the same ID already exists.
   */
  public void registerHabitat(String habitatId, String habitatName, int habitatArea)
    throws DuplicateHabitatException {
    if(containsIdentified(habitatId, _habitatMap)) throw new DuplicateHabitatException(habitatId);
    Habitat newHabitat = new Habitat(habitatId, habitatName, habitatArea);
    addIdentified(newHabitat, _habitatMap);
    setChanged(true);
  }

  /**
   * Registers a new habitat in the hotel with the option to associate existing trees to the habitat.
   * 
   * @param habitatId The unique identifier for the habitat.
   * @param habitatName The name of the habitat.
   * @param area The area of the habitat.
   * @param treeIds An array of tree IDs to be associated with the habitat.
   * @throws InvalidInputException If the habitat already exists or if a tree ID is invalid.
   */
  void registerHabitat(String habitatId, String habitatName, int area, String[] treeIds) 
    throws InvalidInputException {
    if(containsIdentified(habitatId, _habitatMap)) 
      throw new InvalidInputException("Habitat already exists with id: " + habitatId);
    Habitat habitat = new Habitat(habitatId, habitatName, area);
    for(String id : treeIds) {
      if(!containsIdentified(id, _treeMap))
        throw new InvalidInputException("No tree with id: " + id);
    }
    for(String id : treeIds) {
      Tree tree = getIdentified(id, _treeMap);
      habitat.addTree(tree);
    }
    addIdentified(habitat, _habitatMap);
    setChanged(true);
  }

  /**
   * Retrieves all habitats in the hotel.
   * 
   * @return An unmodifiable list of all habitats.
   */
  public List<Habitat> getAllHabitats() {
    return getAllIdentified(_habitatMap);
  }

  /**
   * Retrieves all trees from a given habitat.
   * 
   * @param habitat The habitat object.
   * @return An unmodifiable list of all trees in the habitat.
   */
  public List<Tree> getAllTreesOfHabitat(Habitat habitat) {
    return habitat.getAllTrees();
  }

  /**
   * Retrieves all trees from a habitat by its ID.
   * 
   * @param habitatId The ID of the habitat.
   * @return A list of trees in the specified habitat.
   * @throws UnknownHabitatException If the habitat does not exist.
   */
  public List<Tree> getAllTreesOfHabitat(String habitatId) 
    throws UnknownHabitatException {
    Habitat habitat = getIdentified(habitatId, _habitatMap);
    if(habitat == null) throw new UnknownHabitatException(habitatId);
    return getAllTreesOfHabitat(habitat);
  }

  /**
   * Retrieves all animals from a habitat by its ID.
   * 
   * @param habitatId The ID of the habitat.
   * @return A list of animals in the specified habitat.
   * @throws UnknownHabitatException If the habitat does not exist.
   */
  public List<Animal> getAnimalsFromHabitat(String habitatId) 
    throws UnknownHabitatException {
    Habitat habitat = getIdentified(habitatId, _habitatMap);
    if(habitat == null) throw new UnknownHabitatException(habitatId);
    return habitat.getAllAnimals();
  }

  /**
   * Retrieves a habitat by its ID.
   * 
   * @param habitatId The ID of the habitat to be retrieved.
   * @return The habitat object associated with the given ID, or null if not found.
   */
  Habitat getHabitat(String habitatId) {
    return getIdentified(habitatId, _habitatMap);
  }

  /**
   * Changes the area of a given habitat.
   * 
   * @param habitatId The ID of the habitat whose area is to be changed.
   * @param area The new area value for the habitat.
   * @throws UnknownHabitatException If the habitat does not exist.
   * @throws InvalidInputException If the area value is invalid (e.g., less than or equal to 0).
   */
  public void changeHabitatArea(String habitatId, int area) 
    throws UnknownHabitatException, InvalidInputException {
    if(area <= 0) throw new InvalidInputException("Invalid area value");
    Habitat habitat = getIdentified(habitatId, _habitatMap);
    if(habitat == null) throw new UnknownHabitatException(habitatId);
    boolean changed = habitat.setArea(area);
    if(changed) setChanged(true);
  }

  /**
   * Sets the species adequacy for a specific habitat.
   * 
   * @param habitatId The ID of the habitat.
   * @param speciesId The ID of the species for which adequacy is being set.
   * @param adequacy The adequacy value to be set (e.g., positive, neutral, or negative).
   * @throws UnknownHabitatException If the habitat does not exist.
   * @throws UnknownSpeciesException If the species does not exist.
   */
  public void setHabitatSpeciesAdequacy(String habitatId, String speciesId, SpeciesAdequacy adequacy) 
    throws UnknownHabitatException, UnknownSpeciesException {
    Habitat habitat = getIdentified(habitatId, _habitatMap);
    if(habitat == null) 
      throw new UnknownHabitatException(habitatId);
    if(!containsIdentified(speciesId, _speciesMap)) 
      throw new UnknownSpeciesException(speciesId);
    boolean changed = habitat.setSpeciesAdequacy(speciesId, adequacy);
    if(changed) setChanged(true);
  }
  
  // TREE ----------------------------------------------------------------------
  /**
   * Registers a tree in a given habitat.
   * 
   * @param habitatId The ID of the habitat where the tree will be registered.
   * @param tree The tree object to be added to the habitat.
   * @throws UnknownHabitatException If the habitat does not exist.
   */
  public void registerTree(String habitatId, Tree tree) 
    throws UnknownHabitatException {
    Habitat habitat = getIdentified(habitatId, _habitatMap);
    if(habitat == null) throw new UnknownHabitatException(habitatId);
    tree.setHabitat(habitat);
    addIdentified(tree, _treeMap);
    setChanged(true);
  }
  
  /**
   * Registers a evergreen tree (TreePerene) in a given habitat.
   * 
   * @param habitatId The ID of the habitat where the tree will be registered.
   * @param treeId The ID of the new tree.
   * @param treeName The name of the new tree.
   * @param treeAge The age of the new tree.
   * @param treeDifficulty The difficulty rating of maintaining the tree.
   * @return A string representing the newly registered tree.
   * @throws DuplicateTreeException If a tree with the same ID already exists.
   * @throws UnknownHabitatException If the habitat does not exist.
   */
  public String registerPerene(String habitatId, String treeId, String treeName, int treeAge, int treeDifficulty) 
    throws DuplicateTreeException, UnknownHabitatException {
    if (containsIdentified(treeId, _treeMap)) throw new DuplicateTreeException(treeId);
    Tree newTree = new TreePerene(treeId, treeName, treeAge, treeDifficulty, _season);
    registerTree(habitatId, newTree);
    setChanged(true);
    return newTree.toString();
  }

  /**
   * Registers a new evergreen (perene) tree in the hotel.
   *
   * @param treeId The ID of the tree.
   * @param treeName The name of the tree.
   * @param age The age of the tree.
   * @param difficulty The difficulty of maintaining the tree.
   * @throws InvalidInputException If a tree with the same ID already exists.
   */
  void registerPerene(String treeId, String treeName, int age, int difficulty) 
    throws InvalidInputException {
    if(containsIdentified(treeId, _treeMap)) 
      throw new InvalidInputException("Tree already exists with id: " + treeId);
    Tree tree = new TreePerene(treeId, treeName, age, difficulty, _season);
    addIdentified(tree, _treeMap);
    setChanged(true);
  }
  
  /**
   * Registers a deciduous tree (TreeCaduca) in a given habitat.
   * 
   * @param habitatId The ID of the habitat where the tree will be registered.
   * @param treeId The ID of the new tree.
   * @param treeName The name of the new tree.
   * @param treeAge The age of the new tree.
   * @param treeDifficulty The difficulty rating of maintaining the tree.
   * @return A string representing the newly registered tree.
   * @throws DuplicateTreeException If a tree with the same ID already exists.
   * @throws UnknownHabitatException If the habitat does not exist.
   */
  public String registerCaduca(String habitatid, String treeid, String treeName, int treeAge, int treeDifficulty) 
    throws DuplicateTreeException, UnknownHabitatException {
    if (containsIdentified(treeid, _treeMap)) throw new DuplicateTreeException(treeid);
    Tree newTree = new TreeCaduca(treeid, treeName, treeAge, treeDifficulty, _season);
    registerTree(habitatid, newTree);
    setChanged(true);
    return newTree.toString();
  }

  /**
   * Registers a new deciduous (caduca) tree in the hotel.
   *
   * @param treeId The ID of the tree.
   * @param treeName The name of the tree.
   * @param age The age of the tree.
   * @param difficulty The difficulty of maintaining the tree.
   * @throws InvalidInputException If a tree with the same ID already exists.
   */
  void registerCaduca(String treeId, String treeName, int age, int difficulty) 
    throws InvalidInputException {
    if(containsIdentified(treeId, _treeMap)) throw new InvalidInputException("Tree already exists with id: " + treeId);
    Tree tree = new TreeCaduca(treeId, treeName, age, difficulty, _season);
    addIdentified(tree, _treeMap);
    setChanged(true);
  }

  // ANIMAL --------------------------------------------------------------------
  
  /**
   * Registers a new animal in the hotel.
   * 
   * @param animalId The ID of the new animal.
   * @param animalName The name of the animal.
   * @param speciesId The ID of the species the animal belongs to.
   * @param habitatId The ID of the habitat where the animal will reside.
   * @throws DuplicateAnimalException If an animal with the same ID already exists.
   * @throws UnknownHabitatException If the habitat does not exist.
   * @throws UnknownSpeciesException If the species does not exist.
   */
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
    setChanged(true);
  }

  /**
   * Returns a list of all animals registered in the hotel.
   * 
   * @return An unmodifiable list of all animals.
   */
  public List<Animal> getAllAnimals() {
    return getAllIdentified(_animalMap);
  }

  /**
   * Changes the habitat of a given animal to a new habitat.
   * 
   * @param animalId The ID of the animal whose habitat is to be changed.
   * @param habitatId The ID of the new habitat.
   * @throws UnknownAnimalException If the animal does not exist.
   * @throws UnknownHabitatException If the new habitat does not exist.
   */
  public void changeAnimalHabitat(String animalId, String habitatId) 
    throws UnknownAnimalException, UnknownHabitatException {
    Animal animal = getIdentified(animalId, _animalMap);
    if(animal == null) throw new UnknownAnimalException(animalId);
    Habitat newHabitat = getIdentified(habitatId, _habitatMap);
    if(newHabitat == null) throw new UnknownHabitatException(habitatId);
    boolean changed = animal.changeHabitat(newHabitat);
    if(changed) setChanged(true);
  }

  /**
   * Returns the satisfaction score of a given animal.
   * 
   * @param animalId The ID of the animal whose satisfaction is to be calculated.
   * @return The satisfaction score of the animal.
   * @throws UnknownAnimalException If the animal does not exist.
   */
  public double getAnimalSatisfaction(String animalId) throws UnknownAnimalException {
    Animal animal = getIdentified(animalId, _animalMap);
    if(animal == null) throw new UnknownAnimalException(animalId);
    return animal.calculateSatisfaction();
  }

  /**
 * Vaccinates a given animal with a vaccine administered by a veterinarian.
  * 
  * @param animalId The ID of the animal to be vaccinated.
  * @param employeeId The ID of the veterinarian administering the vaccine.
  * @param vaccineId The ID of the vaccine.
  * @return true if the vaccine was adequate for the animal, false otherwise.
  * @throws UnknownAnimalException If the animal does not exist.
  * @throws UnknownEmployeeException If the employee does not exist.
  * @throws UnknownVaccineException If the vaccine does not exist.
  * @throws NotAVetException If the employee is not a veterinarian.
  * @throws NotAllowedToVaccinateException If the veterinarian is not authorized to vaccinate.
  */
  public boolean vaccinateAnimal(String animalId, String employeeId, String vaccineId) 
    throws UnknownAnimalException, UnknownEmployeeException, UnknownVaccineException,
    NotAVetException, NotAllowedToVaccinateException {
    Animal animal = getIdentified(animalId, _animalMap);
    if(animal == null) throw new UnknownAnimalException(animalId);
    Employee employee = getIdentified(employeeId, _employeeMap);
    if(employee == null) throw new UnknownEmployeeException(employeeId);
    Vaccine vaccine = getIdentified(vaccineId, _vaccineMap);
    if(vaccine == null) throw new UnknownVaccineException(vaccineId);
    EmployeeVet vet = employee.isVet();
    if(vet == null) throw new NotAVetException(employeeId);
    VaccineEvent event = new VaccineEvent(vet, animal, vaccine);
    vet.addVaccineEvent(event);
    animal.addVaccineEvent(event);
    vaccine.addVaccineEvent(event);
    _vaccineEventList.add(event);
    setChanged(true);
    return event.isGood();
  }

  /**
   * Returns a list of all vaccine events associated with a given animal.
   * 
   * @param animalId The ID of the animal whose vaccine events are to be retrieved.
   * @return A list of vaccine events.
   * @throws UnknownAnimalException If the animal does not exist.
   */
  public List<VaccineEvent> getVaccinesFromAnimal(String animalId) 
    throws UnknownAnimalException {
    Animal animal = getIdentified(animalId, _animalMap);
    if(animal == null) throw new UnknownAnimalException(animalId);
    return animal.getAllVaccineEvents();
  }
  
  // SPECIES -------------------------------------------------------------------
  
  /**
   * Registers a new species in the hotel.
   * 
   * @param speciesId The ID of the new species.
   * @param speciesName The name of the new species.
   * @throws InvalidInputException If the species ID or name already exists.
   */
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
    setChanged(true);
  }
  
  /**
   * Retrieves a species by its ID.
   * 
   * @param speciesId The ID of the species to be retrieved.
   * @return The species associated with the given ID.
   */
  Species getSpecies(String speciesId) {
    return getIdentified(speciesId, _speciesMap);
  }

  // EMPLOYEE ------------------------------------------------------------------
  
  /**
   * Registers a new employee in the hotel.
   * 
   * @param newEmployee The employee object to be added.
   * @throws DuplicateEmployeeException If an employee with the same ID already exists.
   */
  public void registerEmployee(Employee newEmployee) 
    throws DuplicateEmployeeException {
    if(containsIdentified(newEmployee.getId(), _employeeMap))
      throw new DuplicateEmployeeException(newEmployee.getId());
    addIdentified(newEmployee, _employeeMap);
    setChanged(true);
  }

  /**
   * Returns the satisfaction score of a given employee.
   * 
   * @param employeeId The ID of the employee whose satisfaction is to be calculated.
   * @return The satisfaction score of the employee.
   * @throws UnknownEmployeeException If the employee does not exist.
   */
  public double getEmployeeSatisfaction(String employeeId) 
    throws UnknownEmployeeException {
    Employee employee = getIdentified(employeeId, _employeeMap);
    if(employee == null) throw new UnknownEmployeeException(employeeId);
    return employee.calculateSatisfaction();
  }

  /**
   * Returns a list of all employees in the hotel.
   * 
   * @return An unmodifiable list of all employees.
   */
  public List<Employee> getAllEmployees() {
    return getAllIdentified(_employeeMap);
  }

  /**
   * Adds a responsibility to an employee.
   * 
   * @param employeeId The ID of the employee.
   * @param responsibilityId The ID of the responsibility to be added.
   * @throws UnknownEmployeeException If the employee does not exist.
   * @throws UnknownResponsibilityException If the responsibility does not exist.
   */
  public void addEmployeeResponsibility(String employeeId, String responsibilityId) 
    throws UnknownEmployeeException, UnknownResponsibilityException {
    Employee employee = getIdentified(employeeId, _employeeMap);
    if(employee == null) throw new UnknownEmployeeException(employeeId);
    boolean changed = employee.addResponsibility(responsibilityId, this);
    if(changed) setChanged(true);
  }

  /**
   * Removes a responsibility from an employee.
   * 
   * @param employeeId The ID of the employee.
   * @param responsibilityId The ID of the responsibility to be removed.
   * @throws UnknownEmployeeException If the employee does not exist.
   * @throws UnknownResponsibilityException If the responsibility does not exist.
   */
  public void removeEmployeeResponsibility(String employeeId, String responsibilityId) 
    throws UnknownEmployeeException, UnknownResponsibilityException {
    Employee employee = getIdentified(employeeId, _employeeMap);
    if(employee == null) throw new UnknownEmployeeException(employeeId);
    boolean changed = employee.removeResponsibility(responsibilityId);
    if(changed) setChanged(true);
  }
  

  /**
   * Registers a veterinarian in the hotel.
   * 
   * @param employeeId The ID of the veterinarian.
   * @param employeeName The name of the veterinarian.
   * @throws DuplicateEmployeeException If an employee with the same ID already exists.
   */
  public void registerVet(String employeeId, String employeeName) 
    throws DuplicateEmployeeException {
    Employee newEmployee = new EmployeeVet(employeeId, employeeName);
    registerEmployee(newEmployee);
    setChanged(true);
  }

  /**
   * Registers a veterinarian and assigns responsibilities to them.
   *
   * @param employeeId The ID of the veterinarian.
   * @param employeeName The name of the veterinarian.
   * @param responsibilityIds An array of responsibility IDs to be assigned to the veterinarian.
   * @throws InvalidInputException If the employee already exists or an invalid responsibility ID is provided.
   * @throws UnknownResponsibilityException If a responsibility is unknown.
   */
  void registerVet(String employeeId, String employeeName, String[] responsibilityIds) 
    throws InvalidInputException, UnknownResponsibilityException {
    if(containsIdentified(employeeId, _employeeMap)) 
      throw new InvalidInputException("Employee already exists with id: " + employeeId);
    Employee employee = new EmployeeVet(employeeId, employeeName);
    for(String id : responsibilityIds) {
      if(!containsIdentified(id, _speciesMap))
        throw new InvalidInputException("Unknown Habitat id: " + id);
    }
    for(String id : responsibilityIds) {
      employee.addResponsibility(id, this);
    }
    addIdentified(employee, _employeeMap);
    setChanged(true);
  }

  /**
   * Returns a list of vaccine events associated with a veterinarian.
   *
   * @param employeeId The ID of the veterinarian.
   * @return A list of vaccine events.
   * @throws NotAVetException If the employee is not a veterinarian.
   * @throws UnknownEmployeeException If the employee does not exist.
   */
  public List<VaccineEvent> getVaccineEventsOfVet(String employeeId) 
    throws NotAVetException, UnknownEmployeeException {
    Employee employee = getIdentified(employeeId, _employeeMap);
    if(employee == null) throw new UnknownEmployeeException(employeeId);
    EmployeeVet vet = employee.isVet();
    if(vet == null)
      throw new NotAVetException(employeeId);
    return vet.getAllVaccineEvents();
  }

  /**
   * Registers a zookeeper in the hotel.
   *
   * @param employeeId The ID of the zookeeper.
   * @param employeeName The name of the zookeeper.
   * @throws DuplicateEmployeeException If a zookeeper with the same ID already exists.
   */
  public void registerKeeper(String employeeId, String employeeName) 
    throws DuplicateEmployeeException {
    Employee newEmployee = new EmployeeKeeper(employeeId, employeeName);
    registerEmployee(newEmployee);
    setChanged(true);
  }

  /**
   * Registers a zookeeper and assigns responsibilities to them.
   *
   * @param employeeId The ID of the zookeeper.
   * @param employeeName The name of the zookeeper.
   * @param responsibilityIds An array of responsibility IDs to be assigned to the zookeeper.
   * @throws InvalidInputException If the employee already exists or an invalid responsibility ID is provided.
   * @throws UnknownResponsibilityException If a responsibility is unknown.
   */
  void registerKeeper(String employeeId, String employeeName, String[] responsibilityIds) 
    throws InvalidInputException, UnknownResponsibilityException {
    if(containsIdentified(employeeId, _employeeMap)) 
      throw new InvalidInputException("Employee already exists with id: " + employeeId);
    Employee employee = new EmployeeKeeper(employeeId, employeeName);
    for(String id : responsibilityIds) {
      if(!containsIdentified(id, _habitatMap))
        throw new InvalidInputException("Unknown Habitat id: " + id);
    }
    for(String id : responsibilityIds) {
      employee.addResponsibility(id, this);
    }
    addIdentified(employee, _employeeMap);
    setChanged(true);
  }

  // VACCINE -------------------------------------------------------------------
  /**
   * Registers a new vaccine in the hotel.
   *
   * @param vaccineId The ID of the vaccine.
   * @param vaccineName The name of the vaccine.
   * @param speciesIdArray An array of species IDs that the vaccine is effective for.
   * @throws UnknownSpeciesException If a species is unknown.
   * @throws DuplicateVaccineException If a vaccine with the same ID already exists.
   */
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
    setChanged(true);
  }

  /**
   * Returns a list of all vaccines registered in the hotel.
   *
   * @return A list of all vaccines.
   */
  public List<Vaccine> getAllVaccines() {
    return getAllIdentified(_vaccineMap);
  }

  /**
   * Returns a list of all vaccine events that have occurred.
   *
   * @return A list of all vaccine events.
   */
  public List<VaccineEvent> getAllVaccineEvents() {
    return Collections.unmodifiableList(_vaccineEventList);
  }

  /**
   * Returns a list of all vaccine events where the vaccine was ineffective or caused damage.
   *
   * @return A list of vaccine events where the vaccine was not suitable.
   */
  public List<VaccineEvent> getBadVaccineEvents() {
    List<VaccineEvent> list = new ArrayList<>();
    for(VaccineEvent ve : _vaccineEventList) {
      if(ve.getDamage() > 0 || (ve.getDamage() == 0 && ve.isGood() == false)) list.add(ve);
    }
    return Collections.unmodifiableList(list);
  }
}
