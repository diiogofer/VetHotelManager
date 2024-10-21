package hva.core;

public class TreeCaducaAutumnStrategy implements TreeStrategy {

  @Override
  public String getBiologicalCycle() {
    return "LARGARFOLHAS";
  }

  @Override
  public int getSeasonalEffort() {
    return 5;
  }

  @Override
  public TreeStrategy next() {
    return new TreeCaducaWinterStrategy();
  }
  
}
