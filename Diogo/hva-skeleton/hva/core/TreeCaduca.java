package hva.core;

public class TreeCaduca extends Tree {
  
    TreeCaduca (String identifier, String treeName, int treeAge, int baseCleaningDifficulty) {
        super(identifier, treeName, treeAge, baseCleaningDifficulty);
    }

    @Override
    String treeTypeToString() {
      return "CADUCA";
    }
    //TODO
    @Override
    String getBioCycle() {
      return "TODO";
    }
    
}
