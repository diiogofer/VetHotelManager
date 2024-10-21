package hva.core;

public class TreeCaducaWinterState implements TreeState {

  @Override
  public String getBiologicalCycle() {
    return "SEMFOLHAS";
  }

  @Override
  public int getSeasonalEffort() {
    return 0;
  }

  @Override
  public TreeState next() {
    return new TreeCaducaSpringState();
  }
  
}
