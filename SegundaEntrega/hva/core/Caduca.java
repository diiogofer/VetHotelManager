package hva.core;

public class Caduca extends Tree {

    private final int[] _seasonalEffort = {0, 1, 2, 5};
    private final BiologicalCycle[] _biologicalCycle = {
        BiologicalCycle.GERARFOLHAS, 
        BiologicalCycle.COMFOLHAS, 
        BiologicalCycle.LARGARFOLHAS,
        BiologicalCycle.SEMFOLHAS
    };

    Caduca(String identifier, String name, int ageInYears, int baseCleaningDifficulty, Hotel hotel) {
        super(identifier, name,ageInYears,baseCleaningDifficulty, hotel);
    }
     @Override
    int getSeasonalCleaningEffort(Season season) {
        return _seasonalEffort[season.getValue()];
    }
    @Override
    BiologicalCycle getBiologicalCycle(Season season){return _biologicalCycle[season.getValue()];}
    @Override
    String treeTypeToString() {return "CADUCA";}

}
