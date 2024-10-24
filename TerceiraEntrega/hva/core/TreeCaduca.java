package hva.core;

public class TreeCaduca extends Tree {


  TreeCaduca (String id, String name, int age, int baseCleaningDifficulty, Season season) {
    super(id, name, age, baseCleaningDifficulty);
    super.setState(initialTreeState(season));
  }

  @Override
  String treeTypeToString() {
    return "CADUCA";
  }  

  private TreeState initialTreeState(Season season) {
    int index = season.ordinal();
    TreeState state = TreeCaducaSpringState.createTreeCaducaSpringState();
    while(index > 0) {
      state = state.next();
      index--;
    }
    return state;
  }
}
