package hva.core;

public class TreePereneWinterState implements TreeState {
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
    return new TreePereneSpringState();
  }
}
