package hva.core;

/**
 * Represents a deciduous tree (Caduca).
 */
public class TreeCaduca extends Tree {

  /**
   * Constructs a new deciduous tree with the specified ID, name, age, base cleaning difficulty, and initial season.
   * The tree's state is initialized based on the provided season.
   * 
   * @param id the unique identifier of the tree
   * @param name the name of the tree
   * @param age the age of the tree in years
   * @param baseCleaningDifficulty the base difficulty for cleaning the tree
   * @param season the current season when the tree is created
   */
  TreeCaduca (String id, String name, int age, int baseCleaningDifficulty, Season season) {
    super(id, name, age, baseCleaningDifficulty);
    super.setState(initialTreeState(season));
  }

  /**
   * Returns the string representation of the tree type, which is "CADUCA" for deciduous trees.
   * 
   * @return the string "CADUCA"
   */
  @Override
  String treeTypeToString() {
    return "CADUCA";
  }  

  /**
   * Determines the initial state of the tree based on the given season.
   * The tree state will advance according to the season provided.
   * 
   * @param season the current season
   * @return the initial tree state corresponding to the season
   */
  private TreeState initialTreeState(Season season) {
    int index = season.ordinal();
    TreeState state = TreeCaducaSpringState.createTreeCaducaSpringState();
    while(index > 0) {
      state = state.next();
      index--;
    }
    return state;
  }
}
