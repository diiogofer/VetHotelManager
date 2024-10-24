package hva.core;

public class TreeCaducaSpringState implements TreeState {

  private static TreeCaducaSpringState _instance;

  private TreeCaducaSpringState() {};

  static TreeCaducaSpringState createTreeCaducaSpringState() {
    if(_instance == null) _instance = new TreeCaducaSpringState();
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
    return TreeCaducaSummerState.createTreeCaducaSummerState();
  }  
}
