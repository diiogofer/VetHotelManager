package hva.core;

/**
 * Represents the winter state of a deciduous tree (Caduca). In this state, the tree has no leaves.
 * Implements the Singleton pattern to ensure only one instance of the state is created.
 */
public class TreeCaducaWinterState implements TreeState {

  /** Singleton instance of the TreeCaducaWinterState */
  private static TreeCaducaWinterState _instance;


  /** Private constructor to enforce Singleton pattern */
  private TreeCaducaWinterState() {}

  /**
   * Creates and returns the singleton instance of TreeCaducaWinterState.
   * If the instance does not already exist, it is created.
   * 
   * @return the singleton instance of TreeCaducaWinterState
   */
  static TreeCaducaWinterState createTreeCaducaWinterState() {
    if(_instance == null) _instance = new TreeCaducaWinterState();
    return _instance;
  }

  /**
   * Returns the biological cycle description of the tree in the winter state.
   * In winter, the tree has no leaves.
   * 
   * @return a string representing the biological cycle, "SEMFOLHAS"
   */
  @Override
  public String getBiologicalCycle() {
    return "SEMFOLHAS";
  }

  /**
   * Returns the seasonal effort required to maintain the tree in the winter state.
   * Since the tree has no leaves, the effort is minimal.
   * 
   * @return the seasonal effort value, which is 0 in winter
   */
  @Override
  public int getSeasonalEffort() {
    return 0;
  }

  /**
   * Advances the tree to its next state in the seasonal cycle, which is spring.
   * 
   * @return the TreeCaducaSpringState representing the spring state of the tree
   */
  @Override
  public TreeState next() {
    return TreeCaducaSpringState.createTreeCaducaSpringState();
  }
  
}
