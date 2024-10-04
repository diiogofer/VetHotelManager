package hva.core;

import java.util.Map;

public class Veterinarian extends Employee{
    private Map<String, Species> _responsibilities;

    Veterinarian(String id, String name, Map<String, Species> responsibilities) {
        super(id, name);
        _responsibilities = responsibilities;
    }

    @Override
    int calculateSatisfaction() {
        double satisfaction = 20.0;
        for(Species species : _responsibilities.values()) {
            satisfaction -= ((species.getPopulation() / species.getNumberQualifiedVets()));
        }
        return (int)Math.round(satisfaction);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != this.getClass()) 
            return false;
        Veterinarian other = (Veterinarian) obj;
        return super.id().equals(other.id());
    } 

}
