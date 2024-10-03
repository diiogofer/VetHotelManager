package hva.core;

import java.io.*;
import java.util.*;

import hva.core.exception.UnrecognizedEntryException;

public class Parser {
    private Hotel _hotel;

    Parser(Hotel h) {_hotel = h;}

    public void parseFile(String filename) throws IOException, UnrecognizedEntryException {
        try(BufferedReader reader = new BufferedReader(FileReader(filename))) {
            String line;

            while((line = reader.readLine()) != null) parseLine(line);
        }
    }

    private void parseLine(String line) throws UnrecognizedEntryException {
        String[] components = line.split("\\|");
        switch(components[0]) {
            case "ESPÉCIE" -> parseSpecies(components);
            default -> throw new UnrecognizedEntryException("tipo de entrada inválido: " + components[0]);
        }
    }

    private void parseAnimal(String[] components) {
        try{
            _hotel.registerAnimal(components[1], components[2], components[3], components[4]);
        } catch (excCore1 | excCore2 | ...) { //Exceptions lançada por registerAnimal()
            throw new UnrecognizedEntryException("tipo de entrada inválido: ");
        }
    }

    private void parseVaccine(String[] components) {
        try {
            String[] speciesIds = components.length == 4 ? components[3].split(",") : new String[0];
        }
    }
}
