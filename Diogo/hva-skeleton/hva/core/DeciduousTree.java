package hva.core;
/* CADUCA */
public class DeciduousTree extends Tree {
    
    private final int[] _seasonalEffort = {0, 1, 2, 5};


    public DeciduousTree(String identifier, String name, int ageInYears, int baseCleaningDifficulty, Hotel hotel) {
        super(identifier, name, ageInYears, baseCleaningDifficulty, hotel);
    }
    @Override
    public int getSeasonalCleaningEffort(Season season) {
        return _seasonalEffort[season.getValue()];
    }
}
