package hva.core;

public class TreePereneWinterState implements TreeState {

  private static TreePereneWinterState _instance;

  private TreePereneWinterState() {}

  static TreePereneWinterState createTreePereneWinterState() {
    if(_instance == null) _instance = new TreePereneWinterState();
    return _instance;
  }

  @Override
  public String getBiologicalCycle() {
    return "LARGARFOLHAS";
  }

  @Override
  public int getSeasonalEffort() {
    return 2;
  }

  @Override
  public TreeState next() {
    return TreePereneSpringState.createTreePereneSpringState();
  }
}
