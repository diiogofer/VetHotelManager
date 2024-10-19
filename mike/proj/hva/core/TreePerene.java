package hva.core;

public class TreePerene extends Tree {
  TreePerene (String id, String name, int age, int baseCleaningDifficulty) {
    super(id, name, age, baseCleaningDifficulty);
  }

  @Override
  String treeTypeToString() {
    return "PERENE";
  }

  //TODO
  @Override
  String getBioCycle() {
    return "TODO";
  }
  
}
