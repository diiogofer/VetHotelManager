package hva.core;

public class TreePereneAutumnState implements TreeState {

  private static TreePereneAutumnState _instance;

  private TreePereneAutumnState() {}

  static TreePereneAutumnState createTreePereneAutumnState() {
    if(_instance == null) _instance = new TreePereneAutumnState();
    return _instance;
  }
  
  @Override
  public String getBiologicalCycle() {
    return "COMFOLHAS";
  }

  @Override
  public int getSeasonalEffort() {
    return 1;
  }

  @Override
  public TreeState next() {
    return TreePereneWinterState.createTreePereneWinterState();
  }
}
