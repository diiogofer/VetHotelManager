package hva.core;

public abstract class Identified implements Comparable<Identified> {
    private String _identifier;
    Identified(String identifier) {
        _identifier = identifier;
    }
    
    public String getId() { //why public ??????
        return _identifier;
    }

    public int compareTo(Identified iden) {
        return (_identifier.toLowerCase()).compareTo(iden.getId().toLowerCase());
    }
}
