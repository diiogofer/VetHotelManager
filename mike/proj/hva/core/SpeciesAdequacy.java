package hva.core;

public enum SpeciesAdequacy {
  POSITIVE(20), NEGATIVE(-20), NEUTRAL(0);
  private int _value;
  SpeciesAdequacy(int value) {
    _value = value;
  }
  public int getValue() {return _value;}
}
