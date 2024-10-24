package hva.core;

/**
 * Represents the summer state for a perennial tree (Perene).
 * In this state, the tree has leaves ("COMFOLHAS") and the cleaning effort is minimal.
 */
public class TreePereneSummerState implements TreeState {

  /** Singleton instance of the summer state. */
  private static TreePereneSummerState _instance;

  /** Private constructor to enforce singleton pattern. */
  private TreePereneSummerState() {}

  /**
   * Returns the singleton instance of the summer state for perennial trees.
   *
   * @return the singleton instance of {@code TreePereneSummerState}
   */
  static TreePereneSummerState createTreePereneSummerState() {
    if(_instance == null) _instance = new TreePereneSummerState();
    return _instance;
  }


  /**
   * Returns the biological cycle of the tree during summer, which is "COMFOLHAS" (with leaves).
   *
   * @return a string representing the biological cycle during summer
   */
  @Override
  public String getBiologicalCycle() {
    return "COMFOLHAS";
  }

  /**
   * Returns the cleaning effort required for the tree during summer.
   * In this state, the effort required is minimal, with a value of 1.
   *
   * @return the seasonal effort for cleaning during summer
   */
  @Override
  public int getSeasonalEffort() {
    return 1;
  }

  /**
   * Advances to the next seasonal state, which is autumn for a perennial tree.
   *
   * @return the next {@code TreeState}, which is {@code TreePereneAutumnState}
   */
  @Override
  public TreeState next() {
    return TreePereneAutumnState.createTreePereneAutumnState();
  }
}
