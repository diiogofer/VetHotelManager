package hva.core;

public class TreePerene extends Tree {
  TreePerene (String id, String name, int age, int baseCleaningDifficulty, Season season) {
    super(id, name, age, baseCleaningDifficulty);
    super.setState(initialTreeState(season));
  }

  @Override
  String treeTypeToString() {
    return "PERENE";
  }

  private TreeState initialTreeState(Season season) {
    int index = season.ordinal();
    TreeState state = TreePereneSpringState.createTreePereneSpringState();
    while(index > 0) {
      state = state.next();
      index--;
    }
    return state;
  }
}
