package hva.core;

public abstract class Tree extends Identified {
  private Habitat _habitat;
  private int _ageInSeasons;
  private int _baseCleaningDifficulty;
  private TreeState _state;

  Tree (String id, String name, int age, int baseCleaningDifficulty) {
    super(id, name);
    _ageInSeasons = age * 4;
    _baseCleaningDifficulty = baseCleaningDifficulty;
  }

  double calculateEffort(){
    return _baseCleaningDifficulty * getSeasonalEffort() * Math.log(getAgeInYears() + 1);
  }

  void setHabitat(Habitat habitat) {
    if(_habitat != null) 
      _habitat.removeTree(this);
    _habitat = habitat;
    habitat.addTree(this);
  }

  private int getAgeInYears() {
    return (int)(_ageInSeasons / 4);
  }

  abstract String treeTypeToString();

  String getBioCycle() {
    return getState().getBiologicalCycle();
  }
  int getSeasonalEffort() {
    return getState().getSeasonalEffort();
  }
  void advanceSeason() {
    setState(getState().next());
  }
  TreeState getState() {return _state;}
  void setState(TreeState state){
    _state = state;
  }

  public String toString() {
    return "ÁRVORE|" + getId() + "|" + getName() + "|" + getAgeInYears() + 
      "|" + _baseCleaningDifficulty + "|" + treeTypeToString() + "|" + getBioCycle();
  }
}
