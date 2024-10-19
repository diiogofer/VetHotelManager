package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.DuplicateTreeException;
import hva.core.exception.UnknownHabitatException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.DuplicateTreeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    addStringField("habitatKey", Prompt.habitatKey());
    addStringField("treeKey", Prompt.treeKey());
    addStringField("treeName", Prompt.treeName());
    addIntegerField("treeAge", Prompt.treeAge());
    addIntegerField("treeDifficulty", Prompt.treeDifficulty());
    addOptionField("treeType", Prompt.treeType(), "PERENE", "CADUCA");
  }
  
  @Override
  protected void execute() throws CommandException {
    String habitatKey = stringField("habitatkey");
    String treeKey = stringField("treeKey");
    String treeName = stringField("treeName");
    int treeAge = integerField("treeAge");
    int treeDifficulty = integerField("treeDifficulty");
    String treeType = optionField("treeType");
    try {
      switch (treeType) {
        case "PERENE" -> _receiver.registerPerene(habitatKey, treeKey, treeName, treeAge, treeDifficulty);
        case "CADUCA" -> _receiver.registerCaduca(habitatKey, treeKey, treeName, treeAge, treeDifficulty);
        default -> System.out.println("Algo correu mal!");
      }
    } catch (DuplicateTreeException dte) {
      throw new DuplicateTreeKeyException(dte.getMessage());
    } catch (UnknownHabitatException uhe) {
      throw new UnknownHabitatKeyException(uhe.getMessage());
    }
  }
}
