package hva.app.habitat;

import java.util.List;
import hva.core.*;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all habitats of this zoo hotel.
 **/
class DoShowAllHabitats extends Command<Hotel> {
  /**
   * Constructs a new command to show all habitats in the zoo hotel.
   * 
   * @param receiver the hotel from which habitats will be retrieved
   */
  DoShowAllHabitats(Hotel receiver) {
    super(Label.SHOW_ALL_HABITATS, receiver);
  }
  
  /**
   * Executes the command by retrieving all habitats from the hotel and displaying them.
   * For each habitat, the trees within it are also displayed.
   */
  @Override
  protected void execute() {
    List<Habitat> habitatList = _receiver.getAllHabitats();
    for(Habitat habitat : habitatList) {
      _display.addLine(habitat);
      List<Tree> treeList = habitat.getAllTrees();
      for(Tree tree : treeList) {
        _display.addLine(tree);
      }
    }
  }
}
