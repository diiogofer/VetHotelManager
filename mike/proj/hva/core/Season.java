package hva.core;

public enum Season {
  SPRING(0), SUMMER(1), AUTUMN(2), WINTER(3);
  
  private final int _value;

  Season(int value) {
    _value = value;
  }

  int getValue() {return _value;}

  public Season next() {
    switch (this) {
      case SPRING: return SUMMER;
      case SUMMER: return AUTUMN;
      case AUTUMN: return WINTER;
      case WINTER: return SPRING;
      default: return this;
    }
  }
}
