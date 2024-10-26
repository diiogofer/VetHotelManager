package hva.core;

/**
 * The {@code TreeState} interface defines the behavior for different seasonal states of a tree.
 * Each tree state represents a specific season and governs the tree's biological cycle
 * and the effort required for its cleaning or maintenance.
 */
public interface TreeState {
  /**
   * Returns the cleaning or maintenance effort required for the tree during the current seasonal state.
   * 
   * @return the seasonal effort required for cleaning the tree
   */
  int getSeasonalEffort();

  /**
   * Returns the biological cycle of the tree during the current seasonal state.
   * The biological cycle may indicate whether the tree is growing leaves, shedding them, or is in another phase.
   *
   * @return a string representing the biological cycle of the tree
   */
  String getBiologicalCycle();

  /**
   * Advances to the next seasonal state of the tree.
   * 
   * @return the next {@code TreeState} representing the next season
   */
  TreeState next();
}
