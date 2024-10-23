package hva.core;

public class TreeCaducaSummerState implements TreeState {

  private static TreeCaducaSummerState _instance;

  private TreeCaducaSummerState() {};

  static TreeCaducaSummerState createTreeCaducaSummerState() {
    if(_instance == null) _instance = new TreeCaducaSummerState();
    return _instance;
  }

  @Override
  public String getBiologicalCycle() {
    return "COMFOLHAS";
  }

  @Override
  public int getSeasonalEffort() {
    return 2;
  }

  @Override
  public TreeState next() {
    return TreeCaducaAutumnState.createTreeCaducaAutumnState();
  }
  
}
