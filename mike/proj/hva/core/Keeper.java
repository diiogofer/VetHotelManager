package hva.core;

import java.util.*;

import hva.core.sorter.SortById;

public class Keeper extends Employee {
    private Map<String, Habitat> _responsibilities = new HashMap<>();

    Keeper(String identifier, String name, List<Habitat> habitats) {
        super(identifier, name);
        for(Habitat h : habitats) {
            _responsibilities.put(h.getId(), h);
        }
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
    @Override
    String getEmployeeTypeToString() {
        return "TRT";
    }
    @Override
    String getResponsibilitiesToString() {
        String responsibilityString = "";
        List<Habitat> responsibilityList = new ArrayList<>(_responsibilities.values());
        Collections.sort(responsibilityList, new SortById<>());
        for(Habitat h : responsibilityList) {
            String id = h.getId();
            responsibilityString += responsibilityString.length() == 0 ? id : "," + id;
        }
        return responsibilityString;
    }
}
