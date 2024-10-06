package hva.core;

import hva.app.exception.*;
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
}
