package hva.core;

import java.io.*;

import hva.core.exception.*;

public class Parser {
    private Hotel _hotel;

    Parser(Hotel h) {_hotel = h;}


    public void parseFile(String filename) throws IOException, UnrecognizedEntryException {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = reader.readLine()) != null) parseLine(line);
        }
    }

    private void parseLine(String line) throws UnrecognizedEntryException {
        String[] components = line.split("\\|");
        switch(components[0]) {
            case "ESPÉCIE" -> parseSpecies(components);
            case "ÁRVORE" -> parseTree(components);
            case "HABITAT" -> parseHabitat(components);
            case "ANIMAL" -> parseAnimal(components);
            case "TRATADOR" -> parseEmployee(components);
            case "VETERINÁRIO" -> parseEmployee(components);
            case "VACINA" -> parseVaccine(components);
            default -> throw new UnrecognizedEntryException("tipo de entrada inválido: " + components[0]);
        }
    }

    private void parseSpecies(String[] components) throws UnrecognizedEntryException {
        try {
            _hotel.registerSpecies(components[1], components[2]);
        } catch (DuplicateSpeciesKeyException | DuplicateSpeciesNameException ex) {
            throw new UnrecognizedEntryException("tipo de entrada inválido");
        }
    }

    private void parseTree(String[] components) throws UnrecognizedEntryException {
        try {
            TreeType treeType;
            switch(components[5]) {
                case "CADUCA" -> treeType = TreeType.CADUCA;
                case "PERENE" -> treeType = TreeType.PERENE;   
                default -> throw new UnknownTreeTypeException();   
            }
            _hotel.registerTree(components[1], components[2], components[3], components[4], treeType);
        } catch (DuplicateTreeKeyException | UnknownTreeTypeException ex) {
            throw new UnrecognizedEntryException("tipo de entrada inválido");
        }
    }

    private void parseHabitat(String[] components) throws UnrecognizedEntryException {
        try {
            String[] treeIds = components.length == 4 ? new String[0] : components[4].split(",");
            _hotel.registerHabitat(components[1], components[2], components[3], treeIds);
        } catch (DuplicateHabitatKeyException | UnknownTreeKeyException ex) {
            throw new UnrecognizedEntryException("tipo de entrada inválido");
        }
    }

    private void parseAnimal(String[] components) throws UnrecognizedEntryException {
        try {
            _hotel.registerAnimal(components[1], components[2], components[3], components[4]);
        } catch (DuplicateAnimalKeyException | UnknownHabitatKeyException | UnknownSpeciesKeyException ex) {
            throw new UnrecognizedEntryException("tipo de entrada inválido");
        }
    }

    private void parseEmployee(String[] components) throws UnrecognizedEntryException {
        try {
            EmployeeType employeeType;
            switch(components[0]) {
                case "TRATADOR" -> employeeType = EmployeeType.KEEPER;
                case "VETERINÁRIO" -> employeeType = EmployeeType.VETERINARIAN;   
                default -> throw new UnknownEmployeeTypeException();   
            }
            String[] responsibilitiesIds = components.length == 3 ? new String[0] : components[3].split(",");
            _hotel.registerEmployee(components[1], components[2], responsibilitiesIds, employeeType);
        } catch (DuplicateEmployeeKeyException | UnknownSpeciesKeyException | UnknownEmployeeTypeException ex) {
            throw new UnrecognizedEntryException("tipo de entrada inválido");
        }
    }

    private void parseVaccine(String[] components) throws UnrecognizedEntryException {
        try {
            String[] speciesIds = components.length == 4 ? components[3].split(",") : new String[0];
            _hotel.registerVaccine(components[1], components[2], speciesIds);
        } catch (DuplicateVaccineKeyException | UnknownSpeciesKeyException ex) {
            throw new UnrecognizedEntryException("tipo de entrada inválido");
        }
    }

    // private void parseAnimal(String[] components) {
    //     try{
    //         _hotel.registerAnimal(components[1], components[2], components[3], components[4]);
    //     } catch (excCore1 | excCore2 | ...) { //Exceptions lançada por registerAnimal()
    //         throw new UnrecognizedEntryException("tipo de entrada inválido: ");
    //     }
    // }

    // private void parseVaccine(String[] components) {
    //     try {
    //         String[] speciesIds = components.length == 4 ? components[3].split(",") : new String[0];
    //     }
    // }
}
