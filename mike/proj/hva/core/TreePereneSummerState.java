package hva.core;

public class TreePereneSummerState implements TreeState {
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
    return new TreePereneAutumnState();
  }
}
