package hva.core;

public class TreePerene extends Tree {
  TreePerene (String id, String name, int age, int baseCleaningDifficulty) {
    super(id, name, age, baseCleaningDifficulty);
    super.setStrategy(new TreePereneSpringStrategy());
  }

  @Override
  String treeTypeToString() {
    return "PERENE";
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
