package hva.core;

import java.util.*;

import hva.core.sorter.SortById;

public class Veterinarian extends Employee{
    private Map<String, Species> _responsibilities = new HashMap<>();

    Veterinarian(String identifier, String name, List<Species> responsibilities) {
        super(identifier, name);

        for (Species s : responsibilities) {
            _responsibilities.put(s.getId(), s);
        }
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
    @Override
    String getEmployeeTypeToString() {
        return "VET";
    }
    @Override
    String getResponsibilitiesToString() {
        String responsibilityString = "";
        List<Species> responsibilityList = new ArrayList<>(_responsibilities.values());
        Collections.sort(responsibilityList, new SortById<>());
        for(Species s : responsibilityList) {
            String id = s.getId();
            responsibilityString += responsibilityString.length() == 0 ? id : "," + id;
        }
        return responsibilityString;
    }
}
