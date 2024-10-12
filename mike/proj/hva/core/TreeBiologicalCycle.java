package hva.core;

/**
 * Enum representing the different biological cycles a tree might go through.
 * The possible values are:
 * <ul>
 *   <li>COMFOLHAS - With leaves</li>
 *   <li>LARGARFOLHAS - Shedding leaves</li>
 *   <li>SEMFOLHAS - Without leaves</li>
 *   <li>GERARFOLHAS - Generating leaves</li>
 * </ul>
 */
public enum TreeBiologicalCycle {
    COMFOLHAS, LARGARFOLHAS, SEMFOLHAS, GERARFOLHAS;
    /**
     * Returns the name of the biological cycle as a string.
     * 
     * @return the name of the biological cycle
     */
    @Override
    public String toString() {
        return name();
    }
}
