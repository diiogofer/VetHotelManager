package hva.core;

/**
 * Represents a tree in the system.
 */
public abstract class Tree extends Identified {

  /** The habitat to which the tree belongs. */
  private Habitat _habitat;

  /** The age of the tree in seasons (one year equals four seasons). */
  private int _ageInSeasons;

  /** The base difficulty associated with cleaning the tree. */
  private int _baseCleaningDifficulty;

  /** The current state of the tree (e.g., active, dormant, etc.). */
  private TreeState _state;

  /**
   * Constructs a tree with the specified ID, name, age, and base cleaning difficulty.
   * 
   * @param id the unique identifier of the tree
   * @param name the name of the tree
   * @param age the age of the tree in years
   * @param baseCleaningDifficulty the base difficulty associated with cleaning the tree
   */
  Tree (String id, String name, int age, int baseCleaningDifficulty) {
    super(id, name);
    _ageInSeasons = age * 4;
    _baseCleaningDifficulty = baseCleaningDifficulty;
  }

  /**
   * Calculates the effort required to clean the tree, based on its age and seasonal effort.
   * 
   * @return the cleaning effort for the tree
   */
  double calculateEffort(){
    return _baseCleaningDifficulty * getSeasonalEffort() * Math.log(getAgeInYears() + 1);
  }

  /**
   * Sets the habitat for the tree, removing it from any previous habitat.
   * 
   * @param habitat the new habitat for the tree
   */
  void setHabitat(Habitat habitat) {
    if(_habitat != null) 
      _habitat.removeTree(this);
    _habitat = habitat;
    habitat.addTree(this);
  }

  /**
   * Returns the age of the tree in years.
   * 
   * @return the age of the tree in years
   */
  private int getAgeInYears() {
    return (int)(_ageInSeasons / 4);
  }

  /**
   * Abstract method to be implemented by subclasses to return the tree type as a string.
   * 
   * @return the type of tree as a string
   */
  abstract String treeTypeToString();

  /**
   * Returns the biological cycle of the tree based on its current state.
   * 
   * @return the biological cycle of the tree
   */
  String getBioCycle() {
    return getState().getBiologicalCycle();
  }

  /**
   * Returns the seasonal effort required for the tree based on its current state.
   * 
   * @return the seasonal effort for the tree
   */
  int getSeasonalEffort() {
    return getState().getSeasonalEffort();
  }

  /**
   * Advances the tree to the next season, updating its state accordingly.
   */
  void advanceSeason() {
    setState(getState().next());
  }

  /**
   * Returns the current state of the tree.
   * 
   * @return the current state of the tree
   */
  TreeState getState() {return _state;}

  /**
   * Sets the state of the tree.
   * 
   * @param state the new state of the tree
   */
  void setState(TreeState state){
    _state = state;
  }

  /**
   * Returns a string representation of the tree, including its ID, name, age, base cleaning difficulty, type, and biological cycle.
   * 
   * @return a string representing the tree
   */
  public String toString() {
    return "ÁRVORE|" + getId() + "|" + getName() + "|" + getAgeInYears() + 
      "|" + _baseCleaningDifficulty + "|" + treeTypeToString() + "|" + getBioCycle();
  }
}
