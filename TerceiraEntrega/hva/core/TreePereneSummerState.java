package hva.core;

public class TreePereneSummerState implements TreeState {

  private static TreePereneSummerState _instance;

  private TreePereneSummerState() {}

  static TreePereneSummerState createTreePereneSummerState() {
    if(_instance == null) _instance = new TreePereneSummerState();
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
    return TreePereneAutumnState.createTreePereneAutumnState();
  }
}
