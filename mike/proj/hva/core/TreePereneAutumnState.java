package hva.core;

public class TreePereneAutumnState implements TreeState {
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
    return new TreePereneWinterState();
  }
}
