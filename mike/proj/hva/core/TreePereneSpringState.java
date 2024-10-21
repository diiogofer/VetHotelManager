package hva.core;

public class TreePereneSpringState implements TreeState {
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
    return new TreePereneSummerState();
  }
}
