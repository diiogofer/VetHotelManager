package hva.core;

/**
 * Enumeration representing the four seasons: SPRING, SUMMER, AUTUMN, and WINTER.
 * Provides a method to get the next season in the cycle.
 */
public enum Season {
  SPRING, SUMMER, AUTUMN, WINTER;

  /**
   * Returns the next season in the cycle. The seasons cycle through in the following order:
   * SPRING -> SUMMER -> AUTUMN -> WINTER -> SPRING.
   * 
   * @return the next season in the cycle.
   */
  public Season next() {
    return values()[(ordinal() + 1) % values().length];
  }
}
