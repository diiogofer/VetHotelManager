package hva.core;

public class TreeCaducaSpringStrategy implements TreeStrategy {

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
    return new TreeCaducaSummerStrategy();
  }  
}
