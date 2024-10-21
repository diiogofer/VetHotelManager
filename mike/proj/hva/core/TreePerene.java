package hva.core;

public class TreePerene extends Tree {
  TreePerene (String id, String name, int age, int baseCleaningDifficulty) {
    super(id, name, age, baseCleaningDifficulty);
    super.setState(new TreePereneSpringState());
  }

  @Override
  String treeTypeToString() {
    return "PERENE";
  }  
}
