package hva.core;

public class TreeCaduca extends Tree {


  TreeCaduca (String id, String name, int age, int baseCleaningDifficulty) {
    super(id, name, age, baseCleaningDifficulty);
    super.setStrategy(new TreeCaducaSpringStrategy());
  }

  @Override
  String treeTypeToString() {
    return "CADUCA";
  }
  @Override
  String getBioCycle() {
    return getStrategy().getBiologicalCycle();
  }
  @Override
  int getSeasonalEffort() {
    return getStrategy().getSeasonalEffort();
  }
  @Override
  void advanceSeason() {
    setStrategy(getStrategy().next());
  }
  
}
