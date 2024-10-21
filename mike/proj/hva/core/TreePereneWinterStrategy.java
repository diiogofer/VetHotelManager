package hva.core;

public class TreePereneWinterStrategy implements TreeStrategy {
  @Override
  public String getBiologicalCycle() {
    return "LARGARFOLHAS";
  }

  @Override
  public int getSeasonalEffort() {
    return 2;
  }

  @Override
  public TreeStrategy next() {
    return new TreePereneSpringStrategy();
  }
}
