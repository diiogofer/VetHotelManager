package hva.core;

public class TreeCaduca extends Tree {
  TreeCaduca (String id, String name, int age, int baseCleaningDifficulty) {
    super(id, name, age, baseCleaningDifficulty);
  }

  @Override
  String treeTypeToString() {
    return "CADUCA";
  }

  //TODO
  @Override
  String getBioCycle() {
    return "TODO";
  }
  
}
