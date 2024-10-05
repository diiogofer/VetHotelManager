package hva.core;

import java.io.Serializable;

public abstract class Tree implements Identifiable, Serializable{
    private final String _identifier;
    private String _name;
    private int _ageInSeasons;
    private int _baseCleaningDifficulty;
    private Hotel _hotel;

    Tree(String identifier, String name, int ageInYears, int baseCleaningDifficulty, Hotel hotel) {
        _identifier = identifier;
        _name = name;
        _ageInSeasons = 4 * ageInYears;
        _baseCleaningDifficulty = baseCleaningDifficulty;
        _hotel = hotel;
    }
    
    public String getId() {return _identifier;}
    String getName() {return _name;}
    int getAge() {return _ageInSeasons / 4;}
    Hotel getHotel() {return _hotel;}

    @Override
    public int hashCode() {return _identifier.hashCode();}

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Tree)) return false;
        Tree tree = (Tree)object;
        return _identifier.equals(tree._identifier);
    }
    abstract String treeTypeToString();

    int getBaseCleaningDifficulty() {return _baseCleaningDifficulty;}
    abstract int getSeasonalCleaningEffort(Season season);
    public double calculateCleaningEffort() {
        return _baseCleaningDifficulty * getSeasonalCleaningEffort(_hotel.getSeason()) * Math.log(getAge() + 1);
    }
    abstract BiologicalCycle getBiologicalCycle(Season season);
    public String toString() {
        return "ÁRVORE|" + _identifier + "|" + _name + "|" + getAge() + "|" + _baseCleaningDifficulty + 
            "|" + treeTypeToString() + "|" + getBiologicalCycle(_hotel.getSeason());
    }
}
