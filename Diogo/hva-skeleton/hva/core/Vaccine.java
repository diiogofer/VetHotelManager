package hva.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Vaccine extends HotelEntity {
    private Map<String, Species> _speciesMap;
    private List<VaccineApplication> _vaccineApplications;
    
    public Vaccine(String vaccineId, String vaccineName, Map<String, Species> speciesMap) {
        super(vaccineId, vaccineName);
        _speciesMap = speciesMap;
        _vaccineApplications = new ArrayList<>();
        
    }

    List<Species> getVaccineSpecies(){
        return new ArrayList<>(_speciesMap.values());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Vaccine other = (Vaccine) obj;
        return super.id().equals(other.id());
    }
    
}
