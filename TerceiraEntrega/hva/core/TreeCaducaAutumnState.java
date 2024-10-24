package hva.core;

public class TreeCaducaAutumnState implements TreeState {

  private static TreeCaducaAutumnState _instance;

  private TreeCaducaAutumnState() {};

  static TreeCaducaAutumnState createTreeCaducaAutumnState() {
    if(_instance == null) _instance = new TreeCaducaAutumnState();
    return _instance;
  }

  @Override
  public String getBiologicalCycle() {
    return "LARGARFOLHAS";
  }

  @Override
  public int getSeasonalEffort() {
    return 5;
  }

  @Override
  public TreeState next() {
    return TreeCaducaWinterState.createTreeCaducaWinterState();
  }
  
}
