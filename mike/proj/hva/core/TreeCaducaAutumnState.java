package hva.core;

/**
 * Represents the autumn state of a deciduous tree (Caduca). In this state, the tree's biological cycle is characterized by leaf shedding.
 * Implements the Singleton pattern to ensure only one instance of the state is created.
 */
public class TreeCaducaAutumnState implements TreeState {
  
  /** Singleton instance of the TreeCaducaAutumnState */
  private static TreeCaducaAutumnState _instance;

  /** Private constructor to enforce Singleton pattern */
  private TreeCaducaAutumnState() {};

  /**
   * Creates and returns the singleton instance of TreeCaducaAutumnState.
   * If the instance does not already exist, it is created.
   * 
   * @return the singleton instance of TreeCaducaAutumnState
   */
  static TreeCaducaAutumnState createTreeCaducaAutumnState() {
    if(_instance == null) _instance = new TreeCaducaAutumnState();
    return _instance;
  }

  /**
   * Returns the biological cycle description of the tree in the autumn state.
   * In autumn, the tree is shedding its leaves.
   * 
   * @return a string representing the biological cycle, "LARGARFOLHAS"
   */
  @Override
  public String getBiologicalCycle() {
    return "LARGARFOLHAS";
  }


  /**
   * Returns the seasonal effort required to maintain the tree in the autumn state.
   * 
   * @return the seasonal effort value, which is 5 in autumn
   */
  @Override
  public int getSeasonalEffort() {
    return 5;
  }

  /**
   * Advances the tree to its next state in the seasonal cycle, which is winter.
   * 
   * @return the TreeCaducaWinterState representing the winter state of the tree
   */
  @Override
  public TreeState next() {
    return TreeCaducaWinterState.createTreeCaducaWinterState();
  }
  
}
