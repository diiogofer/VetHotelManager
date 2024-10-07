package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;
// FIXME import classes



public class Hotel implements Serializable {
  
  private Season _season;
  private Map<String, Species> _species = new HashMap<>();
  private Map<String, Tree> _trees = new HashMap<>();
  private Map<String, Habitat> _habitats = new HashMap<>();
  private Map<String, Animal> _animals = new HashMap<>();
  private Map<String, Employee> _employees = new HashMap<>();
  private Map<String, Vaccine> _vaccines = new HashMap<>();
  
  Hotel() {
    _season = Season.SPRING;
  }

  Season getSeason() {
    return _season;
  }
  
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
    Parser _parser = new Parser(this);
    _parser.parseFile(filename);
  }


  // Species Related Methods
  public void registerSpecies(String id, String name) throws DuplicateSpeciesKeyException, DuplicateSpeciesNameException {
    if(_species.containsKey(id)) throw new DuplicateSpeciesKeyException();
    for(Species s : _species.values()) {
      if(s.name().equals(name)) throw new DuplicateSpeciesNameException();
    }
    Species species = new Species(id, name);
    addSpecies(species);
  }

  private void addSpecies(Species species) {
    _species.put(species.id(), species);
  }

  // Tree Related Methods

  public void registerTree(String id, String name, String age, String baseDifficulty, TreeType treeType) throws DuplicateTreeKeyException, UnknownTreeTypeException {
    if(_trees.containsKey(id)) throw new DuplicateTreeKeyException();
    Tree tree;
    if(treeType == TreeType.CADUCA) tree = new DeciduousTree(id, name, Integer.parseInt(age), Integer.parseInt(baseDifficulty), this);
    else if(treeType == TreeType.PERENE) tree = new EvergreenTree(id, name, Integer.parseInt(age), Integer.parseInt(baseDifficulty), this);
    else throw new UnknownTreeTypeException();
    addTree(tree);
  }
  private void addTree(Tree tree) {
    _trees.put(tree.id(), tree);
  }

  // Habitat Related Methods
    public void registerHabitat(String id, String name, int area) throws DuplicateHabitatKeyException{
    if(_habitats.containsKey(id)) throw new DuplicateHabitatKeyException();
    Habitat habitat = new Habitat(id, name, area);
    addHabitat(habitat);
  }

  public void registerHabitat(String id, String name, String area, String[] treeIds) throws DuplicateHabitatKeyException, UnknownTreeKeyException {
    registerHabitat(id, name, Integer.parseInt(area));
    for(String treeId : treeIds) {
      if (!_trees.keySet().contains(treeId)) throw new UnknownTreeKeyException();
    } // !!!!!!!!!!!!!!!!!!!!!!! como é que as arvores sao associadas ao habitat?
  }

  private void addHabitat(Habitat habitat) {
    _habitats.put(habitat.id(), habitat);
  }
  
  // Animal Related Methods
  public void registerAnimal(String animald, String name, String speciesId, String habitatId) throws DuplicateAnimalKeyException, UnknownSpeciesKeyException, UnknownHabitatKeyException {
    if(_animals.containsKey(animald)) throw new DuplicateAnimalKeyException();
    Species species = _species.get(speciesId);
    if (species == null) throw new UnknownSpeciesKeyException();
    Habitat habitat = _habitats.get(habitatId);
    if (habitat == null) throw new UnknownHabitatKeyException();
    Animal animal = new Animal(animald, name, species, habitat);
    addAnimal(animal);
  }
  private void addAnimal(Animal animal) {
    _animals.put(animal.id(), animal);
  }
  
  // Employee Related Methods

  public void registerEmployee(String id, String name, String[] responsibilityIds, EmployeeType employeeType) throws DuplicateEmployeeKeyException, UnknownSpeciesKeyException, UnknownHabitatKeyException {
    if(_employees.containsKey(id)) throw new DuplicateEmployeeKeyException();
    if(employeeType == EmployeeType.KEEPER) {
      Map<String, Habitat> responsibilities = new HashMap<>();
      Habitat habitat;
      for(String responsibilityId : responsibilityIds) {
        habitat = _habitats.get(responsibilityId);
        if (habitat == null) throw new UnknownHabitatKeyException();
        responsibilities.put(habitat.id(), habitat);
      }
      Zookeeper keeper = new Zookeeper(id, name, responsibilities);
      addEmployee(keeper);
    }
    if(employeeType == EmployeeType.VETERINARIAN) {
      Map<String, Species> responsibilities = new HashMap<>();
      Species species;
      for(String responsibilityId : responsibilityIds) {
        species = _species.get(responsibilityId);
        if (species == null) throw new UnknownHabitatKeyException();
        responsibilities.put(species.id(), species);
      }
      Veterinarian vet = new Veterinarian(id, name, responsibilities);
      addEmployee(vet);
    }
  }

  private void addEmployee(Employee employee) {
    _employees.put(employee.id(), employee);
  }

  // Vaccine Related Methods
  public void registerVaccine(String id, String name, String[] speciesIds) throws DuplicateVaccineKeyException, UnknownSpeciesKeyException {
    if(_vaccines.containsKey(id)) throw new DuplicateVaccineKeyException();
    Map<String, Species> speciesMap = new HashMap<>();
    Species species;
    for(String speciesId : speciesIds) {
      species = _species.get(speciesId);
      if (species == null) throw new UnknownSpeciesKeyException();
      speciesMap.put(species.id(), species);
    }
    Vaccine vaccine = new Vaccine(id, name, speciesMap);
    addVaccine(vaccine);
  }

  private void addVaccine(Vaccine vaccine) {
    _vaccines.put(vaccine.id(), vaccine);
  }


  // Show <HotelEntity> related functions 
  private <T extends HotelEntity> List<T> getAllEntitiesSortedById(Map<String, T> entities) {
    List<T> entityList = new ArrayList<>(entities.values());
    entityList.sort((e1, e2) -> e1.id().compareTo(e2.id()));

    return entityList;
  }

  public List<Habitat> getAllHabitats() {
    return getAllEntitiesSortedById(_habitats); 
  }
  public List<Animal> getAllAnimals() {
    return getAllEntitiesSortedById(_animals);   
  }
  public List<Employee> getAllEmployees() {
    return getAllEntitiesSortedById(_employees);
  }
  public List<Vaccine> getAllVaccines() {
    return getAllEntitiesSortedById(_vaccines); 
  }
  
}