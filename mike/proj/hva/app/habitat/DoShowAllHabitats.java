package hva.app.habitat;

import java.util.List;
import hva.core.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
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
    List<Habitat> habitatList = _receiver.getAllHabitats();
    for(Habitat h : habitatList) {
      _display.addLine(h);
      List<Tree> treeList = h.getAllTrees();
      for(Tree t : treeList) {_display.addLine(t);}
    }
  }
}
