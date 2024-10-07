package hva.core;

public enum Season {
    PRIMAVERA(0), VERÃO(1), OUTONO(2), INVERNO(3);

    private final int _value;

    Season(int value) {_value = value;}

    public int getValue() {return _value;}
}