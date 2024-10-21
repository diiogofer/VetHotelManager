package hva.core;

public class TreeCaducaWinterStrategy implements TreeStrategy {

  @Override
  public String getBiologicalCycle() {
    return "SEMFOLHAS";
  }

  @Override
  public int getSeasonalEffort() {
    return 0;
  }

  @Override
  public TreeStrategy next() {
    return new TreeCaducaSpringStrategy();
  }
  
}
