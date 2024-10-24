package hva.core;


/**
 * Represents the summer state of a deciduous tree (Caduca). In this state, the tree has its leaves fully developed.
 * Implements the Singleton pattern to ensure only one instance of the state is created.
 */
public class TreeCaducaSummerState implements TreeState {

  /** Singleton instance of the TreeCaducaSummerState */
  private static TreeCaducaSummerState _instance;

  /** Private constructor to enforce Singleton pattern */
  private TreeCaducaSummerState() {};

  /**
   * Creates and returns the singleton instance of TreeCaducaSummerState.
   * If the instance does not already exist, it is created.
   * 
   * @return the singleton instance of TreeCaducaSummerState
   */
  static TreeCaducaSummerState createTreeCaducaSummerState() {
    if(_instance == null) _instance = new TreeCaducaSummerState();
    return _instance;
  }

  /**
   * Returns the biological cycle description of the tree in the summer state.
   * In summer, the tree has its leaves fully developed.
   * 
   * @return a string representing the biological cycle, "COMFOLHAS"
   */
  @Override
  public String getBiologicalCycle() {
    return "COMFOLHAS";
  }

  /**
   * Returns the seasonal effort required to maintain the tree in the summer state.
   * 
   * @return the seasonal effort value, which is 2 in summer
   */
  @Override
  public int getSeasonalEffort() {
    return 2;
  }

  /**
   * Advances the tree to its next state in the seasonal cycle, which is autumn.
   * 
   * @return the TreeCaducaAutumnState representing the autumn state of the tree
   */
  @Override
  public TreeState next() {
    return TreeCaducaAutumnState.createTreeCaducaAutumnState();
  }
  
}
