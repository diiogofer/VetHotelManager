package hva.core;

public class TreeCaducaWinterState implements TreeState {

  private static TreeCaducaWinterState _instance;

  private TreeCaducaWinterState() {}

  static TreeCaducaWinterState createTreeCaducaWinterState() {
    if(_instance == null) _instance = new TreeCaducaWinterState();
    return _instance;
  }

  @Override
  public String getBiologicalCycle() {
    return "SEMFOLHAS";
  }

  @Override
  public int getSeasonalEffort() {
    return 0;
  }

  @Override
  public TreeState next() {
    return TreeCaducaSpringState.createTreeCaducaSpringState();
  }
  
}
