package hva.core;

public abstract class Identified {
    private String _identifier;
    Identified(String identifier) {
        _identifier = identifier;
    }
    String getId() {
        return _identifier;
    }
}
