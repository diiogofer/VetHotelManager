package hva.core;

public class TreePerene extends Tree {
  
    TreePerene (String identifier, String treeName, int treeAge, int baseCleaningDifficulty) {
        super(identifier, treeName, treeAge, baseCleaningDifficulty);
    }

    @Override
    String treeTypeToString() {
      return "PERENE";
    }
    //TODO
    @Override
    String getBioCycle() {
      return "TODO";
    }
    
}