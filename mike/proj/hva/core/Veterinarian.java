package hva.core;

import java.util.*;

public class Veterinarian extends Employee{
    private Map<String, Species> _responsibilities;

    Veterinarian(String identifier, String name, Hotel hotel) {
        super(identifier, name, hotel);
        _responsibilities = new HashMap<>();
    }

    @Override
    int calculateSatisfaction() {
        double sat = 0;
        for(Species s : _responsibilities.values()) {
            sat -= ((s.getPopulation() / s.getNumberQualifiedVets()));
        }
        sat += 20;
        return (int)Math.round(sat);
    }
}
