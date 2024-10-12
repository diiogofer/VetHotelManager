package hva.core;

/**
 * Enum representing the possible damages that can occur during a vaccine event.
 * 
 * <p>The possible damages are:</p>
 * <ul>
 *   <li>{@code NORMAL} - 0 damage (same species)</li>
 *   <li>{@code CONFUSÃO} - 0 damage (different species).</li>
 *   <li>{@code ACIDENTE} - 1 to 4 damage.</li>
 *   <li>{@code ERRO} - 5 or more damage.</li>
 * </ul>
 * 
 * @see VaccineEvent
 */
public enum VaccineEventDamage {
    /** No damage occurred, the event proceeded as expected. */
    NORMAL, 
    
    /** Confusion occurred during the event. */
    CONFUSÃO, 
    
    /** An accident occurred during the vaccination. (1 to 4 damage)*/
    ACIDENTE, 

    /** A mistake was made during the vaccination process. (5 or more damage) */
    ERRO;
    
    /**
     * Returns the string representation of the damage type.
     * This method overrides {@code toString()} to return the enum constant's name.
     * 
     * @return the name of the damage type
     */
    public String toString() {
        return name();
    }
}
