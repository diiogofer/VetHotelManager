package hva.core;

public enum Season {
    SPRING(0), 
    SUMMER(1),
    AUTUMN(2),
    WINTER(3);

    private final int _value;

    Season(int value) {
        _value = value;
    }

    
    public int getValue() {
        return _value;
    }

    public Season nextSeason() {
        return switch (this) {
            case SPRING -> SUMMER;
            case SUMMER -> AUTUMN;
            case AUTUMN -> WINTER;
            case WINTER -> SPRING;
        };
    }
}
