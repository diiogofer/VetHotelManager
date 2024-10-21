package hva.core;

public class TreeCaducaSummerStrategy implements TreeStrategy {

  @Override
  public String getBiologicalCycle() {
    return "COMFOLHAS";
  }

  @Override
  public int getSeasonalEffort() {
    return 2;
  }

  @Override
  public TreeStrategy next() {
    return new TreeCaducaAutumnStrategy();
  }
  
}
