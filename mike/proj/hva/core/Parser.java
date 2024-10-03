package hva.core;

import java.io.*;
import java.util.*;

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
            case "TRATADOR" -> parseKeeper(components);
            case "VETERINÁRIO" -> parseVet(components);
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
            _hotel.registerTree(components[1], components[2], components[3], components[4], components[0]);
        } catch (DuplicateTreeKeyException ex) {
            throw new UnrecognizedEntryException("tipo de entrada inválido");
        }
    }

    private void parseHabitat(String[] components) throws UnrecognizedEntryException {
        try {
            if(components.length == 4)
                _hotel.registerHabitat(components[1], components[2], components[3]);
            else {
                String[] treeIds = new String[components.length - 4];
                for(int i = 3; i < components.length; i++) {
                    treeIds[i-3] = components[i];
                }
                _hotel.registerHabitat(components[1], components[2], components[3], treeIds);
            }    
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
