package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.*;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.DuplicateTreeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

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
    String habitatKey = stringField("habitatKey");
    String treeKey = stringField("treeKey");
    String treeName = stringField("treeName");
    int treeAge = integerField("treeAge");
    int treeDifficulty = integerField("treeDifficulty");
    String treeType = optionField("treeType");
    String ret = null;
    try {
      switch (treeType) {
        case "PERENE" -> ret = _receiver.registerPerene(habitatKey, treeKey, treeName, treeAge, treeDifficulty);
        case "CADUCA" -> ret = _receiver.registerCaduca(habitatKey, treeKey, treeName, treeAge, treeDifficulty);
      }
    } catch (DuplicateTreeException dte) {
      throw new DuplicateTreeKeyException(dte.getMessage());
    } catch (UnknownHabitatException uhe) {
      throw new UnknownHabitatKeyException(uhe.getMessage());
    }
    if(ret != null) _display.addLine(ret);
  }
}
