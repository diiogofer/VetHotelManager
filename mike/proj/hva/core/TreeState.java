package hva.core;

public interface TreeState {
  int getSeasonalEffort();
  String getBiologicalCycle();
  TreeState next();
}
