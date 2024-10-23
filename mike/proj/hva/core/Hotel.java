package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;

  /** Indicates whether the state of the hotel has changed. */
  private boolean _hotelChanged = false;
  private Season _season = Season.SPRING;
  private List<SeasonObserver> _seasonObserverList = new ArrayList<>();

  private Map<String, Habitat> _habitatMap = new HashMap<>();
  private Map<String, Tree> _treeMap = new HashMap<>();
  private Map<String, Animal> _animalMap = new HashMap<>();
  private Map<String, Species> _speciesMap = new HashMap<>();
  private Map<String, Employee> _employeeMap = new HashMap<>();
  private Map<String, Vaccine> _vaccineMap = new HashMap<>();
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
   * @throws IOException if there is an IO erro while processing the text file
   * @throws DuplicateFieldException if duplicate fields are found in the file
   * @throws UnknownFieldException if unknown fields are encountered in the file
   */
  void importFile(String filename) 
    throws IOException, UnrecognizedEntryException, InvalidInputException {
    Parser _parser = new Parser(this);
    _parser.parseFile(filename);
    setChanged(true);
  }
  int advanceSeason() {
    _season = _season.next();
    notifySeasonObservers();
    return _season.ordinal();
  }
  int calculateGlobalSatisfaction() {
    double satisfaction = 0;
    for(Animal a : _animalMap.values()) {
      satisfaction += a.calculateSatisfaction();
    }
    for(Employee e : _employeeMap.values()) {
      satisfaction += e.calculateSatisfaction();
    }
    return (int) Math.round(satisfaction);
  }
  
  private void subscribeSeasonObserver(SeasonObserver obs) {
    _seasonObserverList.add(obs);
  }
  private void unsubscribeSeasonObserver(SeasonObserver obs) {
    _seasonObserverList.remove(obs);
  }
  private void notifySeasonObservers() {
    for (SeasonObserver obs : _seasonObserverList) {
      obs.updateSeason();
    }
  }

  // Identified ----------------------------------------------------------------
  private <T extends Identified> void addIdentified(T identified, Map<String, T> map) {
    map.putIfAbsent(identified.getId().toLowerCase(), identified);
    setChanged(true);
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
    setChanged(true);
  }
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
  public List<Habitat> getAllHabitats() {
    return getAllIdentified(_habitatMap);
  }
  public List<Tree> getAllTreesOfHabitat(Habitat habitat) {
    return habitat.getAllTrees();
  }
  public List<Tree> getAllTreesOfHabitat(String habitatId) 
    throws UnknownHabitatException {
    Habitat habitat = getIdentified(habitatId, _habitatMap);
    if(habitat == null) throw new UnknownHabitatException(habitatId);
    return getAllTreesOfHabitat(habitat);
  }
  public List<Animal> getAnimalsFromHabitat(String habitatId) 
    throws UnknownHabitatException {
    Habitat habitat = getIdentified(habitatId, _habitatMap);
    if(habitat == null) throw new UnknownHabitatException(habitatId);
    return habitat.getAllAnimals();
  }
  Habitat getHabitat(String habitatId) {
    return getIdentified(habitatId, _habitatMap);
  }
  public void changeHabitatArea(String habitatId, int area) 
    throws UnknownHabitatException, InvalidInputException {
    if(area <= 0) throw new InvalidInputException("Invalid area value");
    Habitat habitat = getIdentified(habitatId, _habitatMap);
    if(habitat == null) throw new UnknownHabitatException(habitatId);
    boolean changed = habitat.setArea(area);
    if(changed) setChanged(true);
  }
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
  public void registerTree(String habitatId, Tree tree) 
    throws UnknownHabitatException {
    Habitat habitat = getIdentified(habitatId, _habitatMap);
    if(habitat == null) throw new UnknownHabitatException(habitatId);
    tree.setHabitat(habitat);
    addIdentified(tree, _treeMap);
    subscribeSeasonObserver(tree);
    setChanged(true);
  }
  
  public String registerPerene(String habitatId, String treeId, String treeName, int treeAge, int treeDifficulty) 
    throws DuplicateTreeException, UnknownHabitatException {
    if (containsIdentified(treeId, _treeMap)) throw new DuplicateTreeException(treeId);
    Tree newTree = new TreePerene(treeId, treeName, treeAge, treeDifficulty, _season);
    registerTree(habitatId, newTree);
    setChanged(true);
    return newTree.toString();
  }
  void registerPerene(String treeId, String treeName, int age, int difficulty) 
    throws InvalidInputException {
    if(containsIdentified(treeId, _treeMap)) 
      throw new InvalidInputException("Tree already exists with id: " + treeId);
    Tree tree = new TreePerene(treeId, treeName, age, difficulty, _season);
    addIdentified(tree, _treeMap);
    setChanged(true);
  }
  
  public String registerCaduca(String habitatid, String treeid, String treeName, int treeAge, int treeDifficulty) 
    throws DuplicateTreeException, UnknownHabitatException {
    if (containsIdentified(treeid, _treeMap)) throw new DuplicateTreeException(treeid);
    Tree newTree = new TreeCaduca(treeid, treeName, treeAge, treeDifficulty, _season);
    registerTree(habitatid, newTree);
    setChanged(true);
    return newTree.toString();
  }
  void registerCaduca(String treeId, String treeName, int age, int difficulty) 
    throws InvalidInputException {
    if(containsIdentified(treeId, _treeMap)) throw new InvalidInputException("Tree already exists with id: " + treeId);
    Tree tree = new TreeCaduca(treeId, treeName, age, difficulty, _season);
    addIdentified(tree, _treeMap);
    setChanged(true);
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
    setChanged(true);
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
    boolean changed = animal.changeHabitat(newHabitat);
    if(changed) setChanged(true);
  }
  public int getAnimalSatisfaction(String animalId) throws UnknownAnimalException {
    Animal animal = getIdentified(animalId, _animalMap);
    if(animal == null) throw new UnknownAnimalException(animalId);
    return animal.calculateSatisfaction();
  }
  /**
   * 
   * @param animalId
   * @param vetId
   * @param vaccineId
   * @return true if vaccine was adequate to the animal
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
  public List<VaccineEvent> getVaccinesFromAnimal(String animalId) 
    throws UnknownAnimalException {
    Animal animal = getIdentified(animalId, _animalMap);
    if(animal == null) throw new UnknownAnimalException(animalId);
    return animal.getAllVaccineEvents();
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
    setChanged(true);
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
    setChanged(true);
  }
  public int getEmployeeSatisfaction(String employeeId) 
    throws UnknownEmployeeException {
    Employee employee = getIdentified(employeeId, _employeeMap);
    if(employee == null) throw new UnknownEmployeeException(employeeId);
    return (int) Math.round(employee.calculateSatisfaction());
  }
  public List<Employee> getAllEmployees() {
    return getAllIdentified(_employeeMap);
  }
  public void addEmployeeResponsibility(String employeeId, String responsibilityId) 
    throws UnknownEmployeeException, UnknownResponsibilityException {
    Employee employee = getIdentified(employeeId, _employeeMap);
    if(employee == null) throw new UnknownEmployeeException(employeeId);
    boolean changed = employee.addResponsibility(responsibilityId, this);
    if(changed) setChanged(true);
  }
  public void removeEmployeeResponsibility(String employeeId, String responsibilityId) 
    throws UnknownEmployeeException, UnknownResponsibilityException {
    Employee employee = getIdentified(employeeId, _employeeMap);
    if(employee == null) throw new UnknownEmployeeException(employeeId);
    boolean changed = employee.removeResponsibility(responsibilityId);
    if(changed) setChanged(true);
  }
  
  public void registerVet(String employeeId, String employeeName) 
    throws DuplicateEmployeeException {
    Employee newEmployee = new EmployeeVet(employeeId, employeeName);
    registerEmployee(newEmployee);
    setChanged(true);
  }
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
  public List<VaccineEvent> getVaccineEventsOfVet(String employeeId) 
    throws NotAVetException, UnknownEmployeeException {
    Employee employee = getIdentified(employeeId, _employeeMap);
    if(employee == null) throw new UnknownEmployeeException(employeeId);
    EmployeeVet vet = employee.isVet();
    if(vet == null)
      throw new NotAVetException(employeeId);
    return vet.getAllVaccineEvents();
  }

  public void registerKeeper(String employeeId, String employeeName) 
    throws DuplicateEmployeeException {
    Employee newEmployee = new EmployeeKeeper(employeeId, employeeName);
    registerEmployee(newEmployee);
    setChanged(true);
  }
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
  public List<Vaccine> getAllVaccines() {
    return getAllIdentified(_vaccineMap);
  }
  public List<VaccineEvent> getAllVaccineEvents() {
    return Collections.unmodifiableList(_vaccineEventList);
  }
  public List<VaccineEvent> getBadVaccineEvents() {
    List<VaccineEvent> list = new ArrayList<>();
    for(VaccineEvent ve : _vaccineEventList) {
      if(ve.getDamage() > 0) list.add(ve);
    }
    return Collections.unmodifiableList(list);
  }
}
