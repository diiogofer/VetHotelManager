package hva.core;

public enum VaccinationResult {
    NORMAL(0, 0), 
    CONFUSION(0, 0), 
    ACCIDENT(1, 4), 
    ERROR(5, Integer.MAX_VALUE); 

    private final int minDamage;
    private final int maxDamage;

    VaccinationResult(int minDamage, int maxDamage) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    static VaccinationResult getResultFromDamage(int damage, boolean isSameSpecies) {
        if (damage == 0) {
            return isSameSpecies ? NORMAL : CONFUSION;
        }
        for (VaccinationResult result : values()) {
            if (damage >= result.minDamage && damage <= result.maxDamage) {
                return result;
            }
        }
        return ERROR;
    }
}

