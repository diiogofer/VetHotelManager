package hva.app.habitat;

import hva.core.Hotel;
import hva.core.Tree;
import hva.core.exception.UnknownHabitatException;
import java.util.List;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {

  DoShowAllTreesInHabitat(Hotel receiver) {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
    addStringField("habitatKey", Prompt.habitatKey());
  }
  
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
