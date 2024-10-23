package hva.core;

public class TreePereneSpringState implements TreeState {

  private static TreePereneSpringState _instance;

  private TreePereneSpringState() {}

  static TreePereneSpringState createTreePereneSpringState() {
    if(_instance == null) _instance = new TreePereneSpringState();
    return _instance;
  }

  @Override
  public String getBiologicalCycle() {
    return "GERARFOLHAS";
  }

  @Override
  public int getSeasonalEffort() {
    return 1;
  }

  @Override
  public TreeState next() {
    return TreePereneSummerState.createTreePereneSummerState();
  }
}
