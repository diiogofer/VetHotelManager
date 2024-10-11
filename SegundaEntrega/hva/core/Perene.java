package hva.core;

/**
 * Represents a Perene tree. The cleaning effort for this type of tree remains 
 * relatively constant across seasons.
 * 
 * <p>This class extends the abstract {@link Tree} class and provides specific implementations
 * for determining the cleaning effort and biological cycle based on the current season.</p>
 * 
 * <p>Perene trees are identified by the string "PERENE".</p>
 * 
 * @see Tree
 * @see Season
 * @see BiologicalCycle
 */
public class Perene extends Tree{

    /** The seasonal effort for each season, represented as an array. */
    private final int[] _seasonalEffort = {2, 1, 1, 1};

    /** The biological cycle for each season, represented as an array. */
    private final BiologicalCycle[] _biologicalCycle = {
        BiologicalCycle.GERARFOLHAS, 
        BiologicalCycle.COMFOLHAS, 
        BiologicalCycle.COMFOLHAS,
        BiologicalCycle.LARGARFOLHAS
    };

    /**
     * Constructs a Perene tree with the given identifier, name, age, base cleaning difficulty,
     * and the hotel where the tree is located.
     * 
     * @param identifier the unique identifier for the tree
     * @param name the name of the tree
     * @param ageInYears the age of the tree in years
     * @param baseCleaningDifficulty the base difficulty of cleaning around the tree
     * @param hotel the hotel where the tree is located
     */
    public Perene(String identifier, String name, int ageInYears, int baseCleaningDifficulty, Hotel hotel) {
        super(identifier, name,ageInYears,baseCleaningDifficulty, hotel);
    }

    /**
     * Returns the seasonal cleaning effort for the tree based on the current season.
     * The cleaning effort remains relatively low throughout the year.
     * 
     * @param season the current season
     * @return the cleaning effort for the given season
     */
    @Override
    int getSeasonalCleaningEffort(Season season) {
        return _seasonalEffort[season.getValue()];
    }

    /**
     * Returns the biological cycle of the tree for the given season. 
     * 
     * @param season the current season
     * @return the biological cycle for the given season
     */
    @Override
    BiologicalCycle getBiologicalCycle(Season season){
        return _biologicalCycle[season.getValue()];
    }


    /**
     * Returns a string representing the type of tree, which is "PERENE".
     * 
     * @return the string "PERENE"
     */
    @Override
    String treeTypeToString() {
        return "PERENE";
    }
}