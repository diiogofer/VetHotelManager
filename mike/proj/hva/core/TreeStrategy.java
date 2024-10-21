package hva.core;

public interface TreeStrategy {
  int getSeasonalEffort();
  String getBiologicalCycle();
  TreeStrategy next();
}
