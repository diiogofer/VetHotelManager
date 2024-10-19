package hva.app.habitat;

import java.util.List;
import hva.core.Habitat;
import hva.core.Tree;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;

//FIXME add more imports if needed

/**
 * Show all habitats of this zoo hotel.
 **/
class DoShowAllHabitats extends Command<Hotel> {

  DoShowAllHabitats(Hotel receiver) {
    super(Label.SHOW_ALL_HABITATS, receiver);
  }
  
  @Override
  protected void execute() {
        List<Habitat> list = _receiver.getAllHabitats();
    for(Habitat habitat : list) {
      _display.addLine(habitat);
      List<Tree> treeList = _receiver.getAllTreesOfHabitat(habitat);
      for(Tree tree : treeList)
        _display.addLine(tree);
    }
  }
}
