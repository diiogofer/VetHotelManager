package hva.core;

/**
 * Represents the winter state for a evergreen tree (Perene).
 * In this state, the tree sheds its leaves ("LARGARFOLHAS") and requires moderate cleaning effort.
 */
public class TreePereneWinterState implements TreeState {

  /** Singleton instance of the winter state. */
  private static TreePereneWinterState _instance;

  /** Private constructor to enforce singleton pattern. */
  private TreePereneWinterState() {}

  /**
   * Returns the singleton instance of the winter state for evergreen trees.
   *
   * @return the singleton instance of {@code TreePereneWinterState}
   */
  static TreePereneWinterState createTreePereneWinterState() {
    if(_instance == null) _instance = new TreePereneWinterState();
    return _instance;
  }

  /**
   * Returns the biological cycle of the tree during winter, which is "LARGARFOLHAS" (shedding leaves).
   *
   * @return a string representing the biological cycle during winter
   */
  @Override
  public String getBiologicalCycle() {
    return "LARGARFOLHAS";
  }

  /**
   * Returns the cleaning effort required for the tree during winter.
   * In this state, the effort required is moderate, with a value of 2.
   *
   * @return the seasonal effort for cleaning during winter
   */
  @Override
  public int getSeasonalEffort() {
    return 2;
  }

  /**
   * Advances to the next seasonal state, which is spring for a evergreen tree.
   *
   * @return the next {@code TreeState}, which is {@code TreePereneSpringState}
   */
  @Override
  public TreeState next() {
    return TreePereneSpringState.createTreePereneSpringState();
  }
}
