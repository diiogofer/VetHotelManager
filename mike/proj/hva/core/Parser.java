package hva.core;

import java.io.*;

import hva.app.exception.DuplicateEmployeeKeyException;
import hva.core.exception.*;

public class Parser {
  private Hotel _hotel;

  Parser(Hotel h) {_hotel = h;}

  public void parseFile(String filename) throws IOException, UnrecognizedEntryException, DuplicateFieldException, UnknownFieldException {
    try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      while((line = reader.readLine()) != null) parseLine(line);
    }
  }

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

  private void parseSpecies(String[] components) throws DuplicateFieldException {
    try {
			_hotel.registerSpecies(components[1], components[2]);
		} catch (DuplicateSpeciesKeyException | DuplicateSpeciesNameException ex) {
			throw new DuplicateFieldException(ex.getMessage());
		}
  }

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
			throw new DuplicateFieldException(dtke.getMessage());
		} catch (UnknownTreeTypeException utte) {
			throw new UnknownFieldException(utte.getMessage());
		}
  }
  private void parseHabitat(String[] components) throws DuplicateFieldException {
    String[] treeIds = components.length == 4 ? new String[0] : components[4].split(",");
    try {
			_hotel.registerHabitat(components[1], components[2], components[3], treeIds);
		} catch (DuplicateHabitatKeyException dhke) {
			throw new DuplicateFieldException(dhke.getMessage());
		}
  }

  private void parseAnimal(String[] components) throws UnknownFieldException, DuplicateFieldException {
  	try{
			_hotel.registerAnimal(components[1], components[2], components[3], components[4]);
		} catch (DuplicateAnimalKeyException dake) {
			throw new DuplicateFieldException(dake.getMessage());
		} catch (UnknownSpeciesKeyException | UnknownHabitatKeyException ex) {
			throw new UnknownFieldException(ex.getMessage());
		}
  }

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
			throw new DuplicateFieldException(deke.getMessage());
		} catch (UnknownHabitatKeyException | UnknownSpeciesKeyException ex) {
			throw new UnknownFieldException(ex.getMessage());
		} 
  }

  private void parseVaccine(String[] components) throws DuplicateFieldException, UnknownFieldException {
    String[] speciesIds = components.length == 4 ? components[3].split(",") : new String[0];
    try{
			_hotel.registerVaccine(components[1], components[2], speciesIds);
		} catch (DuplicateVaccineKeyException dvke) {
			throw new DuplicateFieldException(dvke.getMessage());
		} catch (UnknownVaccineKeyException uvke) {
			throw new UnknownFieldException(uvke.getMessage());
		}
  }
}
