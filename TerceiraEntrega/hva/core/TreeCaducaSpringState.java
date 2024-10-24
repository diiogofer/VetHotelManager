package hva.core;


/**
 * Represents the spring state of a deciduous tree (Caduca). In this state, the tree's biological cycle is focused on leaf generation.
 * Implements the Singleton pattern to ensure only one instance of the state is created.
 */
public class TreeCaducaSpringState implements TreeState {

  /** Singleton instance of the TreeCaducaSpringState */
  private static TreeCaducaSpringState _instance;

  /** Private constructor to enforce Singleton pattern */
  private TreeCaducaSpringState() {};

  /**
   * Creates and returns the singleton instance of TreeCaducaSpringState.
   * If the instance does not already exist, it is created.
   * 
   * @return the singleton instance of TreeCaducaSpringState
   */
  static TreeCaducaSpringState createTreeCaducaSpringState() {
    if(_instance == null) _instance = new TreeCaducaSpringState();
    return _instance;
  }

  /**
   * Returns the biological cycle description of the tree in the spring state.
   * In spring, the tree generates new leaves.
   * 
   * @return a string representing the biological cycle, "GERARFOLHAS"
   */
  @Override
  public String getBiologicalCycle() {
    return "GERARFOLHAS";
  }

  /**
   * Returns the seasonal effort required to maintain the tree in the spring state.
   * 
   * @return the seasonal effort value, which is 1 in spring
   */
  @Override
  public int getSeasonalEffort() {
    return 1;
  }


  /**
   * Advances the tree to its next state in the seasonal cycle, which is summer.
   * 
   * @return the TreeCaducaSummerState representing the summer state of the tree
   */
  @Override
  public TreeState next() {
    return TreeCaducaSummerState.createTreeCaducaSummerState();
  }  
}
