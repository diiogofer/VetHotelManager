package hva.core;

/**
 * Represents the spring state for a perennial tree (Perene).
 * In this state, the tree is in the process of generating leaves, and the cleaning effort is minimal.
 */
public class TreePereneSpringState implements TreeState {

  /** Singleton instance of the spring state. */
  private static TreePereneSpringState _instance;

  /** Private constructor to enforce singleton pattern. */
  private TreePereneSpringState() {}

  /**
   * Returns the singleton instance of the spring state for perennial trees.
   *
   * @return the singleton instance of {@code TreePereneSpringState}
   */
  static TreePereneSpringState createTreePereneSpringState() {
    if(_instance == null) _instance = new TreePereneSpringState();
    return _instance;
  }

  /**
   * Returns the biological cycle of the tree during spring, which is "GERARFOLHAS" (generating leaves).
   *
   * @return a string representing the biological cycle during spring
   */
  @Override
  public String getBiologicalCycle() {
    return "GERARFOLHAS";
  }


  /**
   * Returns the cleaning effort required for the tree during spring.
   * In this state, the effort required is minimal, with a value of 1.
   *
   * @return the seasonal effort for cleaning during spring
   */
  @Override
  public int getSeasonalEffort() {
    return 1;
  }

  /**
   * Advances to the next seasonal state, which is summer for a perennial tree.
   *
   * @return the next {@code TreeState}, which is {@code TreePereneSummerState}
   */
  @Override
  public TreeState next() {
    return TreePereneSummerState.createTreePereneSummerState();
  }
}
