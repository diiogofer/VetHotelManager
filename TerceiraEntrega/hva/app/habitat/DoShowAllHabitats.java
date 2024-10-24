package hva.app.habitat;

import hva.core.Habitat;
import hva.core.Tree;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import java.util.List;

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
    for(Habitat h : list) {
      _display.addLine(h);
      List<Tree> treeList = _receiver.getAllTreesOfHabitat(h);
      for(Tree t : treeList)
        _display.addLine(t);
    }
  }
}
