package hva.core;

public abstract class Tree extends Identified {
  private Habitat _habitat;
  private String _name;
  private int _ageInSeasons;
  private int _baseCleaningDifficulty;

  Tree (String id, String name, int age, int baseCleaningDifficulty) {
    super(id);
    _name = name;
    _ageInSeasons = age * 4;
    _baseCleaningDifficulty = baseCleaningDifficulty;
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

  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Tree tree)) return false;
    return this.getId().toLowerCase().equals(tree.getId().toLowerCase());
  }
  public int hashCode() {return this.getId().toLowerCase().hashCode();}

  abstract String treeTypeToString();

  //TODO
  abstract String getBioCycle();

  public String toString() {
    return "ÁRVORE|" + getId() + "|" + _name + "|" + getAgeInYears() + 
      "|" + _baseCleaningDifficulty + "|" + treeTypeToString() + "|" + getBioCycle();
  }
}
