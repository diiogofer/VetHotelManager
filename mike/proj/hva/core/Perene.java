package hva.core;

public class Perene extends Tree{
    private final int[] _seasonalEffort = {2, 1, 1, 1};

    public Perene(String identifier, String name, int ageInYears, int baseCleaningDifficulty, Hotel hotel) {
        super(identifier, name,ageInYears,baseCleaningDifficulty, hotel);
    }
    @Override
    public int getSeasonalCleaningEffort(Season season) {
        return _seasonalEffort[season.getValue()];
    }
}