package hva.app.habitat;

import java.util.List;
import hva.core.Tree;
import hva.core.Hotel;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {

  DoShowAllTreesInHabitat(Hotel receiver) {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
    addStringField("habitatId", Prompt.habitatKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
      List<Tree> list =_receiver.getAllTreesOfHabitat(stringField("habitatId"));
      for (Tree t : list) 
        _display.addLine(t);
    } catch (hva.core.exception.UnknownHabitatKeyException uhke) {
      throw new UnknownHabitatKeyException(stringField("habitatId"));
    }
  }
}
