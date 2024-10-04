package hva.core;

abstract class Tree extends HotelEntity{

    private int _ageInSeasons;
    private int _baseCleaningDifficulty;
    private Hotel _hotel;
    
    
    Tree(String treeId, String treeName, int ageInYears, int baseCleaningDifficulty, Hotel hotel){
        super(treeId, treeName);
        _ageInSeasons = 4 * ageInYears;  
        _baseCleaningDifficulty =  baseCleaningDifficulty;
        _hotel = hotel;
    }

    abstract int getSeasonalCleaningEffort(Season season);

    int getAge() {
        return _ageInSeasons / 4;
    }

    double calculateCleaningEffort() {
        return _baseCleaningDifficulty * getSeasonalCleaningEffort(_hotel.getSeason()) * Math.log(getAge() + 1);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;
        if (obj == null || obj.getClass() != this.getClass()) 
            return false;
        Tree other = (Tree) obj;
        return super.id().equals(other.id());
    }
    
}
