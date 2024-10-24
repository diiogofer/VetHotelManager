package hva.core;

/**
 * Represents a perennial tree (Perene).
 * This class extends the {@link Tree} class.
 */
public class TreePerene extends Tree {
  
  /**
   * Constructs a TreePerene instance with the given ID, name, age, base cleaning difficulty, and initial season.
   * The tree's state is initialized according to the given season.
   *
   * @param id                     the unique identifier for the tree
   * @param name                   the name of the tree
   * @param age                    the age of the tree in years
   * @param baseCleaningDifficulty the base difficulty of cleaning the tree
   * @param season                 the current season that determines the initial state of the tree
   */
  TreePerene (String id, String name, int age, int baseCleaningDifficulty, Season season) {
    super(id, name, age, baseCleaningDifficulty);
    super.setState(initialTreeState(season));
  }

  /**
   * Returns the type of tree as a string, which is "PERENE" for perennial trees.
   *
   * @return a string representing the type of tree
   */
  @Override
  String treeTypeToString() {
    return "PERENE";
  }

  /**
   * Determines the initial state of the tree based on the current season.
   * The tree's state will change according to the given season's ordinal value.
   *
   * @param season the current season used to set the initial state
   * @return the initial state of the tree based on the current season
   */
  private TreeState initialTreeState(Season season) {
    int index = season.ordinal();
    TreeState state = TreePereneSpringState.createTreePereneSpringState();
    while(index > 0) {
      state = state.next();
      index--;
    }
    return state;
  }
}
