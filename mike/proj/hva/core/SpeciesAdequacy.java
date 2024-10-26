package hva.core;

/**
 * Enum representing the adequacy of a habitat for a particular species.
 * The adequacy can be positive, neutral, or negative, and each has an associated numeric value.
 */
public enum SpeciesAdequacy {
  
  /** Positive adequacy, meaning the habitat is highly suitable for the species. */
  POSITIVE(20), 
  
  /** Neutral adequacy, meaning the habitat has no significant impact on the species. */
  NEUTRAL(0), 
  
  /** Negative adequacy, meaning the habitat is not suitable for the species. */
  NEGATIVE(-20);
  
  /** The numeric value associated with the adequacy. */
  private final int value;

  /**
   * Constructor for creating a species adequacy with a specific value.
   * 
   * @param value the numeric value representing the adequacy
   */
  SpeciesAdequacy(int value) {
      this.value = value;
  }

  /**
   * Returns the numeric value associated with the species adequacy.
   * 
   * @return the value of the adequacy
   */
  public int getValue() {
      return value;
  }
}
