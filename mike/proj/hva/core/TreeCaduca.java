package hva.core;

/**
 * Represents a deciduous tree (Caduca). 
 * The class tracks the biological cycle of the tree 
 * throughout different seasons and adjusts the seasonal cleaning effort based on 
 * the current season.
 * 
 * @see BiologicalCycle
 * @see Season
 * @see Tree
 */
public class TreeCaduca extends Tree {

    /** Array representing the seasonal effort for each season. */
    private final int[] _seasonalEffort = {0, 1, 2, 5};
    
    /** Array representing the biological cycle of the tree for each season. */
    private final TreeBiologicalCycle[] _biologicalCycle = {
        TreeBiologicalCycle.GERARFOLHAS, 
        TreeBiologicalCycle.COMFOLHAS, 
        TreeBiologicalCycle.LARGARFOLHAS,
        TreeBiologicalCycle.SEMFOLHAS
    };

    /**
     * Constructs a Caduca (deciduous tree) with the specified identifier, name, age, 
     * base cleaning difficulty, and associated hotel.
     * 
     * @param identifier the unique identifier of the tree
     * @param name the name of the tree
     * @param ageInYears the age of the tree in years
     * @param baseCleaningDifficulty the base difficulty of cleaning around the tree
     * @param hotel the hotel where the tree is located
     */
    TreeCaduca(String identifier, String name, int ageInYears, int baseCleaningDifficulty, Hotel hotel) {
        super(identifier, name,ageInYears,baseCleaningDifficulty, hotel);
    }

    /**
     * Returns the cleaning effort required for the given season.
     * 
     * @param season the current season
     * @return the cleaning effort value for the given season
     */
    @Override
    int getSeasonalCleaningEffort(Season season) {
        return _seasonalEffort[season.getValue()];
    }

    /**
     * Returns the biological cycle of the tree for the given season.
     * 
     * @param season the current season
     * @return the biological cycle of the tree for the season
     */
    @Override
    TreeBiologicalCycle getBiologicalCycle(Season season){
        return _biologicalCycle[season.getValue()];
    }

    /**
     * Returns a string representing the type of the tree, which is "CADUCA".
     * 
     * @return the string "CADUCA"
     */
    @Override
    String treeTypeToString() {
        return "CADUCA";
    }

}
