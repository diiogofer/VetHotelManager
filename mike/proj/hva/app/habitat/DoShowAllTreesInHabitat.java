package hva.app.habitat;

import hva.core.Hotel;
import hva.core.Tree;
import hva.core.exception.UnknownHabitatException;

import java.util.List;

import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {

  DoShowAllTreesInHabitat(Hotel receiver) {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
    addStringField("habitatKey", Prompt.habitatKey());
  }
  
/**
 * Executes the command to show all trees in a given habitat.
 *
 * @throws UnknownHabitatKeyException if the specified habitat key does not exist.
 */
  @Override
  protected void execute() throws CommandException {
    String habitatKey = stringField("habitatKey");
    try {
      List<Tree> treeList = _receiver.getAllTreesOfHabitat(habitatKey);
      for (Tree tree : treeList)
        _display.addLine(tree);
    } catch (UnknownHabitatException uhe) {
      throw new UnknownHabitatKeyException(habitatKey);
    }
  }
}
