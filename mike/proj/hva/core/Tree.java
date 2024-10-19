package hva.core;

public class Tree extends Identified {
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
  void setHabitat(Habitat habitat) {_habitat = habitat;}
}
