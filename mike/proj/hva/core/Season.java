package hva.core;

/**
 * Represents the four seasons of the year: Spring (PRIMAVERA), Summer (VERÃO), Autumn (OUTONO), and Winter (INVERNO).
 * Each season is associated with an integer value, which can be retrieved using the getValue() method.
 * 
 * <p>The integer values assigned to the seasons are:</p>
 * <ul>
 *   <li>PRIMAVERA: 0</li>
 *   <li>VERÃO: 1</li>
 *   <li>OUTONO: 2</li>
 *   <li>INVERNO: 3</li>
 * </ul>
 */
public enum Season {

    /** Represents the Spring season. */
    PRIMAVERA(0), 
    
    /** Represents the Summer season. */
    VERÃO(1), 
    
    /** Represents the Autumn season. */ 
    OUTONO(2), 
    
    /** Represents the Winter season. */ 
    INVERNO(3);

    /** The integer value associated with the season. */
    private final int _value;

    /**
     * Constructs a Season with the given integer value.
     * 
     * @param value the integer value associated with the season
     */
    Season(int value) {
        _value = value;
    }

    /**
     * Returns the integer value associated with the season.
     * 
     * @return the integer value representing the season
     */
    public int getValue() {
        return _value;
    }
}