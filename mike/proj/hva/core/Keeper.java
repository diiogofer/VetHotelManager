package hva.core;

import java.util.*;

public class Keeper extends Employee {
    private Map<String, Habitat> _responsibilities;

    Keeper(String identifier, String name, Hotel hotel) {
        super(identifier, name, hotel);
        _responsibilities = new HashMap<>();
    }

    @Override
    int calculateSatisfaction() {
        double sat = 0;
        for (Habitat h : _responsibilities.values()) {
            sat -= ( h.calculateWork() / h.getNumberKeepers() );
        }
        sat += 300;
        return (int)Math.round(sat);
    }

}
