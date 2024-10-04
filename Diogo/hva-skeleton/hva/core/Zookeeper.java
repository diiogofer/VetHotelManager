package hva.core;

import java.util.Map;

public class Zookeeper extends Employee{
    private Map<String, Habitat> _responsibilities;
    
    Zookeeper(String id, String name, Map<String, Habitat> habitats) {
        super(id, name);
        _responsibilities = habitats;
    }
    
    int calculateSatisfaction() {
        double satisfaction = 300;
        for (Habitat habitat : _responsibilities.values()) {
            satisfaction -= ( habitat.calculateWork() / habitat.getNumberKeepers() );
        }
        return (int)Math.round(satisfaction);
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != this.getClass()) 
            return false;
        Zookeeper other = (Zookeeper) obj;
        return super.id().equals(other.id());
    } 
    
}
