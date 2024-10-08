package hva.core;

public abstract class Tree extends Identified{
    private String _name;
    private int _ageInSeasons;
    private int _baseCleaningDifficulty;
    private Hotel _hotel;
    private Habitat _habitat;

    Tree(String identifier, String name, int ageInYears, int baseCleaningDifficulty, Hotel hotel) {
        super(identifier);
        _name = name;
        _ageInSeasons = 4 * ageInYears;
        _baseCleaningDifficulty = baseCleaningDifficulty;
        _hotel = hotel;
    }
    void changeHabitat(Habitat habitat) {
        if(_habitat != null) _habitat.removeTree(this);
        _habitat = habitat;
    }
    String getName() {return _name;}
    int getAge() {return _ageInSeasons / 4;}
    Hotel getHotel() {return _hotel;}

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Tree)) return false;
        Tree tree = (Tree)object;
        return getId().equals(tree.getId());
    }
    abstract String treeTypeToString();

    int getBaseCleaningDifficulty() {return _baseCleaningDifficulty;}
    abstract int getSeasonalCleaningEffort(Season season);
    public double calculateCleaningEffort() {
        return _baseCleaningDifficulty * getSeasonalCleaningEffort(_hotel.getSeason()) * Math.log(getAge() + 1);
    }
    abstract BiologicalCycle getBiologicalCycle(Season season);
    public String toString() {
        return "ÁRVORE|" + getId() + "|" + _name + "|" + getAge() + "|" + _baseCleaningDifficulty + 
            "|" + treeTypeToString() + "|" + getBiologicalCycle(_hotel.getSeason());
    }
}
