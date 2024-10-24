package hva.core;

/**
 * Represents the autumn state for a perennial tree (Perene).
 * In this state, the tree has leaves, and the cleaning effort is reduced compared to other seasons.
 */
public class TreePereneAutumnState implements TreeState {

  /** Singleton instance of the autumn state. */
  private static TreePereneAutumnState _instance;

  /** Private constructor to enforce singleton pattern. */
  private TreePereneAutumnState() {}

  /**
   * Returns the singleton instance of the autumn state for perennial trees.
   *
   * @return the singleton instance of {@code TreePereneAutumnState}
   */
  static TreePereneAutumnState createTreePereneAutumnState() {
    if(_instance == null) _instance = new TreePereneAutumnState();
    return _instance;
  }
  
  /**
   * Returns the biological cycle of the tree during autumn, which is "COMFOLHAS" (with leaves).
   *
   * @return a string representing the biological cycle during autumn
   */
  @Override
  public String getBiologicalCycle() {
    return "COMFOLHAS";
  }

  /**
   * Returns the cleaning effort required for the tree during autumn.
   * In this state, the cleaning effort is lower compared to other states, with a value of 1.
   *
   * @return the seasonal effort for cleaning during autumn
   */
  @Override
  public int getSeasonalEffort() {
    return 1;
  }

  /**
   * Advances to the next seasonal state, which is winter for a perennial tree.
   *
   * @return the next {@code TreeState}, which is {@code TreePereneWinterState}
   */
  @Override
  public TreeState next() {
    return TreePereneWinterState.createTreePereneWinterState();
  }
}
