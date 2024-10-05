package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;
import hva.core.sorter.*;

public class Hotel implements Serializable {

  private Season _season;
  private Map<String, Species> _species = new HashMap<>();
  private Map<String, Tree> _trees = new HashMap<>();
  private Map<String, Habitat> _habitats = new HashMap<>();
  private Map<String, Animal> _animals = new HashMap<>();
  private Map<String, Employee> _employees = new HashMap<>();
  private Map<String, Vaccine> _vaccines = new HashMap<>();

  Season getSeason() {return _season;}

  Hotel() {_season = Season.PRIMAVERA;}

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

  //Register
  public void registerSpecies(String id, String name) throws DuplicateSpeciesKeyException, DuplicateSpeciesNameException {
    if(_species.containsKey(id)) throw new DuplicateSpeciesKeyException();
    for(Species s : _species.values()) {
      if(s.getName().equals(name)) throw new DuplicateSpeciesNameException();
    }
    Species species = new Species(id, name);
    addSpecies(species);
  }
  public void registerTree(String id, String name, String age, String baseDifficulty, TreeType treeType) throws DuplicateTreeKeyException, UnknownTreeTypeException {
    if(_trees.containsKey(id)) throw new DuplicateTreeKeyException();
    Tree tree;
    if(treeType == TreeType.CADUCA) tree = new Caduca(id, name, Integer.parseInt(age), Integer.parseInt(baseDifficulty), this);
    else if(treeType == TreeType.PERENE) tree = new Perene(id, name, Integer.parseInt(age), Integer.parseInt(baseDifficulty), this);
    else throw new UnknownTreeTypeException();
    addTree(tree);
  }
  public void registerHabitat(String id, String name, String area, String[] treeIds) throws DuplicateHabitatKeyException, UnknownTreeKeyException {
    registerHabitat(id, name, Integer.parseInt(area));
    for(String treeId : treeIds) {
      if (!_trees.keySet().contains(treeId)) throw new UnknownTreeKeyException();
    }
  }
  public void registerHabitat(String id, String name, int area) throws DuplicateHabitatKeyException{
    if(_habitats.containsKey(id)) throw new DuplicateHabitatKeyException();
    Habitat habitat = new Habitat(id, name, area);
    addHabitat(habitat);
  }
  public void registerAnimal(String animald, String name, String speciesId, String habitatId) throws DuplicateAnimalKeyException, UnknownSpeciesKeyException, UnknownHabitatKeyException {
    if(_animals.containsKey(animald)) throw new DuplicateAnimalKeyException();
    Species species = _species.get(speciesId);
    if (species == null) throw new UnknownSpeciesKeyException();
    Habitat habitat = _habitats.get(habitatId);
    if (habitat == null) throw new UnknownHabitatKeyException();
    Animal animal = new Animal(animald, name, species, habitat);
    addAnimal(animal);
  }
  public void registerEmployee(String id, String name, String[] responsibilityIds, EmployeeType employeeType) throws DuplicateEmployeeKeyException, UnknownSpeciesKeyException, UnknownHabitatKeyException {
    if(_employees.containsKey(id)) throw new DuplicateEmployeeKeyException();
    if(employeeType == EmployeeType.KEEPER) {
      Map<String, Habitat> responsibilities = new HashMap<>();
      Habitat habitat;
      for(String responsibilityId : responsibilityIds) {
        habitat = _habitats.get(responsibilityId);
        if (habitat == null) throw new UnknownHabitatKeyException();
        responsibilities.put(habitat.getId(), habitat);
      }
      Keeper keeper = new Keeper(id, name, responsibilities);
      addEmployee(keeper);
    }
    if(employeeType == EmployeeType.VETERINARIAN) {
      Map<String, Species> responsibilities = new HashMap<>();
      Species species;
      for(String responsibilityId : responsibilityIds) {
        species = _species.get(responsibilityId);
        if (species == null) throw new UnknownHabitatKeyException();
        responsibilities.put(species.getId(), species);
      }
      Veterinarian vet = new Veterinarian(id, name, responsibilities);
      addEmployee(vet);
    }
  }
  public void registerVaccine(String id, String name, String[] speciesIds) throws DuplicateVaccineKeyException, UnknownSpeciesKeyException {
    if(_vaccines.containsKey(id)) throw new DuplicateVaccineKeyException();
    Map<String, Species> speciesMap = new HashMap<>();
    Species species;
    for(String speciesId : speciesIds) {
      species = _species.get(speciesId);
      if (species == null) throw new UnknownSpeciesKeyException();
      speciesMap.put(species.getId(), species);
    }
    Vaccine vaccine = new Vaccine(id, name, speciesMap);
    addVaccine(vaccine);
    
  }

  //Add
  private void addSpecies(Species species) {_species.put(species.getId(), species);}
  private void addTree(Tree tree) {_trees.put(tree.getId(), tree);}
  private void addHabitat(Habitat habitat) {_habitats.put(habitat.getId(), habitat);}
  private void addAnimal(Animal animal) {_animals.put(animal.getId(), animal);}
  private void addEmployee(Employee employee) {_employees.put(employee.getId(), employee);}
  private void addVaccine(Vaccine vaccine) {_vaccines.put(vaccine.getId(), vaccine);}

  //GetAll
  public <T extends Identifiable> List<T> getAllEntities(Map<String, T> entities) {
    List<T> list = new ArrayList<>(entities.values());
    Collections.sort(list, new SortById<T>());
    return Collections.unmodifiableList(list);
  }
  public List<Habitat> getAllHabitats() {
    return getAllEntities(_habitats);  
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
  // public List<Habitat> getAllHabitats() {
  //   List<Habitat> list = new ArrayList<>(_habitats.values());
  //   Collections.sort(list, new SortById<Habitat>()); 
  //   return Collections.unmodifiableList(list);
  // }
  // public List<Animal> getAllAnimals() {
  //   List<Animal> list = new ArrayList<>(_animals.values());
  //   Collections.sort(list, new SortById<Animal>());
  //   return Collections.unmodifiableList(list);
  // }
  // public List<Employee> getAllEmployees() {
  //   List<Employee> list = new ArrayList<>(_employees.values());
  //   Collections.sort(list, new SortById<Employee>()); 
  //   return Collections.unmodifiableList(list);
  // }
  // public List<Vaccine> getAllVaccines() {
  //   List<Vaccine> list = new ArrayList<>(_vaccines.values());
  //   Collections.sort(list, new SortById<Vaccine>()); 
  //   return Collections.unmodifiableList(list);
  // }
}

