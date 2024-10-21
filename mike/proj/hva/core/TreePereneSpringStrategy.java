package hva.core;

public class TreePereneSpringStrategy implements TreeStrategy {
  @Override
  public String getBiologicalCycle() {
    return "GERARFOLHAS";
  }

  @Override
  public int getSeasonalEffort() {
    return 1;
  }

  @Override
  public TreeStrategy next() {
    return new TreePereneSummerStrategy();
  }
}
