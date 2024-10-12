package hva.core;

public enum Adequacy {
  POSITIVE(20), NEGATIVE(-20), NEUTRAL(0);
  private int _value;
  Adequacy(int value) {
    _value = value;
  }
  public int getValue() {return _value;}
}
