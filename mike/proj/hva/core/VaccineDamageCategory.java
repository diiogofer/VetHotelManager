package hva.core;

/**
 * Represents the different categories of damage
 * that can occur during a vaccination event. Each category has a string label associated with it.
 */
public enum VaccineDamageCategory {

  /** Represents a normal vaccination event with no issues. */
	NORMAL("NORMAL"),

  /** Represents a vaccination event that caused confusion. */
	CONFUSION("CONFUSÃO"),

  /** Represents an accidental issue during the vaccination. */
  ACCIDENT("ACIDENTE"),

  /** Represents an error during the vaccination process. */
  ERROR("ERRO");

  /** The label representing the damage category. */
  private String _label;

  /**
   * Constructs a VaccineDamageCategory with the specified label.
   * 
   * @param label the label for the damage category
   */
  VaccineDamageCategory(String label) {
      _label = label;
  }
  
  /**
   * Returns the string representation of the damage category.
   * 
   * @return the label of the damage category
   */
  public String toString() {
      return _label;
  }
}
