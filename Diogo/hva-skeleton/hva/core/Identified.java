package hva.core;

import java.io.Serializable;

public abstract class Identified implements Comparable<Identified>, Serializable {
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

    public boolean equals(Identified iden) {
        return _identifier.equalsIgnoreCase(iden.getId());
    }
}
