package hva.core;
/* PERENE */
public class EvergreenTree extends Tree{
    private final int[] _seasonalEffort = {2, 1, 1, 1};

    public EvergreenTree(String identifier, String name, int ageInYears, int baseCleaningDifficulty, Hotel hotel) {
        super(identifier, name, ageInYears, baseCleaningDifficulty, hotel);
    }
    
    @Override
    public int getSeasonalCleaningEffort(Season season) {
        return _seasonalEffort[season.getValue()];
    }
} 
