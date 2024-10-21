package hva.core;

public class TreeCaducaAutumnState implements TreeState {

  @Override
  public String getBiologicalCycle() {
    return "LARGARFOLHAS";
  }

  @Override
  public int getSeasonalEffort() {
    return 5;
  }

  @Override
  public TreeState next() {
    return new TreeCaducaWinterState();
  }
  
}
