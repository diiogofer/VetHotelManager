package hva.core;

public class TreeCaducaSpringState implements TreeState {

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
    return new TreeCaducaSummerState();
  }  
}
