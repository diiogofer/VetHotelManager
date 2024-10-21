package hva.core;

public class TreeCaducaSummerState implements TreeState {

  @Override
  public String getBiologicalCycle() {
    return "COMFOLHAS";
  }

  @Override
  public int getSeasonalEffort() {
    return 2;
  }

  @Override
  public TreeState next() {
    return new TreeCaducaAutumnState();
  }
  
}
