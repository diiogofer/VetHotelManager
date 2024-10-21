package hva.core;

public class TreeCaduca extends Tree {


  TreeCaduca (String id, String name, int age, int baseCleaningDifficulty) {
    super(id, name, age, baseCleaningDifficulty);
    super.setState(new TreeCaducaSpringState());
  }

  @Override
  String treeTypeToString() {
    return "CADUCA";
  }  
}
