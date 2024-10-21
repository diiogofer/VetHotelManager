package hva.core;

public class TreePereneSummerStrategy implements TreeStrategy {
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
    return new TreePereneAutumnStrategy();
  }
}
