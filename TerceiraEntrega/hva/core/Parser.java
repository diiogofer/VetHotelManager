package hva.core;

import java.io.*;

import hva.core.exception.*;

/**
 * The Parser class is responsible for reading input files and creating core entities in the Hotel system.
 * It processes each line of the input file and delegates the creation of entities such as species, trees,
 * habitats, animals, employees, and vaccines to the corresponding registration methods in the Hotel class.
 * 
 * <p>Each line of the input file is expected to be formatted with specific components separated by a pipe (|),
 * and the first component determines the type of entity to be created.</p>
 * 
 * @see Hotel
 */
public class Parser {
  
  /** The Hotel instance where the parsed data will be stored. */
  private Hotel _hotel;

  /**
   * Constructs a Parser for the given hotel instance.
   * 
   * @param hotel the hotel where parsed data will be registered
   */
  Parser(Hotel h) {
    _hotel = h;
  }

  /**
   * Reads a file and processes each line to create domain entities in the hotel.
   * Each line is parsed and mapped to a specific type of entity (species, tree, habitat, etc.).
   * 
   * @param filename the name of the input file
   * @throws IOException if an I/O error occurs while reading the file
   * @throws UnrecognizedEntryException if an unrecognized entry type is encountered
   * @throws InvalidInputException if any entry has an invalid input (duplicate, unknown fields, etc.)
   */
  void parseFile(String filename) 
    throws IOException, UnrecognizedEntryException, InvalidInputException {
    try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      while((line = reader.readLine()) != null) parseLine(line);
    }
  }

  /**
   * Parses a single line and creates the corresponding entity based on the entry type.
   * 
   * @param line the line to be parsed
   * @throws InvalidInputException if the entry type or data is invalid
   */
  private void parseLine(String line) throws InvalidInputException {
    String[] components = line.split("\\|");
    switch(components[0]) {
      case "ESPÉCIE"      -> parseSpecies(components);
      case "ÁRVORE"       -> parseTree(components);
      case "HABITAT"      -> parseHabitat(components);
      case "ANIMAL"       -> parseAnimal(components);
      case "TRATADOR"     -> parseEmployee(components);
      case "VETERINÁRIO"  -> parseEmployee(components);
      case "VACINA"       -> parseVaccine(components);
      default             -> throw new InvalidInputException(
                          "Tipo de entrada inválido: " + components[0]);
    }
  }

  /**
   * Parses a species entry and registers the species in the hotel.
   * 
   * @param components the components of the species entry
   * @throws InvalidInputException if a duplicate species entry is found or data is invalid
   */
  private void parseSpecies(String[] components) throws InvalidInputException {
			_hotel.registerSpecies(components[1], components[2]);
  }

  /**
   * Parses a tree entry and registers the tree in the hotel.
   * 
   * @param components the components of the tree entry
   * @throws InvalidInputException if the tree type is unknown or a duplicate tree entry is found
   */
  private void parseTree(String[] components) throws InvalidInputException {
    String treeId = components[1];
    String treeName = components[2];
    int age = Integer.parseInt(components[3]);
    int difficulty = Integer.parseInt(components[4]);
    String treeType = components[5];
      switch(treeType) {
        case "CADUCA" -> _hotel.registerCaduca(treeId, treeName, age, difficulty);
        case "PERENE" -> _hotel.registerPerene(treeId, treeName, age, difficulty);   
        default -> throw new InvalidInputException("Unknown tree type.");
      }
  }

  /**
   * Parses a habitat entry and registers the habitat in the hotel.
   * 
   * @param components the components of the habitat entry
   * @throws InvalidInputException if a duplicate habitat entry is found or data is invalid
   */
  private void parseHabitat(String[] components) throws InvalidInputException{
    String habitatId = components[1];
    String habitatName = components[2];
    int area = Integer.parseInt(components[3]);
    String[] treeIds = components.length == 4 ? new String[0] : components[4].split(",");
		_hotel.registerHabitat(habitatId, habitatName, area, treeIds);
  }

  /**
   * Parses an animal entry and registers the animal in the hotel.
   * 
   * @param components the components of the animal entry
   * @throws InvalidInputException if the animal's species or habitat is unknown or a duplicate entry is found
   */
  private void parseAnimal(String[] components) throws InvalidInputException {
    String animalId = components[1];
    String animalName = components[2];
    String speciesId = components[3];
    String habitatId = components[4];
    try {
		  _hotel.registerAnimal(animalId, animalName, speciesId, habitatId);
    } catch (DuplicateAnimalException | UnknownHabitatException | UnknownSpeciesException ex) {
      throw new InvalidInputException("Invalid animal.");
    }
  }

  /**
   * Parses an employee entry and registers the employee in the hotel.
   * 
   * @param components the components of the employee entry
   * @throws InvalidInputException if the employee type or responsibility is invalid or unknown
   */
  private void parseEmployee(String[] components) throws InvalidInputException {
    String employeeType = components[0];
    String employeeId = components[1];
    String employeeName = components[2];
    String[] responsibilityIds = components.length == 3 ? new String[0] : components[3].split(",");
    try{
      switch(employeeType) {
        case "TRATADOR" -> _hotel.registerKeeper(employeeId, employeeName, responsibilityIds);
        case "VETERINÁRIO" -> _hotel.registerVet(employeeId, employeeName, responsibilityIds);  
        default -> throw new InvalidInputException("Invalid employee type.");   
      }
    } catch (UnknownResponsibilityException ure) {
      throw new InvalidInputException(ure.getMessage());
    }
  }

  /**
   * Parses a vaccine entry and registers the vaccine in the hotel.
   * 
   * @param components the components of the vaccine entry
   * @throws InvalidInputException if the vaccine's species is unknown or a duplicate entry is found
   */
  private void parseVaccine(String[] components) throws InvalidInputException {
    String vaccineId = components[1];
    String vaccineName = components[2];
    String[] speciesIds = components.length == 4 ? components[3].split(",") : new String[0];
    try {
		  _hotel.registerVaccine(vaccineId, vaccineName, speciesIds);
    } catch (UnknownSpeciesException | DuplicateVaccineException ex) {
      throw new InvalidInputException("Invalid vaccine");
    }
  }
}
