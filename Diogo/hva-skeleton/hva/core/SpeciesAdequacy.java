package hva.core;

public enum SpeciesAdequacy {
  POSITIVE(20), NEUTRAL(0), NEGATIVE(-20);
  
  private final int value;
  
  SpeciesAdequacy(int value) {
      this.value = value;
  }
  public int getValue() {
      return value;
  }
}
