package hva.core;

import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.exception.*;
import java.io.*;
import java.util.*;
// FIXME import classes

public class Hotel implements Serializable {
  
  private Season _season;
  private Map<String, Animal> _animals = new HashMap<>();
  private Map<String, Species> _species = new HashMap<>();
  private Map<String, Habitat> _habitats = new HashMap<>();

  Hotel() {
    _season = Season.SPRING;
  }

  Season getSeason() {
    return _season;
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

  private void addAnimal(Animal animal) {
    _animals.put(animal.id(), animal);
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
    //FIXME implement method
  }
}
