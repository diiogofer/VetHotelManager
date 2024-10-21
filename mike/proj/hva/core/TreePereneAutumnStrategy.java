package hva.core;

public class TreePereneAutumnStrategy implements TreeStrategy {
  @Override
  public String getBiologicalCycle() {
    return "COMFOLHAS";
  }

  @Override
  public int getSeasonalEffort() {
    return 1;
  }

  @Override
  public TreeStrategy next() {
    return new TreePereneWinterStrategy();
  }
}
