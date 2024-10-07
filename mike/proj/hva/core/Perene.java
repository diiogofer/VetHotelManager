package hva.core;

public class Perene extends Tree{
    private final int[] _seasonalEffort = {2, 1, 1, 1};
    private final BiologicalCycle[] _biologicalCycle = {
        BiologicalCycle.GERARFOLHAS, 
        BiologicalCycle.COMFOLHAS, 
        BiologicalCycle.COMFOLHAS,
        BiologicalCycle.LARGARFOLHAS
    };

    public Perene(String identifier, String name, int ageInYears, int baseCleaningDifficulty, Hotel hotel) {
        super(identifier, name,ageInYears,baseCleaningDifficulty, hotel);
    }
    @Override
    int getSeasonalCleaningEffort(Season season) {
        return _seasonalEffort[season.getValue()];
    }
    @Override
    BiologicalCycle getBiologicalCycle(Season season){return _biologicalCycle[season.getValue()];}

    @Override
    String treeTypeToString() {return "PERENE";}
}