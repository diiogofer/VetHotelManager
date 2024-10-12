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
   * @throws DuplicateFieldException if a duplicate entry is found
   * @throws UnknownFieldException if an unknown field or type is encountered
   */
  void parseFile(String filename) throws IOException, UnrecognizedEntryException, DuplicateFieldException, UnknownFieldException {
    try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      while((line = reader.readLine()) != null) parseLine(line);
    }
  }

  /**
   * Parses a single line and creates the corresponding entity based on the entry type.
   * 
   * @param line the line to be parsed
   * @throws DuplicateFieldException if a duplicate entry is found
   * @throws UnknownFieldException if an unknown field or type is encountered
   * @throws UnrecognizedEntryException if the entry type is not recognized
   */
  private void parseLine(String line) throws DuplicateFieldException, UnknownFieldException, UnrecognizedEntryException {
    String[] components = line.split("\\|");
    switch(components[0]) {
      case "ESPÉCIE"      -> parseSpecies(components);
      case "ÁRVORE"       -> parseTree(components);
      case "HABITAT"      -> parseHabitat(components);
      case "ANIMAL"       -> parseAnimal(components);
      case "TRATADOR"     -> parseEmployee(components);
      case "VETERINÁRIO"  -> parseEmployee(components);
      case "VACINA"       -> parseVaccine(components);
      default             -> throw new UnrecognizedEntryException(
                          "tipo de entrada inválido: " + components[0]);
    }
  }

  /**
   * Parses a species entry and registers the species in the hotel.
   * 
   * @param components the components of the species entry
   * @throws DuplicateFieldException if a duplicate species entry is found
   */
  private void parseSpecies(String[] components) throws DuplicateFieldException {
    try {
			_hotel.registerSpecies(components[1], components[2]);
		} catch (DuplicateSpeciesKeyException | DuplicateSpeciesNameException ex) {
			throw new DuplicateFieldException(ex.getMessage(), ex);
		}
  }

  /**
   * Parses a tree entry and registers the tree in the hotel.
   * 
   * @param components the components of the tree entry
   * @throws UnknownFieldException if the tree type is unknown
   * @throws DuplicateFieldException if a duplicate tree entry is found
   */
  private void parseTree(String[] components) throws UnknownFieldException, DuplicateFieldException {
    TreeType treeType;
    switch(components[5]) {
      case "CADUCA" -> treeType = TreeType.CADUCA;
      case "PERENE" -> treeType = TreeType.PERENE;   
      default -> throw new UnknownFieldException("Unknown tree type.");
		}
		try {
    	_hotel.registerTree(components[1], components[2], components[3], components[4], treeType);
		} catch (DuplicateTreeKeyException dtke) {
			throw new DuplicateFieldException(dtke.getMessage(), dtke);
		} catch (UnknownTreeTypeException utte) {
			throw new UnknownFieldException(utte.getMessage(), utte);
		}
  }

  /**
   * Parses a habitat entry and registers the habitat in the hotel.
   * 
   * @param components the components of the habitat entry
   * @throws DuplicateFieldException if a duplicate habitat entry is found
   */
  private void parseHabitat(String[] components) throws DuplicateFieldException {
    String[] treeIds = components.length == 4 ? new String[0] : components[4].split(",");
    try {
			_hotel.registerHabitat(components[1], components[2], components[3], treeIds);
		} catch (DuplicateHabitatKeyException dhke) {
			throw new DuplicateFieldException(dhke.getMessage(), dhke);
		}
  }

  /**
   * Parses an animal entry and registers the animal in the hotel.
   * 
   * @param components the components of the animal entry
   * @throws UnknownFieldException if the species or habitat is unknown
   * @throws DuplicateFieldException if a duplicate animal entry is found
   */
  private void parseAnimal(String[] components) throws UnknownFieldException, DuplicateFieldException {
  	try{
			_hotel.registerAnimal(components[1], components[2], components[3], components[4]);
		} catch (DuplicateAnimalKeyException dake) {
			throw new DuplicateFieldException(dake.getMessage(), dake);
		} catch (UnknownSpeciesKeyException | UnknownHabitatKeyException ex) {
			throw new UnknownFieldException(ex.getMessage());
		}
  }

  /**
   * Parses an employee entry and registers the employee in the hotel.
   * 
   * @param components the components of the employee entry
   * @throws UnknownFieldException if the employee type or responsibility is unknown
   * @throws DuplicateFieldException if a duplicate employee entry is found
   */
  private void parseEmployee(String[] components) throws UnknownFieldException, DuplicateFieldException {
    EmployeeType employeeType;
    switch(components[0]) {
      case "TRATADOR" -> employeeType = EmployeeType.KEEPER;
      case "VETERINÁRIO" -> employeeType = EmployeeType.VETERINARIAN;   
      default -> throw new UnknownFieldException("Unknown employee type.");   
    }
    String[] responsibilitiesIds = components.length == 3 ? new String[0] : components[3].split(",");
    try {
			_hotel.registerEmployee(components[1], components[2], responsibilitiesIds, employeeType);
		} catch (DuplicateEmployeeKeyException deke) {
			throw new DuplicateFieldException(deke.getMessage(), deke);
		} catch (UnknownHabitatKeyException | UnknownSpeciesKeyException ex) {
			throw new UnknownFieldException(ex.getMessage(), ex);
		} 
  }

  /**
   * Parses a vaccine entry and registers the vaccine in the hotel.
   * 
   * @param components the components of the vaccine entry
   * @throws DuplicateFieldException if a duplicate vaccine entry is found
   * @throws UnknownFieldException if a species for the vaccine is unknown
   */
  private void parseVaccine(String[] components) throws DuplicateFieldException, UnknownFieldException {
    String[] speciesIds = components.length == 4 ? components[3].split(",") : new String[0];
    try{
			_hotel.registerVaccine(components[1], components[2], speciesIds);
		} catch (DuplicateVaccineKeyException dvke) {
			throw new DuplicateFieldException(dvke.getMessage(), dvke);
		} catch (UnknownVaccineKeyException uvke) {
			throw new UnknownFieldException(uvke.getMessage(), uvke);
		}
  }
}
